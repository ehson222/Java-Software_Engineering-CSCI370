import edu.qc.seclass.BuggyClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestSC2 {

    private BuggyClass buggyClass = new BuggyClass();

    @Test
    public void testBugMeth2(){
        assertEquals(2, buggyClass.buggyMethod2(10, 5), 0);
        assertEquals(0.5, buggyClass.buggyMethod2(5, 10), 0);

//        buggyClass.buggyMethod2(10, 5);
//        buggyClass.buggyMethod2(5,10);
    }
}
