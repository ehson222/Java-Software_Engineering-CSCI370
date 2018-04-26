import edu.qc.seclass.BuggyClass;
import org.junit.Assert;
import org.junit.Test;

public class BuggyClassTestSC1a {

    private BuggyClass buggyClass = new BuggyClass();

    @Test
    public void testBugMeth1(){
        buggyClass.buggyMethod1(2, 3);  // a > 0 and b >0
        buggyClass.buggyMethod1(-2, -3); //a < 0 and b < 0
    }



}
