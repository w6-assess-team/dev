import com.w6.nlp.ViolentVerbsParser;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class ViolentVerbsTest {
    @Test
    public void testNoViolentVerbs() {
        List<String> res = ViolentVerbsParser.getAllViolentVerbs("I went to school.");
        List<String> correct = new ArrayList<String>();
        assertTrue(res.equals(correct));
    }

    @Test
    public void testBurnAndKill(){
        List<String> res = ViolentVerbsParser
                .getAllViolentVerbs("Killer burnt all the houses and killed all the hosts");
        List<String> correct = new ArrayList<String>();
        correct.add("burnt");
        correct.add("killed");
        assertTrue(res.equals(correct));
    }

    @Test
    public void testCut(){
        List<String> res = ViolentVerbsParser
                .getAllViolentVerbs("The murder cut all his fingers.");
        List<String> correct = new ArrayList<String>();
        correct.add("cut");
        assertTrue(res.equals(correct));
    }
}
