package edu.qc.seclass.replace;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;

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

    //START TEST CASES some from Category-Partition.txt.tsl

    /*1
    Test Case 4  		<error>
    File Two :  File not found
     */
    @Test
    public void replaceTest1() throws Exception{
        File fileInput1 = createInputFile1();

        String args[] = {"replace -b Howdy Hello -- NOT FOUND to <to>", fileInput1.getPath()+"error_path"};
        Main.main(args);
        assertEquals("File Not Found ", errStream);

    }

    /*2
    Purpose: if length of string from <from> file is longer than the the file contents itself
     */
    @Test
    public void replaceTest1a()throws Exception {
        File inputFile = createInputFile2();

        String args[] = {"replace -b Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice... the Avengers was a great movie!  -- file1.txt file2.txt "};

        String expected = "\"Howdy Bill,\\n\" +\n" +
                "               \"This is another test file for the replace utility\\n\" +\n" +
                "                \"that contains a list:\\n\" +\n" +
                "                \"-a) Item 1\\n\" +\n" +
                "                \"-b) Item 2\\n\" +\n" +
                "                \"...\\n\" +\n" +
                "                \"and says \\\"howdy Bill\\\" twice\"";

        String actual = getFileContent(inputFile.getPath());

        Main.main(args);
        assertEquals("FROM file is longer",expected,actual);

    }

    /*3
    Test Case 2  		<error>
    File One :  File not found
     */
    @Test
    public void replaceTest2() throws Exception{
        File fileInput = createInputFile2();

        String args[] = {"replace -b Howdy Hello -- <from> to NOT FOUND", fileInput.getPath()};
        Main.main(args);
        assertEquals("File not found", errStream);
    }

    /*4
    Purpose:  File 2 empty
     */
    @Test
    public void replaceTest2a(){
        String args[] = {"replace -b Howdy Hello file1.txt file2.txt"};
        Main.main(args);
        String expected = "File 2 is empty";
        String actual = "File 2 is empty";
        assertEquals("File 2 not found", expected, actual);
    }

    //5 Purpose:  OPTION (opt) is not found
    @Test
    public void replaceTest2b(){
        String args[] = {"replace -k Howdy Hello file1.txt file2.txt"};
        Main.main(args);
        String expected = "Option -k Not found. Error";
        String acutal = "Option -k Not found. Error";
        assertEquals("Option not found", expected, acutal);
    }

    //Purpose:  Repeated number of the same char or string in the [from]
    @Test
    public void replaceTest2c(){
        String args[] = {"replace -b ccccccccccccccccccc Cat file1.txt file2.txt"};
        Main.main(args);
        String expected = "Invalid format";
        String acutal = "Invalid format";
        assertEquals("too long", expected, acutal);
    }




    /*6
   Test Case 11 		(Key = 2.2.1.2.1.1.1.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -b
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  None
   Replace Value                                   :  Replace with backUp
   */
    @Test
    public void replaceTest3() throws Exception{
        File fileInput = createInputFile1();

        String args[] = {"replace -i Hello Howdy file1.txt file2.txt", fileInput.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
               "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(fileInput.getPath());
        assertEquals("replaced",expected, actual);
    }

    /*7
   Test Case 12 		(Key = 2.2.1.2.1.2.1.)
   File One                                        :  Not empty
   File Two                                        :  Not empty
   Options                                         :  -b
   Parameter from                                  :  length1
   Parameter to                                    :  length0
   Number of matches of the pattern in second file :  One
   Replace Value                                   :  Replace with backUp
     */

    @Test
    public void replaceTest4() throws Exception{
        File fileInput = createInputFile1();

        String args[] = {"replace -i Bill William file1.txt file2.txt", fileInput.getPath()};
        Main.main(args);

        String expected = "Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!";

        String actual = getFileContent(fileInput.getPath());
        assertEquals("replaced",expected, actual);
    }

    @Test
    public void replaceTest5() throws Exception{
        File fileInput = createInputFile1();

        String args[] = {"replace -i Bill William file1.txt file2.txt", fileInput.getPath()};
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
   Replace Value                                   :  Replace with backUp
     */
    @Test
    public void replaceTest6() throws Exception{
        File input = createInputFile3();
        String args[] = {"replace -b 'have' 'evah' file1.txt, file2.txt"};
        String args1[] = {"replace -i 'have' 'evah' file1.txt, file2.txt"};
        Main.main(args);
        Main.main(args1);

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
        String args1[] = {"replace -i 'have' 'evah' file1.txt, file2.txt"};
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
        String args1[] = {"replace -f 'have' 'evah' file1.txt, file2.txt"};
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
        String args[] ={"replace -f hello HELLO file1.txt file2.txt"};
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
        String args[] ={"replace -l hello hi file1.txt file2.txt"};
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
        String args[] ={"replace -i hello HELLO file1.txt file2.txt"};
        Main.main(args);
        String expected = "hi";

        assertEquals("hi", expected);
    }






}
