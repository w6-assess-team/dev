package com.w6.data;

public class Email {
    private long id;
    private String subject;
    private String  date;
    private String from;
    private String text;
    private Boolean used;

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

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }

    public Boolean getUsed() {
        return used;
    }

}
