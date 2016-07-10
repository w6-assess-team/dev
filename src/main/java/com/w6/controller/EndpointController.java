package com.w6.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.w6.data.Article;
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
            solrClient.uploadDataToSolr(new Article(sourse, text, title));
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
            @RequestParam("id") int docId,            
            @RequestParam("title") String title,
            @RequestParam("date") String date
    ) throws IOException
    {
        /*Article text;
        try { 
            text = solrClient.getDocumentById(docId);
            ModelAndView modelAndView = new ModelAndView(W6_VIEW);
            modelAndView.addObject("response", gson.toJson(new Parser().generateResponse(text)));
            modelAndView.addObject("events", gson.toJson(solrClient.getEvents()));
            return modelAndView;
        } catch (SolrServerException ex) {
            Logger.getLogger(EndpointController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        System.err.println(docId);
        System.err.println(title);
        System.err.println(date);
        return new ModelAndView(W6_VIEW);
        
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayInput() 
    {
        return INPUT_VIEW;
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
    
}
