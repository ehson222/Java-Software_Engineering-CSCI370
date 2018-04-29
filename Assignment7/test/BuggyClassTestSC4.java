import edu.qc.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BuggyClassTestSC4 {

    BuggyClass buggyClass = new BuggyClass();

    @Test (expected = AssertionError.class)
    public void testBugMeth(){

        //100% statement coverage reveals fault

        assertEquals(5.0, buggyClass.buggyMethod4(true, true), 0.0);
        assertEquals("reveals fault", buggyClass.buggyMethod4(false, false), 0);
        assertEquals(5.0, buggyClass.buggyMethod4(true, false), 0);
        assertEquals("Do Nothing", buggyClass.buggyMethod4(false, true), 0);

    }
}
