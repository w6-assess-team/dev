package com.w6.data;

public class Word {
    private final String content;
    private final int position;
    private final CollectionOfWords collection;
    private final String tag;

    public Word(String content, String tag) {
        this.content = content;
        this.tag = tag;
        this.position = 0;
        this.collection = null;
    }
}
