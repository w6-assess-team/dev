package com.w6.nlp;

import edu.stanford.nlp.ling.WordTag;
import edu.stanford.nlp.process.Morphology;
import edu.stanford.nlp.trees.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountryParser {

    private Set<String> countries;
    private final String globalPath = "/countries.txt";

    public CountryParser() throws IOException {
        countries = new HashSet<String>();
        InputStream in = this.getClass().getResourceAsStream(globalPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str;
        while ((str = br.readLine()) != null) {
            str = str.replaceAll("\\s+", "");
            countries.add(str);
        }
    }

    public List<String> getAllCountries(Tree tree) {
        List<String> result = new ArrayList<>();

        tree.getLeaves().forEach((leave) -> {
            Tree parent = leave.parent(tree);
            if (parent != null) {

                WordTag tag = Morphology.stemStatic(
                        leave.label().value(),
                        parent.label().value()
                );

                if (countries.contains(tag.value())) {
                    result.add(leave.label().value());
                }
            }
        });

        return result;
    }
}
