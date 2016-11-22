package com.w6.data;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "core")
public class Article {

    @Field
    private long id;

    @Field
    private String source;

    @Field
    private String text;

    @Field
    private String title;

    @Field
    private String response;

    @Field
    private long eventId;

    public Article() {
    }

    public Article(long id, String source, String text, String title, String response, long eventId) {
        this.id = id;
        this.source = source;
        this.text = text;
        this.title = title;
        this.response = response;
        this.eventId = eventId;
    }

    public long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getResponse() {
        return response;
    }

    public long getEventId() {
        return eventId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
