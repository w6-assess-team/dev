package com.w6.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.w6.nlp.Parser;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


public class EndpointController {
    protected static final String INPUT_VIEW = "input";
    protected static final String W6_VIEW = "w6";
    
    
    private static final Gson gson = new GsonBuilder().create();

    @RequestMapping(value = "parse", method = RequestMethod.POST)
    public ModelAndView parse(@RequestParam("text") String text) throws IOException
    {
        ModelAndView modelAndView = new ModelAndView(W6_VIEW);
        modelAndView.addObject("response", gson.toJson(new Parser().generateResponse(text)));
        
        return modelAndView;
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayInput() 
    {
        return INPUT_VIEW;
    }
}
