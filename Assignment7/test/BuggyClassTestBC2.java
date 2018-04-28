import edu.qc.seclass.BuggyClass;
import org.junit.Test;

public class BuggyClassTestBC2 {

    BuggyClass buggyClass;

    @Test(expected = NullPointerException.class)
    public void testBugMeth2(){
        buggyClass.buggyMethod2(10,5);
        buggyClass.buggyMethod2(5, 10);
        buggyClass.buggyMethod2(0, 0);
    }
}
