import edu.stanford.nlp.simple.*;

import java.util.ArrayList;
import java.util.List;

/**
 *Class to parse locations
 */

public class LocationParser {

    /**
     * String location parser
     * @param text  This is the text to parse
     * @return  arraylist of locations
     */

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