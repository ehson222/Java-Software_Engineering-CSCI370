package edu.qc.seclass;
/**
 * Class that tests methods from MyCustomString.java
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers2() {
        mycustomstring.setString("6ta5 1s the be5t game ever!");
        assertEquals(4, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers3() {
        mycustomstring.setString("B1ack h0le 5un, won't y0u com3");
        assertEquals(5, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers4() {
        mycustomstring.setString("th3 l3g3nd 0f Z3lda");
        assertEquals(5, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers5() {
        mycustomstring.setString("W0r1d 0f Warcra5f 1s th3 b3st gam3 3v3r!");
        assertEquals(10, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers6() {
        mycustomstring.setString("th15 1s th3 1a5t te15");
        assertEquals(6, mycustomstring.countNumbers());
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
        mycustomstring.setString("Not y3t 1mpl3m3nt3d");
        assertEquals("o 3 mlmn3", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
        mycustomstring.setString("t0p sp0t");
        assertEquals("tps0", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
        mycustomstring.setString("step on no pets");
        assertEquals("eonp", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
        mycustomstring.setString("step on no pets");
        assertEquals("spnoe", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
        mycustomstring.setString("step on no pets");
        assertEquals("p p", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
        mycustomstring.setString("Not y3t 1mpl3m3nt3d");
        assertEquals("  ln", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("' 3trptsm 11si hs5rn,rgt", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
        mycustomstring.setString("The qu1ck br0wn f0x jumps ov3r th3 laz3 do6");
        assertEquals("h uc rw 0 up vrt3lz o", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
        mycustomstring.setString("The qu1ck br0wn f0x jumps ov3r th3 laz3 do6");
        assertEquals("eukrn0jporhl3o", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
        mycustomstring.setString("The qu1ck br0wn f0x jumps ov3r th3 laz3 do6");
        assertEquals("h uc rw 0 up vrt3lz o", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
        mycustomstring.setString("The qu1ck br0wn f0x jumps ov3r th3 laz3 do6");
        assertEquals("hqcbwf m 3t zd", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
        mycustomstring.setString("The qu1ck br0wn f0x jumps ov3r th3 laz3 do6");
        assertEquals(" cr  pvtl ", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));
    }

    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString("Th3 qu1ck br0wn f0x jumps 0v3r th3 lazy d0g");
        mycustomstring.convertDigitsToNamesInSubstring(3, 20);
        assertEquals("ThThree quOneck brZerown fZerox jumps 0v3r th3 lazy d0g", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring3()
    {
        mycustomstring.setString("Th3 qu1ck br0wn f0x jumps 0v3r th3 lazy d0g");
        mycustomstring.convertDigitsToNamesInSubstring(20, 38);
        assertEquals("Th3 qu1ck br0wn f0x jumps ZerovThreer thThree lazy d0g", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring4() {
        mycustomstring.setString("Th3 qu1ck br0wn f0x jumps 0v3r th3 lazy d0g");
        mycustomstring.convertDigitsToNamesInSubstring(11, 38);
        assertEquals("Th3 qu1ck brZerown fZerox jumps ZerovThreer thThree lazy d0g", mycustomstring.getString());

    }

    @Test
    public void testConvertDigitsToNamesInSubstring5() {
        mycustomstring.setString("Th3 qu1ck br0wn f0x jumps 0v3r th3 lazy d0g");
        mycustomstring.convertDigitsToNamesInSubstring(12, 29);
        assertEquals("Th3 qu1ck brZerown fZerox jumps ZerovThreer th3 lazy d0g", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring6() {
        mycustomstring.setString("Th3 qu1ck br0wn f0x jumps 0v3r th3 lazy d0g");
        mycustomstring.convertDigitsToNamesInSubstring(7, 12);
        assertEquals("Th3 quOneck br0wn f0x jumps 0v3r th3 lazy d0g", mycustomstring.getString());
    }

    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring7() {
        mycustomstring.setString("Th3 qu1ck br0wn f0x jumps 0v3r th3 lazy d0g");
        mycustomstring.convertDigitsToNamesInSubstring(-20, 20);
       // assertEquals("Th3 qu1ck br0wn fZerox jumps ZerovThreer thThree lazy d0g", mycustomstring.getString());
    }

    @Test (expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring8() {
        mycustomstring.setString("1 + 1 = 2");
        mycustomstring.convertDigitsToNamesInSubstring(11, 19);
        //assertEquals("ThThree quOneck brZerown fZerox jumps ZerovThreer thThree lazy dZerog", mycustomstring.getString());
    }

}
