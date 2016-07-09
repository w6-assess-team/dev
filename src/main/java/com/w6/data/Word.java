package com.w6.data;

public class Word {
    
    private final String content;
    private final Integer position;
    private final CollectionOfWords collection;
    private final String tag;

    public Word(String content, String tag) {
        this.content = content;
        this.tag = tag;
        this.position = 0;
        this.collection = null;
    }
    
    public Word(String content,int position, CollectionOfWords collection)
    {
        this.content = content;
        this.position = position;
        this.collection = collection;
        this.tag = "";
    }
    
    public Integer getPosition()
    {
        return position;
    }
    
    public String getValue()
    {
        return content;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        
        Word word = (Word)object;
        
        if(word.content.equals(this.collection) && word.position.equals(this.position))
        {
            return true;
        }
        
        return false;
    }
}
