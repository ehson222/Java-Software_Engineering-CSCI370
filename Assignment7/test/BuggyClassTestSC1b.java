import edu.qc.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Test;

public class BuggyClassTestSC1b {
    BuggyClass buggyClass;

    @Test (expected = NullPointerException.class)
    public void testBugMeth1(){
        buggyClass.buggyMethod1(3, 0);
    }

    @Test (expected = NullPointerException.class)
    public void testBugMeth2(){
        buggyClass.buggyMethod2(10,5);
        buggyClass.buggyMethod2(5, 10);
        buggyClass.buggyMethod2(0, 0);
    }
}
