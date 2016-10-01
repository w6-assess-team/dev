package com.w6.data;

public class Email {
    public long id;
    public String subject;
    public String  date;
    public String from;
    public String text;
    public Boolean used;
    //todo: add boolean "used"
    
    public Email(long id, String date, String subject, 
             String text, String from, Boolean used) 
    {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.text = text;
        this.from = from;
        this.used = used;
    }
    
}
