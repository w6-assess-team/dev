import com.w6.data.ObjectsAndSubjects;
import com.w6.nlp.GetDoerAndVictim;
import com.w6.nlp.LocationParser;
import com.w6.nlp.ViolentVerbsParser;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import java.io.IOException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import static org.junit.Assert.*;
    /*

public class ObjectAndSubjectTest extends TestCase
{
    private ViolentVerbsParser violentVerbsParser;
    
    @Override
    protected void setUp() throws IOException {
        violentVerbsParser = new ViolentVerbsParser(LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz"));
    }
    @Test
    public void testSimpleSentence()
    {
        String sentence = "One boy killed a girl.";
        List<String> violentVerbs = violentVerbsParser.getAllViolentVerbs(sentence);
        ObjectsAndSubjects test = GetDoerAndVictim.getSubjectAndObjectOfViolence(sentence, violentVerbs);
        ArrayList<String> correctSubj = new ArrayList<>(); 
        correctSubj.add("boy");
        ArrayList<String> correctObj = new ArrayList<>();
        correctObj.add("girl");
        assertTrue(correctSubj.equals(test.subjects) && correctObj.equals(test.objects));
        
    }
    
    @Test
    public void testManySentences()
    {
        String sentences = "One boy killed a girl. The murder cut all his fingers. The dog was killed by murder.";
        List<String> violentVerbs = violentVerbsParser.getAllViolentVerbs(sentences);
        ObjectsAndSubjects test = GetDoerAndVictim.getSubjectAndObjectOfViolence(sentences, violentVerbs);
        
        ArrayList<String> correctSubj = new ArrayList<>(); 
        correctSubj.add("boy"); 
        correctSubj.add("murder"); 
        correctSubj.add("murder");
        
        ArrayList<String> correctObj = new ArrayList<>();
        correctObj.add("girl");
        correctObj.add("fingers");
        correctObj.add("dog");
        
        assertTrue(correctSubj.equals(test.subjects) && correctObj.equals(test.objects));
    }
    
    @Test
    public void testArticle()
    {
        String article = "Gunmen kidnapped two foreign aid workers in Sudan - a French national "
                + "and a Canadian - as relief work becomes increasingly dangerous in the war-torn region, officials said on Sunday. "
                + "Two Sudanese staff of AMI were also kidnapped and later released, a local official said.";
        List<String> violentVerbs = violentVerbsParser.getAllViolentVerbs(article);
        ObjectsAndSubjects test = GetDoerAndVictim.getSubjectAndObjectOfViolence(article, violentVerbs);
        
        ArrayList<String> correctSubj = new ArrayList<>(); 
        correctSubj.add("Gunmen");
        
        ArrayList<String> correctObj = new ArrayList<>();
        correctObj.add("workers");
        correctObj.add("staff");
        
        System.out.println(test.subjects.toString());
        assertTrue(correctSubj.equals(test.subjects) && correctObj.equals(test.objects));
    }
}
*/
