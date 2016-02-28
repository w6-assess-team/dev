package com.w6.nlp;

import edu.stanford.nlp.simple.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class DateTimeParser {

    
    public static List<String> parseDateAndTimeFromString(@NotNull String text) {
    
        List<String> dates = new ArrayList<String>();
        Document doc = new Document(text);
        for (Sentence sent : doc.sentences()) {
            List<String> words = sent.lemmas();
            List<String> tags = sent.nerTags();
            for (int i = 0; i < tags.size(); ++i) {
                if (tags.get(i).equals("DATE") || tags.get(i).equals("TIME")) {
                    dates.add(words.get(i));
                }
            }
        }
        return dates;
    }
}