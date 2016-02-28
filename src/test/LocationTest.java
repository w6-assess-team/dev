import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testLocation() {
		List<String> res = LocationParser.parseLocationFromString("World powers have agreed to seek" +
				" a nationwide \"cessation of hostilities\" in Syria to begin in a week's time, after talks in Munich, Germany.");
		List<String> correct= new ArrayList<String>();
		correct.add("Syria");
		correct.add("Munich");
		correct.add("Germany");
		assertTrue(res.equals(correct));
    }

}
