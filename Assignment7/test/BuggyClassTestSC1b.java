import edu.qc.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Test;

public class BuggyClassTestSC1b {
    BuggyClass buggyClass;

    @Test (expected = NullPointerException.class)
    public void testBugMeth1(){
        buggyClass.buggyMethod1(3, 2);
        buggyClass.buggyMethod1(3, 0);
    }
}
