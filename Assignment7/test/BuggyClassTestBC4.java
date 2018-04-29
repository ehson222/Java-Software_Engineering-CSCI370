import edu.qc.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BuggyClassTestBC4 {

    BuggyClass buggyClass;

    @Test (expected = NullPointerException.class)
    public void testBugMeth4(){

        // without expected, Output: 5.0
        assertEquals(5.0, buggyClass.buggyMethod4(true, true), 0.0);
        // without expected, Output: 5.0
        assertEquals(5.0, buggyClass.buggyMethod4(true, false), 0);
        // without expected, Output:  given values does not show output or error -- not reveal fault
        assertEquals("Values does not show output or error", buggyClass.buggyMethod4(false, true), 0);
        // without expected, Output:  No Output
    }

}


