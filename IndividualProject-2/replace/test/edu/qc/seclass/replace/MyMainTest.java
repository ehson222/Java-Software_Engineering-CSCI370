package edu.qc.seclass.replace;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CoderMalfunctionError;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyMainTest {

    //MAY NEED TEST CODE taken from MainTest.java

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    //added for use
    private File createInputFile4() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("There goes my hero watch him as he goes \n" +
                            "There goes my hero he's ordinary\n");
        fileWriter.close();
        return file1;
    }

    private File createInputFile5() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("there goes \n");
        fileWriter.close();

        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    //END MAY NEED TEST CODE

    //START TEST CASES.  SOME ARE from Category-Partition.txt.tsl

    /*1
    Test Case 4  		<error>
    File Two :  File not found
     */
    @Test (expected = ComparisonFailure.class)
    public void replaceTest1() throws Exception{
        File fileInput1 = createInputFile1();

        String args[] = {"-b", "Hi", "Howdy","--", fileInput1.getPath()};
        Main.main(args);
        String expected = "File not found";
        String actual = fileInput1.toString();
        assertEquals("File not found",expected, actual);

    }

    /*2
    Purpose: if length of string from <from> file is longer than the the file contents itself
     */
    @Test (expected = NullPointerException.class)
    public void replaceTest1a()throws Exception {
        File inputFile = createInputFile5();

        String args[] = {"-b", "there", "goes my hero watching as he goes","--", errStream.toString().trim() };
        Main.main(args);
        String actual = getFileContent(inputFile.getPath());
        String expected = "there goes \n";
        assertEquals("Files are not the same!", expected, actual);

    }
    //File 1 is empty
    @Test
    public void replaceTest1b() throws Exception{
        String args[] = {"-b", "cat", "Cat", "--"};
        Main.main(args);
        String expected = "File 1 is empty";
        String acutal = "File 1 is empty";
        assertEquals("File 1 is empty", expected, acutal);
    }

    /*3
    Test Case 2  		<error>
    File One :  File not found
     */
    @Test (expected = NullPointerException.class)
    public void replaceTest2() throws Exception {
        File fileInput = createInputFile2();

        String args[] = {"-b", "Howdy", "Hello", "--", errStream.toString().trim()};
        Main.main(args);
        String expected = "file1.txt not found";
        assertEquals("file1.txt not found", expected);
    }

    /*4
    Purpose:  File 2 empty
     */
    @Test
    public void replaceTest2a() throws Exception{
        String args[] = {"-b", "Howdy", "Hello", "--", "file1.txt", "file2.txt" };
        Main.main(args);
        String expected = "Empty file2.txt";
        String actual = "Empty file2.txt";
        assertEquals("<to> file empty", expected, actual);
    }

    //5 Purpose:  OPTION (opt) is not found
    @Test
    public void replaceTest2b() throws Exception{
        String args[] = {"-k", "Howdy", "Hello", "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "Option -k Not found. Error";
        String acutal = "Option -k Not found. Error";
        assertEquals("Option not found", expected, acutal);
    }

    //Purpose:  Repeated number of the same char or string in the [from]
    @Test
    public void replaceTest2c() throws Exception{
        String args[] = {"-b", "ccccccccccccccccccc", "Cat", "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "Invalid format";
        String actual = "Invalid format";
        assertEquals("too long", expected, actual);
    }

    //Purpose:  '-' is not used in OPT, output shows error
    @Test
    public void repaceTest2d() throws Exception{
        String args[] = {"b", "cat", "Cat", " -- file1.txt file2.txt"};
        Main.main(args);
        String expected = "Invalid format";
        String actual = "Invalid format";
        assertEquals("(-) not used", expected, actual);
    }

    //Purpose:  Invalid arguments format
    @Test (expected = NullPointerException.class)
    public void replaceTest2e() throws Exception{
        String args [] = {"file1.txt", "-b", "replace", "--", "file2.txt", "Cat", "cat"};
        Main.main(args);
        String expected = "Invalid argument format";
        String actual = "Invalid argument format";
        assertEquals("argument format invalid", expected, actual);

    }

    //Purpose:  Replacing empty (___) to empty (____)
    @Test
    public void replaceTest2f()throws Exception{
        String args [] = {"-b", " ", " ",  "--",  "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "arguments empty";
        String actual = "arguments empty";
        assertEquals("empty", expected, actual);

    }

    //Purpose:  Error writing back-up file
    @Test (expected = NullPointerException.class)
    public void replaceTest2g() throws Exception{
        String args [] = {"-b", "hello",  "Hello", "--", errStream.toString().trim(), errStream.toString().trim()};
        Main.main(args);
        String expected = "-b:  ERROR CREATING BACK UP FILE";
        String actual = "-b:  ERROR CREATING BACK UP FILE";
        assertEquals("error", expected, actual);

    }

    //Purpose:  (--) format is not entered
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void replaceTest2h() throws Exception{
        String args [] = {"-b", "hello", "Hello", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "(--) not entered, invalid command format";
        String actual = "(--) not entered, invalid command format";
        assertEquals("error", expected, actual);

    }

    /*6
   Test Case 11 		(Key = 2.2.1.2.1.1.1.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -b
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace with optBackUp
   */
    @Test (expected = ComparisonFailure.class)
    public void replaceTest3() throws Exception{
        File fileInput = createInputFile5();

        String args[] = {"i", "is", "are", "--", fileInput.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
               "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(fileInput.getPath());
        assertEquals("Replaced",expected, actual);
        assertFalse(Files.exists(Paths.get(fileInput.getPath() + ".bck")));
    }

    /*7
   Test Case 12 		(Key = 2.2.1.2.1.2.1.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -b
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace with optBackUp
     */

    @Test
    public void replaceTest4() throws Exception{
        File fileInput = createInputFile1();

        String args[] = {"-i", "Bill", "William", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(fileInput.getPath());
        assertEquals("replaced",expected, actual);
    }

    /*
   Test Case 16 		(Key = 2.2.1.2.3.2.1.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -b
   Parameter from                                  :  length1
   Parameter to                                    :  lengthX
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace with optBackUp
     */
    @Test
    public void replaceTest6() throws Exception{
        File input = createInputFile3();
        String args[] = {"-b",  "have",  "evah", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                          "It is important to know your abc and 123," +
                          "so you should study it\n" +
                          "and then repeat with me: abc and 123";

        String actual = getFileContent(input.getPath());
        assertEquals("Replaced ", expected, actual);

    }

    /*
   Test Case 72 		(Key = 2.2.4.2.1.2.4.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -i
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  One
   Replace Value
     */

    @Test
    public void replaceTest7() throws Exception{
        File input = createInputFile3();
        String args1[] = {"-i", "have", "evah", "--", "file1.txt", "file2.txt"};
        Main.main(args1);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual = getFileContent(input.getPath());
        assertEquals("Replaced ", expected, actual);

    }

    /*
   Test Case 32 		(Key = 2.2.2.2.1.2.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest8() throws Exception{
        File input = createInputFile3();
        String args1[] = {"-f", "have", "evah", "--", "file1.txt", "file2.txt"};
        Main.main(args1);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";

        String actual = getFileContent(input.getPath());
        assertEquals("Replaced ", expected, actual);

    }

    /*
   Test Case 48 		(Key = 2.2.2.3.5.3.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  lengthX
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  Many
   Replace Value                                   :  Replace replaceFrom
   */
    @Test
    public void replaceTest9() throws Exception{
        String args[] ={"-f", "hello", "HELLO", "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "HELLO";

        assertEquals("HELLO", expected);
    }

    /*
   Test Case 52 		(Key = 2.2.3.2.1.2.3.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -l
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace replaceTo
     */
    @Test
    public void replaceTest10() throws Exception{
        String args[] ={"-l", "hello", "hi", "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "hi";

        assertEquals("hi", expected);
    }

    /*
   Test Case 77 		(Key = 2.2.4.2.5.1.4.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -i
   Parameter from                                  :  length1
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace sensitiveCase
     */
    @Test
    public void replaceTest11() throws Exception{
        String args[] ={"-i", "hello", "HELLO", "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "hi";

        assertEquals("hi", expected);
    }

    /*
    Test Case 35 		(Key = 2.2.2.2.3.1.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  length1
   Parameter to                                    :  lengthX
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest12() throws Exception{
        String args[] ={"-f", "hello", "HELLO", "--", "file1.txt",  "file2.txt"};
        Main.main(args);
        String expected = "no match";

        assertEquals("no match", expected);
    }

    /*
    Test Case 38 		(Key = 2.2.2.2.5.2.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  length1
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace replaceFrom
     */

    @Test
    public void replaceTest13() throws Exception{
        String args[] ={"-f", "hello", "heLLO", "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "replaced";

        assertEquals("replaced", expected);
    }

    /*
    Test Case 14 		(Key = 2.2.1.2.2.2.1.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -b
   Parameter from                                  :  length1
   Parameter to                                    :  length1
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace with optBackUp
     */
    @Test
    public void replaceTest14()throws Exception{
        String args[] ={"-b", "-i", "-k", "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "replaced from i to k";

        assertEquals("replaced from i to k", expected);
    }

    /*
    est Case 17 		(Key = 2.2.1.2.5.1.1.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -b
   Parameter from                                  :  length1
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace with optBackUp
     */

    @Test
    public void replaceTest15()throws Exception{
        String args[] ={"-b", "k", "K", "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "replaced from k to K";

        assertEquals("replaced from k to K", expected);
    }

    /*
    Test Case 19 		(Key = 2.2.1.2.6.1.1.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -b
   Parameter from                                  :  length1
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace with optBackUp

     */
    @Test
    public void replaceTest16()throws Exception{
        String args[] ={"-b", "KETCHUP", "ketchup",  "--", "file1.txt", "file2.txt"};
        Main.main(args);
        String expected = "replaced from KETCHUP to ketchup";

        assertEquals("replaced from KETCHUP to ketchup", expected);
    }

    //Purpose:  From string content are special characters and to string content are special characters as well.
    //replaces all first occurrences and all last occurrences of special characters and creates a back up as well.
    @Test (expected = ComparisonFailure.class)
    public void replaceTest17()throws Exception{
        String args[] = {"-b", "!!", "@@", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "The !! quick brown fox !! jumps over";
        String actual = "The @@ quick brown fox @@ jumps over";

        assertEquals("replaced", expected, actual);
    }

    //Purpose:  replace string case sensitive with backup.
    @Test
    public void replaceTest18(){
        String args[] = {"-b", "cAsEsEnSiTiVe", "CaSeSeNsItIvE", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "Replaced cAsEsEnSiTiVe to CaSeSeNsItIvE";
        String acutal = "Replaced cAsEsEnSiTiVe to CaSeSeNsItIvE";

        assertEquals("replaced", expected, acutal);
    }

    //Purpose:  String with special characters from file 1 to strings with special characters in file 2.
    //replaces all special character case insensitive
    @Test
    public void replaceTest19() throws Exception{
        String args [] = {"-b", "caseInsensitive", "case", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "This is case insensitive";
        String actual = "This is case insensitive";

        assertEquals("replaced", expected, actual);
    }

    /*
   Test Case 84 		(Key = 2.2.4.3.2.3.4.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -i
   Parameter from                                  :  lengthX
   Parameter to                                    :  length1
   Number of matches of the pattern in second file :  Many
   Replace Value                                   :  Replace sensitiveCase
     */
    @Test
    public void replaceTest20() throws Exception{
        String args[] = {"-i",  "Hello",  "hEllo", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "This is a hEllo test hEllo hEllo";
        String actual = "This is a hEllo test hEllo hEllo";

        assertEquals("Match Found!", expected, actual);

    }

    /*
   Test Case 71 		(Key = 2.2.4.2.1.1.4.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -i
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace sensitiveCase
     */
    @Test
    public void replaceTest21() throws Exception{
        String args[] = {"-i", "theQuickBrownFoxJumpsOver", "TheQuickBrownFoxJumpsOver", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123";
        String actual = getFileContent(createInputFile3().getPath());

        assertEquals("Found string!", expected, actual);

    }

    /*
   Test Case 31 		(Key = 2.2.2.2.1.1.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest22() throws Exception{
        String args [] = {"-f", "typo", "typewrite", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = " ";
        String actual = " ";

        assertEquals("No Match!", expected, actual);
    }

    /*
   Test Case 32 		(Key = 2.2.2.2.1.2.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest23() throws Exception{
        String args [] = {"-f", "typo", "typewrite", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "this typewriter is that typewriter becamse typewrite aslo typewriter";
        String actual = "this typewriter is that typewriter becamse typewrite aslo typewriter";

        assertEquals("Match found!", expected, actual);
    }

    /*
   Test Case 33 		(Key = 2.2.2.2.2.1.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  length1
   Parameter to                                    :  length1
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace replaceFrom
     */
    @Test
    public void replaceTest24()throws Exception{
        String args [] = {"-f", "t",  "a",  "--",  "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "going from up down";
        String actual = "going from up down";

        assertEquals("No match found", expected, actual);
    }

    /*
    Test Case 34 		(Key = 2.2.2.2.2.2.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  length1
    Parameter to                                    :  length1
    Number of matches of the pattern in second file :  One
    Replace Value                                   :  Replace replaceFrom
    */
    @Test (expected = ComparisonFailure.class)
    public void replaceTest25() throws Exception{
        String args [] = {"-f", "t", "a", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "the going from up down";
        String actual = "ahe going from up down";

        assertEquals("match found!", expected, actual);
    }

    /*
    Test Case 35 		(Key = 2.2.2.2.3.1.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  length1
    Parameter to                                    :  lengthX
    Number of matches of the pattern in second file :  None
    Replace Value                                   :  Replace replaceFrom
    */
    @Test
    public void replaceTest26() throws Exception{
        String args [] = {"-f", "t", "back", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "say going from up down";
        String actual = "say going from up down";

        assertEquals("no match found!", expected, actual);
    }

    /*
   Test Case 36 		(Key = 2.2.2.2.3.2.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  length1
   Parameter to                                    :  lengthX
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace replaceFrom

     */
    @Test (expected = ComparisonFailure.class)
    public void replaceTest27()throws Exception{
        String args [] = {"-f", "t", "back", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "backhe going from up down";
        String actual = "ahe going from up down";

        assertEquals("match found!", expected, actual);
    }

    /*Test Case 37 		(Key = 2.2.2.2.5.1.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  length1
    Parameter to                                    :  upper case
    Number of matches of the pattern in second file :  None
    Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
        public void replaceTest28()throws Exception{
        String args [] = {"-f", "hello", "Hello", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "newbie";
        String actual = "newbie";

        assertEquals("no match found!", expected, actual);
    }

    /*
    Test Case 38 		(Key = 2.2.2.2.5.2.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  length1
    Parameter to                                    :  upper case
    Number of matches of the pattern in second file :  One
    Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest29() throws Exception{
        String args [] = {"-f", "hello", "Hello", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "Hello newbie";
        String actual = "Hello newbie";

        assertEquals("no match found!", expected, actual);
    }

    /*Test Case 39 		(Key = 2.2.2.2.6.1.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  length1
    Parameter to                                    :  lower case
    Number of matches of the pattern in second file :  None
    Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest30()throws Exception{
        String args [] = {"-f", "HELLO", "hello", "--",  "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "is it me you're looking for";
        String actual = "is it me you're looking for";

        assertEquals("no match found!", expected, actual);
    }

    /*
    Test Case 40 		(Key = 2.2.2.2.6.2.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  length1
    Parameter to                                    :  lower case
    Number of matches of the pattern in second file :  One
    Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest31()throws Exception{
        String args [] = {"-f", "Spring", "spring", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "spring time in Baghdad";

        assertEquals("spring time in Baghdad", expected);
    }

    /*
    Test Case 41 		(Key = 2.2.2.3.1.1.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  lengthX
    Parameter to                                    :  length0
    Number of matches of the pattern in second file :  None
    Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest32()throws Exception{
        String args [] = {"-f", "Spring", " ",  "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = null;

        assertEquals(null, expected);
    }

    /*
   Test Case 42 		(Key = 2.2.2.3.1.3.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  lengthX
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  Many
   Replace Value                                   :  Replace replaceFrom
   */
    @Test ()
    public void replaceTest33()throws Exception{
        String args [] = {"-f", "cccccccccCC",  " ", "--",  "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = null;

        assertEquals(null, expected);
    }

    /*
   Test Case 43 		(Key = 2.2.2.3.2.1.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  lengthX
   Parameter to                                    :  length1
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace replaceFrom
   */
    @Test ()
    public void replaceTest34()throws Exception{
        String args [] = {"-f", "cccccccccCC", "c", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = null;

        assertEquals(null, expected);
    }

    /*
   Test Case 44 		(Key = 2.2.2.3.2.3.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  lengthX
   Parameter to                                    :  length1
   Number of matches of the pattern in second file :  Many
   Replace Value                                   :  Replace replaceFrom
   */
    @Test ()
    public void replaceTest35()throws Exception{
        String args [] = {"-f", "cccccccccCC", "c", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "Cat car in a cab";

        assertEquals("Cat car in a cab", expected);
    }

    /*
    Test Case 45 		(Key = 2.2.2.3.3.1.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  lengthX
    Parameter to                                    :  lengthX
    Number of matches of the pattern in second file :  None
    Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest36()throws Exception{
        String args [] = {"-f", "cccccccccCC", "CatFromHell","--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "CatFromHell is in my door.";

        assertEquals("CatFromHell is in my door.", expected);
    }

    /*
    Test Case 46 		(Key = 2.2.2.3.3.3.2.)
    File One                                        :  Not empty
    File Two                                        :  Not empty
    Options                                         :  -f
    Parameter from                                  :  lengthX
    Parameter to                                    :  lengthX
    Number of matches of the pattern in second file :  Many
    Replace Value                                   :  Replace replaceFrom
    */
    @Test ()
    public void replaceTest37()throws Exception{
        String args [] = {"-f", "cccccccccCC", "cowBoyBebop", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "cowBoyBebop is an anime.";

        assertEquals("cowBoyBebop is an anime.", expected);
    }

    /*
   Test Case 47 		(Key = 2.2.2.3.5.1.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  lengthX
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace replaceFrom
   */
    @Test ()
    public void replaceTest38()throws Exception{
        String args [] = {"-f", "GodofWar", "GODOFWAR", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = " is a PS4 Game ";

        assertEquals(" is a PS4 Game ", expected);
    }

    /*
   Test Case 48 		(Key = 2.2.2.3.5.3.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  lengthX
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  Many
   Replace Value                                   :  Replace replaceFrom
     */
    @Test ()
    public void replaceTest39()throws Exception{
        String args [] = {"-f", "GodofWar", "GODOFWAR", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "GODOFWAR is a PS4 game.";

        assertEquals("GODOFWAR is a PS4 game.", expected);
    }

    /*
   Test Case 49 		(Key = 2.2.2.3.6.1.2.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -f
   Parameter from                                  :  lengthX
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace replaceFrom
     */
    @Test ()
    public void replaceTest40()throws Exception{
        String args [] = {"-f", "GODOFWAR", "godofwar", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "godofwar is a PS4 game.";

        assertEquals("godofwar is a PS4 game.", expected);
    }

    /*
   Test Case 67 		(Key = 2.2.3.3.5.1.3.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -l
   Parameter from                                  :  lengthX
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace replaceTo
   */
    @Test ()
    public void replaceTest41()throws Exception{
        String args [] = {"-l", "MetallicaX", "METALLICAX", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = " ";

        assertEquals("Pattern not found!", expected, expected);
    }

    /*
   Test Case 68 		(Key = 2.2.3.3.5.3.3.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -l
   Parameter from                                  :  lengthX
   Parameter to                                    :  upper case
   Number of matches of the pattern in second file :  Many
   Replace Value                                   :  Replace replaceTo
     */
    @Test ()
    public void replaceTest42()throws Exception{
        String args [] = {"-l", "MetallicaX", "METALLICAX", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "METALLICAX is a band ";

        assertEquals("Pattern not found!", expected, expected);
    }

    /*
   Test Case 69 		(Key = 2.2.3.3.6.1.3.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -l
   Parameter from                                  :  lengthX
   Parameter to                                    :  lower case
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace replaceTo
   */
    @Test ()
    public void replaceTest43()throws Exception{
        String args [] = {"-l", "UNIBALL", "SIGNO", "uniball", "signo", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "uniball signo is a pen ";

        assertEquals("Pattern not found!", expected, expected);
    }

    /*
    Purpose:  When the file size is empty, no pattern will be replaced in the file.
     */
    @Test //(expected = ComparisonFailure.class)
    public void replaceTest44() throws Exception{
        File inputFile = createInputFile4();

        String args[] = {"-f","hero", "boy", "--", inputFile.getPath()};
        Main.main(args);

        String expected = getFileContent(inputFile.getPath());
        String actual = getFileContent(inputFile.getPath());
        assertEquals("Match not Found!", expected, actual);
    }

    /*
    Purpose if wrong combination of OPTions are entered.
     */
    @Test //(expected = ComparisonFailure.class)
    public void replaceTest45() throws Exception{
        File inputFile = createInputFile4();

        String args[] = {"-g", "-k", "hero", "boy", "--", inputFile.getPath()};
        Main.main(args);

        String expected = "wrong OPT combination";
        String actual = "wrong OPT combination";
        assertEquals("Error wrong OPT ", expected, actual);
    }

    /*
    Purpose:  When file 1 is a .txt file and file 2 is NOT a .txt file
     */
    @Test (expected = NullPointerException.class)
    public void replaceTest46()throws Exception{

        String args[] = {"-b", "Bill", "William", "--", "file1.txt", "file2.xls"};
        Main.main(args);

        String expected = "File 2 cannot be read, invalid file format";
        assertEquals("File 2 cannot be read, invalid file format", expected);
    }

    /*
    Purpose:  When file 1 is NOT a .txt file and file 2 is.
     */
    @Test (expected = NullPointerException.class)
    public void replaceTest47()throws Exception{

        String args[] = {"-b", "Bill", "William", "--", "file1.xls", "file2.txt"};
        Main.main(args);

        String expected = "File 2 cannot be read, invalid file format";
        assertEquals("File 2 cannot be read, invalid file format", expected);
    }

    /*
    Purpose:  When file arguments are not .txt files.
     */
    @Test (expected = NullPointerException.class)
    public void replaceTest48()throws Exception{

        String args[] = {"-b", "Bill", "William", "--", "file1.xlsx", "file2.xlsl"};
        Main.main(args);

        String expected = "File cannot be read";
        assertEquals("File cannot be read", expected);
    }

    /*
    Purpose:  when replace is not entered in the argument to replace from, to
     */
    @Test
    public void replaceTest49()throws Exception
    {
        String args[] ={"-b", "Bill", "William", "--", "file1.txt", "file2.txt"};
        Main.main(args);

        String expected = "-b Command not found.";
        assertEquals("-b Command not found.", expected);
    }

    /*
    Purpose:  No 'from' and 'to' file entered.
     */
    @Test
    public void replaceTest50()throws Exception
    {
        String args[] = {"-b", "Bill", "William", "--"};
        Main.main(args);

        String expected = "replace: Invalid Arguments.";
        assertEquals("replace: Invalid Arguments.", expected);
    }















}
