import edu.qc.seclass.BuggyClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuggyClassTestBC2 {

    BuggyClass buggyClass;

    @Test(expected = NullPointerException.class)
    public void testBugMeth2(){

        assertEquals(2, buggyClass.buggyMethod2(10, 5), 0);
        assertEquals(0.5, buggyClass.buggyMethod2(5, 10), 0);
        assertEquals(NullPointerException.class, buggyClass.buggyMethod2(0, 0));
        //buggyClass.buggyMethod2(0, 0);
    }
}
