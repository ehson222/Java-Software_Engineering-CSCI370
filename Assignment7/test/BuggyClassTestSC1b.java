import edu.qc.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC1b {
    BuggyClass buggyClass;

    @Test (expected = NullPointerException.class)
    public void testBugMeth1(){

        assertEquals(2.0, buggyClass.buggyMethod1(10, 5), 0);
        assertEquals(0.5, buggyClass.buggyMethod1(5, 10), 0);
        assertEquals(-2.0, buggyClass.buggyMethod1(10, -5), 0);
        assertEquals(-0.5, buggyClass.buggyMethod1(-5, 10), 0);
        assertEquals(0.0, buggyClass.buggyMethod1(0, 5), 0);
        //division by zero
        buggyClass.buggyMethod1(3, 0);
    }


}
