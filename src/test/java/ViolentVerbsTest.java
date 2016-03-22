import com.w6.nlp.ViolentVerbsParser;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import static org.junit.Assert.*;
    /*
public class ViolentVerbsTest extends TestCase {

    private ViolentVerbsParser parser;
    @Override
    protected void setUp() throws IOException {
        parser = new ViolentVerbsParser(LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz"));
    }
    
    @Test
    public void testNoViolentVerbs() {
        List<String> res = parser.getAllViolentVerbs("I went to school.");
        List<String> correct = new ArrayList<String>();
        assertTrue(res.equals(correct));
    }

    @Test
    public void testBurnAndKill(){
        List<String> res = parser
                .getAllViolentVerbs("Killer burnt all the houses and killed all the hosts");
        List<String> correct = new ArrayList<String>();
        correct.add("burnt");
        correct.add("killed");
        assertTrue(res.equals(correct));
    }

    @Test
    public void testCut(){
        List<String> res = parser
                .getAllViolentVerbs("The murder cut all his fingers.");
        List<String> correct = new ArrayList<String>();
        correct.add("cut");
        assertTrue(res.equals(correct));
    }
    
    @Test
    public void testPassiveVoice(){
        List<String> res = parser
                .getAllViolentVerbs("The dog was killed by murder.");
        List<String> correct = new ArrayList<String>();
        correct.add("killed");
        assertTrue(res.equals(correct));
    }
}
*/
