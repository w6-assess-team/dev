import com.w6.nlp.LocationParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/*
public class LocationTest {

    @Test
    public void testCityAndCountry() {
        List<String> res = LocationParser.parseLocationFromString("World powers have agreed to seek" +
                        " a nationwide \"cessation of hostilities\" in Syria to begin in a week's time, after talks in Munich, Germany.");
        List<String> correct= new ArrayList<String>();
        correct.add("Syria");
        correct.add("Munich");
        correct.add("Germany");
        assertTrue(res.equals(correct));
    }
    
    @Test
    public void testCountry() {
        List<String> res = LocationParser.parseLocationFromString("It was in Russia.");
        List<String> correct= new ArrayList<String>();
        correct.add("Russia");
        assertTrue(res.equals(correct));
    }
    
    @Test
    public void testComplexCity() {
        List<String> res = LocationParser.parseLocationFromString("Hellow to you from New York!");
        List<String> correct= new ArrayList<String>();
        correct.add("New"); correct.add("York");
        assertTrue(res.equals(correct));
    }

}
*/