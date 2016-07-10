package com.w6.nlp;

import java.io.IOException;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class MySolrClient 
{
    
    final private String client = "user";
    final private String password = "220895";
    final private String url = "http://" + client + ":" + password + "@" +"localhost:8983/solr/core/";   
    final private SolrClient clientSolr;
    
    public MySolrClient()
    {
        clientSolr = new HttpSolrClient(url);
    }
    
    public void uploadDataToSolr(final String input) throws IOException, SolrServerException
    {
        long maxIndex = getNumberOfDocuments() + 1;
        clientSolr.add(createDocument(maxIndex, input));
        clientSolr.commit();
    }
    
    public String getDocumentById(int id) throws SolrServerException, IOException
    {
        SolrDocument document = clientSolr.getById(Integer.toString(id));
        return document.getFieldValue("text").toString();
    }
    
    private long getNumberOfDocuments() throws SolrServerException, IOException
    {
        SolrQuery query = new SolrQuery();
        query.setQuery( "*" );
        
        QueryResponse response = clientSolr.query(query);
        
        SolrDocumentList listOfDocuments = response.getResults();
        return listOfDocuments.getNumFound();
    }
    
    private SolrInputDocument createDocument(long maxIndex, String text)
    {
        SolrInputDocument newDocument = new SolrInputDocument();
        
        newDocument.addField("id", maxIndex);
        newDocument.addField("name", "Sourse " + maxIndex);
        newDocument.addField("text", text);
        
        return newDocument;
    }
}
