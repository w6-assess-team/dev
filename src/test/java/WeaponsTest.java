
import com.w6.nlp.ViolentVerbsParser;
import com.w6.nlp.WeaponsParser;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
/*
public class WeaponsTest extends TestCase{
    private WeaponsParser parser;
    
    @Override
    protected void setUp() throws IOException 
    {
        parser = new WeaponsParser(LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz"));
    }
    
    @Test
    public void testGun()
    {
        List<String> res = parser.getAllWeapons("Boy dropped his gun.");
        List<String> correct = new ArrayList<String>(); 
        correct.add("gun");
        assertTrue(res.equals(correct));
    }
    
}
*/