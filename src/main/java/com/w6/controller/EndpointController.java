package com.w6.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.w6.nlp.Parser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.w6.data.Message;
import com.w6.data.Response;


@Controller
public class EndpointController {

    Integer count = 0;
    protected static final String INDEX_VIEW = "index";
    
//    @Autowired
//    private SimpMessageSendingOperations messenger;
    private static final Gson gson = new GsonBuilder().create();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String display() 
    {
        return INDEX_VIEW;
    }
    
    @RequestMapping(value = "/rest-call/{name}", method = RequestMethod.POST)
    @ResponseBody
    public Message restCall(@PathVariable("name") String name) 
    {
        return new Message(gson.toJson(new Parser().generateResponse(name)));//Todo:use native serializator
    }
}
