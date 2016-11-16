package com.w6.email;

import com.sun.mail.pop3.POP3SSLStore;
import com.w6.data.Email;
import com.w6.nlp.MySolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.search.FlagTerm;

public class GmailClient {

    private Store store;
    private String username, password;
    private Folder folder;
    private final ScheduledExecutorService fScheduler;
    private static final int NUM_THREADS = 1;
    private static final long fInitialDelay = 0;
    private static final long fDelayBetweenRuns = 3600;
    private ScheduledFuture<?> emailLoadingFuture;
    private static final boolean DONT_INTERRUPT_IF_RUNNING = false;

    @Autowired
    private MySolrClient mySolrClient;

    public GmailClient(String username, String password, String folderName) {
        this.username = username;
        this.password = password;
        fScheduler = Executors.newScheduledThreadPool(NUM_THREADS);
        try {
            connect();
            openFolder(folderName);
        } catch (MessagingException e) {
            Logger.getLogger(GmailClient.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void connect() throws MessagingException {

        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties pop3Props = new Properties();

        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

        URLName url = new URLName("pop3", "pop.gmail.com", 995, "",
                username, password);

        Session session = Session.getInstance(pop3Props, null);
        store = new POP3SSLStore(session, url);
        store.connect();

    }

    private void openFolder(String folderName) throws MessagingException {
        folder = store.getFolder(folderName);

        if (folder == null) {
            throw new MessagingException("Invalid folder");
        }

        try {
            folder.open(Folder.READ_WRITE);
        } catch (MessagingException ex) {
            folder.open(Folder.READ_ONLY);
        }
    }

    private void closeFolder() throws MessagingException {
        if (folder != null) {
            folder.close(false);
        }
    }

    public int getMessageCount() throws MessagingException {
        if (folder == null) {
            throw new MessagingException("Folder is not open!");
        } else {
            return folder.getMessageCount();
        }
    }


    public Message getMessageById(int id) throws MessagingException {
        if (folder == null) {
            throw new MessagingException("Folder is not open!");
        } else {
            return folder.getMessage(id);
        }
    }

    public int getNewMessageCount() throws MessagingException {
        if (folder == null) {
            throw new MessagingException("Folder is not open!");
        } else {
            return folder.getNewMessageCount();
        }
    }

    public Collection<Message> getUnreadMessages() throws MessagingException {
        if (folder != null) {
            Message[] messages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            return new ArrayList<>(Arrays.asList(messages));
        }
        return new ArrayList<>();
    }


    public void disconnect() throws MessagingException {
        if (folder != null) {
            closeFolder();
        }
        if (store != null) {
            store.close();
        }
    }

    public static Email parseMessage(Message message, int id) throws MessagingException, IOException {
        String from = "", subject = "", date = "", text = "";
        if (message != null) {
            List<Address> fromArray = new ArrayList<>(Arrays.asList(message.getFrom()));
            StringBuilder fromBuilder = new StringBuilder("");
            fromArray.forEach((address -> fromBuilder.append(address.toString())));
            from = fromBuilder.toString();
            date = message.getSentDate().toString();
            subject = message.getSubject();
            text = writePart(message);
        }

        return new Email(id, date, subject, text, from, false);
    }

    private static String writePart(Part p) throws IOException, MessagingException {
        if (p.isMimeType("text/plain")) {
            return (String) p.getContent();
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            BodyPart bp = mp.getBodyPart(0);
            return (String) bp.getContent();
        } else if (p.isMimeType("message/rfc822")) {
            return writePart((Part) p.getContent());
        } else {
            Object o = p.getContent();
            if (o instanceof String) {
                return (String) o;
            }
        }
        return "";

    }

    public void activateEmailLoading() {
        Runnable emailLoader = new EmailLoadingTask();
        emailLoadingFuture = fScheduler.scheduleWithFixedDelay(
                emailLoader, fInitialDelay, fDelayBetweenRuns, TimeUnit.SECONDS
        );
    }

    public void deactivateEmailLoading() {
        Runnable deactivateEmailLoading = new DeactivateEmailLoadingTask();
        fScheduler.schedule(deactivateEmailLoading, 0, TimeUnit.SECONDS);
    }

    private class DeactivateEmailLoadingTask implements Runnable {
        @Override
        public void run() {
            emailLoadingFuture.cancel(DONT_INTERRUPT_IF_RUNNING);
            fScheduler.shutdown();
        }
    }


    private class EmailLoadingTask implements Runnable {
        @Override
        public void run() {
            try {
                final int[] maxIndex = {
                        mySolrClient.getAllNewEmails().size()
                };
                List<Email> emailList = new ArrayList<>();
                List<Message> unreadMessages = new ArrayList<>(getUnreadMessages());
                unreadMessages.forEach((message -> {
                    try {
                        emailList.add(parseMessage(message, ++maxIndex[0]));
                    } catch (MessagingException | IOException e) {
                        e.printStackTrace();
                    }
                }));
                mySolrClient.updateEmailsInSolr(emailList);
            } catch (MessagingException | SolrServerException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
