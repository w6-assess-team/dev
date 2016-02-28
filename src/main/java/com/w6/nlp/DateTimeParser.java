/**
 * Created by arseny on 25.02.16.
 */
import edu.stanford.nlp.simple.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to parse date.
 */

public class DateTimeParser {
    /**
     * String date parser.
     * @param text    This is the text to parse.
     */

    public static List<String> parseDateAndDateFromString(String text) throws Exception {
        if(text != null) {
            List<String> dates = new ArrayList<String>();
            Document doc = new Document(text);
            for (Sentence sent : doc.sentences()) {
                List<String> words = sent.lemmas();
                List<String> tags = sent.nerTags();
                for (int i = 0; i < tags.size(); ++i) {
                    if (tags.get(i).equals("DATE")) {
                        dates.add(words.get(i));
                    }
                }
            }
            return dates;
        } else {
            throw new Exception("text is empty!");
        }
    }
}