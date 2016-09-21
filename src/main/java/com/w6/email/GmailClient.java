package com.w6.email;

import com.sun.mail.pop3.POP3SSLStore;

import java.util.*;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.search.FlagTerm;

public class GmailClient {
    private Session session = null;
    private Store store = null;
    private String username, password;
    private Folder folder;

    public GmailClient() {
    }

    public void setUserPass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void connect() throws Exception {

        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties pop3Props = new Properties();

        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

        URLName url = new URLName("pop3", "pop.gmail.com", 995, "",
                username, password);

        session = Session.getInstance(pop3Props, null);
        store = new POP3SSLStore(session, url);
        store.connect();

    }

    public void openFolder(String folderName) throws Exception {
        folder = store.getFolder(folderName);

        if (folder == null) {
            throw new Exception("Invalid folder");
        }

        try {

            folder.open(Folder.READ_WRITE);

        } catch (MessagingException ex) {
            folder.open(Folder.READ_ONLY);
        }
    }

    public void closeFolder() throws Exception {
        folder.close(false);
    }

    public int getMessageCount() throws Exception {
        return folder.getMessageCount();
    }

    
    public Message getMessageById(int id) throws Exception {
        if (folder != null) {
            return folder.getMessage(id);
        }

        return null;
    }

    public int getNewMessageCount() throws Exception {
        return folder.getNewMessageCount();
    }
    
    public Collection<Message> getUnreadMessages() throws MessagingException {
        if (folder != null) {
            Message[] messages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            return new ArrayList<>(Arrays.asList(messages));
        }
        return null;
    }

    public void disconnect() throws Exception {
        if (folder != null) {
            closeFolder();
        }
        store.close();
    }

    public void printMessage(int messageNo) throws Exception {
        System.out.println("Getting message number: " + messageNo);

        Message m = null;

        try {
            m = folder.getMessage(messageNo);
        } catch (IndexOutOfBoundsException iex) {
            System.out.println("Message number out of range");
        }
    }

}
