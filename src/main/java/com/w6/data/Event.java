/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.w6.data;

import java.util.Date;

public class Event {
    public long id;
    //todo: make it date
    public String date;
    public String title; 
    public String description; 

    public Event(long id, String date, String title, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
    }
}
