package com.w6.data;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "email")
public class Email {

    @Field
    private long id;

    @Field
    private String subject;

    @Field
    private String  date;

    @Field
    private String from;

    @Field
    private String text;

    @Field
    private Boolean used;
    //todo: add boolean "used"


    public Email() {
    }

    public Email(long id, String date, String subject,
                 String text, String from, Boolean used) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.text = text;
        this.from = from;
        this.used = used;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
