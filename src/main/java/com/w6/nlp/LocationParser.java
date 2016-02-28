package com.w6.nlp;

import edu.stanford.nlp.simple.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class LocationParser {
    
    @NotNull
    public static List<String> parseLocationFromString(String text) {
        List<String> locations = new ArrayList<String>();
        Document doc = new Document(text);
        for (Sentence sent : doc.sentences()) {
            List<String> words = sent.lemmas();
            List<String> tags = sent.nerTags();
            for (int i = 0; i < tags.size(); ++i) {
                if (tags.get(i).equals("LOCATION")) {
                    locations.add(words.get(i));
                }
            }
        }
        return locations;
    }
}