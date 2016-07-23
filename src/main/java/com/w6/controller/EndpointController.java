package com.w6.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.w6.data.Article;
import com.w6.data.Event;
import com.w6.nlp.Parser;
import com.w6.nlp.MySolrClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EndpointController {
    protected static final String INPUT_VIEW = "input";
    protected static final String W6_VIEW = "w6";
    protected static final String UPLOAD_VIEW = "upload";
    protected static final String QUERY_VIEW = "query";
    protected static final String DOCUMENTS_BY_EVENT_VIEW = "articlesOfEvent";
    protected static final String REPORT_VIEW = "report";

    protected MySolrClient solrClient = new MySolrClient();
    
    
    private static final Gson gson = new GsonBuilder().create();
    
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public ModelAndView post(
            @RequestParam("sourse") String sourse,
            @RequestParam("title") String title,           
            @RequestParam("text") String text
            ) throws IOException
    {
        try {
            Article article = new Article(new Long(-1), sourse, text, title, 
                    "",
                    -1
            );
            article.response = gson.toJson(new Parser().generateResponse(article));
            solrClient.uploadDataToSolr(article);
        } catch (SolrServerException ex) {
            Logger.getLogger(EndpointController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView(UPLOAD_VIEW);
    }

    @RequestMapping(value = "parse", method = RequestMethod.GET)
    public ModelAndView parse(@RequestParam("id") int docId) throws IOException
    {
        Article text;
        try { 
            text = solrClient.getDocumentById(docId);
            ModelAndView modelAndView = new ModelAndView(W6_VIEW);
            modelAndView.addObject("response", gson.toJson(new Parser().generateResponse(text)));
            modelAndView.addObject("events", gson.toJson(solrClient.getEvents()));
            modelAndView.addObject("id", docId);
            
            return modelAndView;
        } catch (SolrServerException ex) {
            Logger.getLogger(EndpointController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView(W6_VIEW);
        }
        
    }
    @RequestMapping(value = "parse", method = RequestMethod.POST)
    public ModelAndView update(
            @RequestParam("id") long docId,    
            @RequestParam("event_select") long eventId,                
            @RequestParam("title") String title,
            @RequestParam("date") String date
    ) throws IOException, SolrServerException
    {
        if (eventId == -1)
        {
            Event event = new Event(
                    -1,
                    date,
                    title,
                    "Something happened"
            );
            eventId = solrClient.uploadEventToSolr(event);
        }
        
        Article document = solrClient.getDocumentById(docId);
        document.eventId = eventId;
        solrClient.uploadDataToSolr(document);
        return new ModelAndView(W6_VIEW);
        
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayInput() 
    {
        return INPUT_VIEW;
    }
    
    @RequestMapping(value = "/events/view", method = RequestMethod.POST)
    public ModelAndView displayDocumentsByEvent(@RequestParam("id") int docId) 
            throws IOException
    {
        ModelAndView modelAndView = new ModelAndView(DOCUMENTS_BY_EVENT_VIEW);
        try {
           modelAndView.addObject("event",gson.toJson(solrClient.getEventById(docId))); 
           modelAndView.addObject("docList",gson.toJson(solrClient.getArticlesByEventId(docId)));
        } catch (SolrServerException e) {
             Logger.getLogger(EndpointController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return modelAndView;
    }
    
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView parse() throws IOException
    {
        ArrayList<Article> text;
        try { 
            text = solrClient.getDocuments();
            ModelAndView modelAndView = new ModelAndView(QUERY_VIEW);
            modelAndView.addObject("response", gson.toJson(text));
            return modelAndView;
        } catch (SolrServerException ex) {
            Logger.getLogger(EndpointController.class.getName()).log(Level.SEVERE, null, ex);
            return new ModelAndView(W6_VIEW);
        }
    }
    
    @RequestMapping(value = "report", method = RequestMethod.GET)
    public ModelAndView report(@RequestParam("month") String month) throws IOException, SolrServerException
    {
        ModelAndView modelAndView = new ModelAndView(REPORT_VIEW);
        ArrayList<Event> eventsInRange = solrClient.getEventsInRange(month.concat("-01"), month.concat("-31"));
        modelAndView.addObject("events", eventsInRange);
        return modelAndView;
    }  
    
}
