package com.w6.data;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "events")
public class Event {
    @Field
    private long id;

    //todo: make it date
    @Field
    private String date;

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private String region;

    @Field
    private String country;

    public Event() {
    }

    public Event(long id, String date, String title, String description, String region, String country) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.region = region;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
