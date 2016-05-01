
import com.w6.nlp.GetDoerAndVictim;
import com.w6.nlp.ViolentVerbsParser;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
    
/*

public class ObjectAndSubjectTest extends TestCase
{
    private ViolentVerbsParser violentVerbsParser;
    private  LexicalizedParser lp;
    private TokenizerFactory<CoreLabel> tokenizerFactory;
    
    @Override
    protected void setUp() throws IOException {
        lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
         tokenizerFactory =
                PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
        violentVerbsParser = new ViolentVerbsParser(lp);
        
    }
    
    @Test
    public void testSimpleSentence()
    {
        String sentence = "One boy killed a girl.";
        Tree parse = lp.apply(
                    tokenizerFactory.getTokenizer(new StringReader(sentence))
                        .tokenize()
        );
        
        List<String> violentVerbs = violentVerbsParser.getAllViolentVerbs(parse);
        ObjectsAndSubjects test = GetDoerAndVictim.getSubjectAndObjectOfViolence(parse, violentVerbs);
       
               
        ArrayList<String> correctSubj = new ArrayList<>();
        correctSubj.add("One boy ");
        
        ArrayList<String> correctObj = new ArrayList<>();
        correctObj.add("a girl ");
        
       
        assertEquals(correctSubj, test.subjects);
        assertEquals(correctObj, test.objects);      
    }
    
    @Test
    public void testManySentences()
    {
        String sentences = "One boy killed a girl. The murder cut all his fingers. The dog was killed by murder.";
        ObjectsAndSubjects res = new ObjectsAndSubjects();
        Document doc = new Document(sentences);
        for(Sentence c : doc.sentences()){
            Tree parse = lp.apply(
                    tokenizerFactory.getTokenizer(new StringReader(c.text()))
                        .tokenize()
            );
            List<String> violentVerbs = violentVerbsParser.getAllViolentVerbs(parse);
            ObjectsAndSubjects test = GetDoerAndVictim.getSubjectAndObjectOfViolence(parse, violentVerbs);
            res.objects.addAll(test.objects);
            res.subjects.addAll(test.subjects);
        }
        
        ArrayList<String> correctSubj = new ArrayList<>(); 
        correctSubj.add("One boy "); 
        correctSubj.add("The murder "); 
        correctSubj.add("by murder ");
        
        ArrayList<String> correctObj = new ArrayList<>();
        correctObj.add("a girl ");
        correctObj.add("all his fingers ");
        correctObj.add("The dog ");
        
        assertEquals(correctSubj, res.subjects);
        assertEquals(correctObj, res.objects);
    }
    
    @Test
    public void testArticle()
    {
        String article = "Gunmen kidnapped two foreign aid workers in Sudan - a French national "
                + "and a Canadian - as relief work becomes increasingly dangerous in the war-torn region, officials said on Sunday. "
                + "Two Sudanese staff of AMI were also kidnapped and later released, a local official said.";
        ObjectsAndSubjects res = new ObjectsAndSubjects();
        Document doc = new Document(article);
        for(Sentence c : doc.sentences()){
            Tree parse = lp.apply(
                    tokenizerFactory.getTokenizer(new StringReader(c.text()))
                        .tokenize()
            );
            List<String> violentVerbs = violentVerbsParser.getAllViolentVerbs(parse);
            ObjectsAndSubjects test = GetDoerAndVictim.getSubjectAndObjectOfViolence(parse, violentVerbs);
            res.objects.addAll(test.objects);
            res.subjects.addAll(test.subjects);
        }
        
        ArrayList<String> correctSubj = new ArrayList<>(); 
        correctSubj.add("Gunmen ");
        
        ArrayList<String> correctObj = new ArrayList<>();
        correctObj.add("two foreign aid workers in Sudan a French national and a Canadian ");
        correctObj.add("Two Sudanese staff of AMI ");
        
        assertEquals(correctSubj, res.subjects);
        assertEquals(correctObj, res.objects);
    }

}


*/