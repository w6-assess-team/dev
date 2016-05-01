package com.w6.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionOfWords {
    
    private Set<Word> collection;
    private String tag;
    
    public CollectionOfWords()
    {
        this.collection = new HashSet<>();
    }
    
    public CollectionOfWords(String tag)
    {
        this();
        this.tag = tag;
    }
    
    public void setTag(String tag)
    {
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
    
    public String getCollectionAsString()
    {
        List<Word> words = new ArrayList(collection);
        Collections.sort(words, new ComparatorOfWords());
        return serialiseListOfWords(words);
        
    }
    
    private class ComparatorOfWords implements Comparator<Word>
    {
        @Override
        public int compare(Word lhs, Word rhs) {
            return lhs.getPosition().compareTo(rhs.getPosition());
        }    
    }
     
    private String serialiseListOfWords(List<Word> words)
    {
        StringBuilder collocation = new StringBuilder();
        
        words.forEach((word) -> {
            collocation.append(word.getValue());
            collocation.append(" ");
        });
       
        return collocation.toString();
    }
    
    public boolean hasThisCollection(CollectionOfWords collection)
    {
        return this.collection.containsAll(collection.collection);
    }
     
     
    
}
