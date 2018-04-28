import edu.qc.seclass.BuggyClass;
import org.junit.Test;

public class BuggyClassTestSC2 {

    private BuggyClass buggyClass = new BuggyClass();

    @Test
    public void testBugMeth2(){
        buggyClass.buggyMethod2(10, 5);
        buggyClass.buggyMethod2(5,10);
    }
}
