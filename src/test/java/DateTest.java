import com.w6.nlp.DateTimeParser;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;


public class DateTest {
    @Test
    public void testDateYesterday() {
        List<String> res = DateTimeParser.parseDateAndTimeFromString("I went to school yesterday.");
        List<String> howItShouldBe = new ArrayList<String>();
        howItShouldBe.add("yesterday");
        assertEquals(res,howItShouldBe);
    }
    
    @Test
    public void testDateTomorrow() {
        List<String> res = DateTimeParser.parseDateAndTimeFromString("Who will meet you tomorrow?");
        List<String> howItShouldBe = new ArrayList<String>();
        howItShouldBe.add("tomorrow");
        assertEquals(res,howItShouldBe);
    }
    
    @Test
    public void testComplexDate() {
        List<String> res = DateTimeParser.parseDateAndTimeFromString("I will come back on 1st july of 2016.");
        List<String> howItShouldBe = new ArrayList<String>();
        howItShouldBe.add("1st");
        howItShouldBe.add("july");
        howItShouldBe.add("of");
        howItShouldBe.add("2016");
          
        assertEquals(res,howItShouldBe);
    }
}
