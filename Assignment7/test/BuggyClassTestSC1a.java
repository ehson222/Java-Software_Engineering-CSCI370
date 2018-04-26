import edu.qc.seclass.BuggyClass;
import org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC1a {

    private BuggyClass buggyClass = new BuggyClass();

    @Test
    public void testBugMeth1(){
        buggyClass.buggyMethod1(10, 5);
        buggyClass.buggyMethod1(5, 10);
        buggyClass.buggyMethod1(10, -5);
        buggyClass.buggyMethod1(-10, 5);
        buggyClass.buggyMethod1(-10, 5);
        buggyClass.buggyMethod1(0, 5);

        //buggyClass.buggyMethod1(0, -3);

        //assertEquals(5, buggyClass.buggyMethod1(10, 5), 5);
        //assertEquals(2, buggyClass.buggyMethod1(5, 10), 5/10);

    }

    @Test
    public void testBugMeth2(){
        buggyClass.buggyMethod2(10, 5);
        buggyClass.buggyMethod2(5,10);
    }



}
