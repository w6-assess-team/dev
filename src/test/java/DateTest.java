import com.w6.nlp.DateTimeParser;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;


public class DateTest {
    @Test
    public void testDate0() {
        List<String> res = DateTimeParser.parseDateAndDateFromString("I wend to school yesterday.");
        List<String> howItShouldBe = new ArrayList<String>();
        howItShouldBe.add("yesterday");
        assertTrue(res.equals(howItShouldBe));
    }
    
    @Test
    public void testDate1() {
        List<String> res = DateTimeParser.parseDateAndDateFromString("Who will meet you tomorrow?");
        List<String> howItShouldBe = new ArrayList<String>();
        howItShouldBe.add("tomorrow");
        assertTrue(res.equals(howItShouldBe));
    }
    
    @Test
    public void testDate2() {
        List<String> res = DateTimeParser.parseDateAndDateFromString("I am going home now.");
        List<String> howItShouldBe = new ArrayList<String>();
        howItShouldBe.add("now");
        assertTrue(res.equals(howItShouldBe));
    }
}
