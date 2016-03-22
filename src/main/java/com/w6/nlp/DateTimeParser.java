package com.w6.nlp;

import edu.stanford.nlp.simple.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class DateTimeParser {

    
    public static List<String> parseDateAndTimeFromString(
            Sentence sentense,
            Collection<String> targetTags) 
    {
    
        List<String> dates = new ArrayList<String>();
        List<String> words = sentense.lemmas();
        List<String> tags = sentense.nerTags();
        for (int i = 0; i < tags.size(); ++i) 
        {
            if (targetTags.contains(tags.get(i))) 
            {
                dates.add(words.get(i));
            }
        }
        return dates;
    }
}