package com.w6.nlp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.w6.data.Article;
import com.w6.data.Event;
import com.w6.data.Response;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;

public class MySolrClient 
{
    
    final private String client = "user";
    final private String password = "220895";
    final private String url = "http://" + client + ":" + password + "@" +"localhost:8983/solr/core/";   
    final private String urlEvents = "http://" + client + ":" + password + "@" +"localhost:8983/solr/events/";   
    final private SolrClient clientSolr;
    final private SolrClient clientEventSolr;
    private static final Gson gson = new GsonBuilder().create();

    
    public MySolrClient()
    {
        clientSolr = new HttpSolrClient(url);
        clientEventSolr = new HttpSolrClient(urlEvents);
    }
    
    public void uploadDataToSolr(
            Article article)
        throws IOException, SolrServerException
    {
        if (article.id == -1)
        {
            article.id = getNumberOfDocuments() + 1;
        }
        clientSolr.add(createDocument(article));
        clientSolr.commit();
    }

    public long uploadEventToSolr(
            Event event)
        throws IOException, SolrServerException
    {
        event.id = getNumberOfEvents()+ 1;
        clientEventSolr.add(createEvent(event));
        clientEventSolr.commit();
        return event.id;
    }
    
    public Article getDocumentById(long id) throws SolrServerException, IOException
    {
        SolrDocument document = clientSolr.getById(Long.toString(id));
        String eventId = document.getFieldValue("eventId").toString();
        eventId = eventId.substring(1, eventId.length() - 1);
        
        return new Article(
                id,
                document.getFieldValue("sourse").toString(),
                document.getFieldValue("text").toString(),
                document.getFieldValue("title").toString(),
                document.getFieldValue("response").toString(),
                Integer.parseInt(eventId)
        );
    }
    
    private long getNumberOfDocuments() throws SolrServerException, IOException
    {
        SolrQuery query = new SolrQuery();
        query.setQuery( "*" );
        
        QueryResponse response = clientSolr.query(query);
        
        SolrDocumentList listOfDocuments = response.getResults();
        return listOfDocuments.getNumFound();
    }
    
    public ArrayList<Article> getDocuments() throws SolrServerException, IOException
    {
        ArrayList<Article> listOfDocuments = new ArrayList<>();
        long numberOfDocs = getNumberOfDocuments();
        for (long documentId = 1; documentId <= numberOfDocs; ++documentId)
        {
            listOfDocuments.add(getDocumentById(documentId));
        }
        return listOfDocuments;
    }
    private SolrInputDocument createDocument(
            Article article
    ) throws IOException
    {
        SolrInputDocument newDocument = new SolrInputDocument();
        
        newDocument.addField("id", article.id);
        newDocument.addField("title", article.title);
        newDocument.addField("sourse", article.sourse);
        newDocument.addField("text", article.text);
        newDocument.addField("response", article.response);
        newDocument.addField("eventId", article.eventId);        
        
        return newDocument;
    }

        
    private SolrInputDocument createEvent(
            Event article 
    ) throws IOException
    {
        SolrInputDocument newDocument = new SolrInputDocument();
        
        newDocument.addField("id", article.id);
        newDocument.addField("title", article.title);
        newDocument.addField("Date", article.date);
        newDocument.addField("description", article.description);
        
        return newDocument;
    }

    private long getNumberOfEvents() throws SolrServerException, IOException
    {
        SolrQuery query = new SolrQuery();
        query.setQuery( "*" );
   
        QueryResponse response = clientEventSolr.query(query);
        
        SolrDocumentList listOfDocuments = response.getResults();
        return listOfDocuments.getNumFound();
    }

    public Event getEventById(long id) throws SolrServerException, IOException
    {
        SolrDocument document = clientEventSolr.getById(Long.toString(id));
        return new Event(
                id,                
                document.getFieldValue("description").toString(),
                document.getFieldValue("title").toString(),
                document.getFieldValue("Date").toString()
        );
    }
    public ArrayList<Event> getEvents() throws SolrServerException, IOException 
    {
        ArrayList<Event> events = new ArrayList<>();
        long numberOfEvents = getNumberOfEvents();
                
        for (long documentId = 1; documentId <= numberOfEvents; ++documentId)
        {
            events.add(getEventById(documentId));
        }
        return events;
    }
}
