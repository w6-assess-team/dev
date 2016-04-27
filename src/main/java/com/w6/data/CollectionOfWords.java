package com.w6.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionOfWords {
    
    private Set<Word> collection;
    private String tag;
    
    public CollectionOfWords(String tag)
    {
        this.collection = new HashSet<>();
        this.tag = tag;
    }
    
    public boolean addNewWord(Word word)
    {
        return collection.add(word);
    }
    
    public List<Word> getAllWords()
    {
        List<Word> listOfWords = new ArrayList<Word>();
        listOfWords.addAll(collection);
        return listOfWords;
    }
    
    public String getCollocationFromCollection()
    {
        StringBuilder collocation = new StringBuilder();
        
        return collocation.toString();
    }
    
    public String getTagOfCollection()
    {
        return tag;
    }
    
    public boolean isWordInCollection(String word, int position)
    {
        return false;
    }
    
}
