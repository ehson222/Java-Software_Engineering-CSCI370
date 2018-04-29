import edu.qc.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BuggyClassTestSC1a {

    private BuggyClass buggyClass = new BuggyClass();

    @Test
    public void testBugMeth1(){
        //buggyClass.buggyMethod1(10, 5);

        //100% statement coverage, does not reveal fault
        assertEquals(2.0, buggyClass.buggyMethod1(10, 5), 0);
        assertEquals(0.5, buggyClass.buggyMethod1(5, 10), 0);
        assertEquals(-2.0, buggyClass.buggyMethod1(10, -5), 0);
        assertEquals(-0.5, buggyClass.buggyMethod1(-5, 10), 0);
        assertEquals(0.0, buggyClass.buggyMethod1(0, 5), 0);


        //buggyClass.buggyMethod1(5, 10);
        //buggyClass.buggyMethod1(10, -5);
        //buggyClass.buggyMethod1(-10, 5);
        //buggyClass.buggyMethod1(-10, 5);
        //buggyClass.buggyMethod1(0, 5);

        //buggyClass.buggyMethod1(0, -3);

        //assertEquals(5, buggyClass.buggyMethod1(10, 5), 5);
        //assertEquals(2, buggyClass.buggyMethod1(5, 10), 5/10);

    }





}
