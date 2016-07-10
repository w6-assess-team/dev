package com.w6.data;

public class Article 
{
    public String sourse;
    public String text;
    public String title;
    public Long id;

    public Article(Long id, String sourse, String text, String title) {
        this.id = id;
        this.sourse = sourse;
        this.text = text;
        this.title = title;
    }

    public Article(String sourse, String text, String title) {
        this.sourse = sourse;
        this.text = text;
        this.title = title;
    }

    
}
