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

    /*
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

    /*
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

    /*
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




}
