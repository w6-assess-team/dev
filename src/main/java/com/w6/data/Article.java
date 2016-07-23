package com.w6.data;

public class Article 
{
    public String sourse;
    public String text;
    public String title;
    public long id;
    public String response;
    public long eventId;

    public Article(long id, String sourse, String text, String title, String response, long eventId) {
        this.id = id;
        this.sourse = sourse;
        this.text = text;
        this.title = title;
        this.response = response;
        this.eventId = eventId;
    }

    public Article(String sourse, String text, String title) {
        this.sourse = sourse;
        this.text = text;
        this.title = title;
    }
    
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
