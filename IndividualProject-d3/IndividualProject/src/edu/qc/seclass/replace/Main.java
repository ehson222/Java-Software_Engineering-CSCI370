package edu.qc.seclass.replace;

import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Main {



    public static void main(String[] args){
        // write your code here

        //Replace replace = new Replace();
        Main newMain = new Main();

        System.out.println("Wrong Arguments: " + args.length);


        //the number of arguments before ---
        for (int i = 0; i < args.length; i++) {
            if (args.length < 4) {
                newMain.usage();
            } else if (args[i].equals("--")) {

                Replace.numberOfDoubleDash++;
                Replace.delimiterPosition = i;
                Replace.trueReplaceOptions = i;

                for (int j = i; j < args.length; j++) {
                    if (args[j].equals("--")) {
                        Replace.delimiterPosition = j;
                    }
                }
                break;
            }
        }

        Replace.numberOfFiles(args);
        Replace.differentReplacements(args);
        Replace.optionsCategory(args);

    }

    //public TemporaryFolder temporaryFolder = new TemporaryFolder();

    public static String usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*");
        return "Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*";
    }


    private Charset charset = StandardCharsets.UTF_8;


    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

//    private File createInputFile1() throws Exception {
//        File testFile = createTmpFile();
//        FileWriter fileWriter = new FileWriter(testFile);
//
//        fileWriter.write("Howdy Bill,\n" +
//                "This is a test file for the replace utility\n" +
//                "Let's make sure it has at least a few lines\n" +
//                "so that we can create some interesting test cases...\n" +
//                "And let's say \"howdy bill\" again!");
//
//        fileWriter.close();
//        return testFile;
//    }

    public File backUpOption(File sourceFile) throws IOException, AssertionError {
        //temporaryFolder.create();
        String targetFileName = sourceFile.getName() + ".bck";
        File targetFile = new File(targetFileName);
        String sourcePathDir = sourceFile.toPath().toString() + ".bck";
        Path pathTest = Paths.get(sourcePathDir);

        Files.copy(sourceFile.toPath(), pathTest, REPLACE_EXISTING);
        return targetFile;

    }

    /*
     Reference:  http://tutorials.jenkov.com/java-regex/pattern.html
    */
    public void replaceCaseInsensitive(String fromString, String toString, File fileToConvert) throws IOException {
        String file = getFileContent(fileToConvert.toString());

        Pattern pattern = Pattern.compile(fromString, Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(file);

        String replaceStuff = match.replaceAll(toString);

        FileWriter fileWriter = new FileWriter(fileToConvert, false);
        fileWriter.write(replaceStuff);
        fileWriter.close();

    }

    public void firstReplaceForAll(String fromString, String toString, File fileToConvert) throws IOException {
        String file = getFileContent(fileToConvert.toString());

        fromString = "(?i)" + fromString;
        String replaceContent = file.replaceFirst(fromString, toString);

        FileWriter fileWriter = new FileWriter(fileToConvert, false);
        fileWriter.write(replaceContent);
        fileWriter.close();

    }


    public void replaceCaseSensitive(String fromString, String toString, File fileToConvert) throws IOException {
        String file = getFileContent(fileToConvert.toString());

        String replacedContent = file.replaceAll(fromString, toString);

        FileWriter fileWriter = new FileWriter(fileToConvert, false);
        fileWriter.write(replacedContent);
        fileWriter.close();


    }

    public void replaceFirstOccurrence(String fromString, String toString, File fileToConvert) throws IOException {
        String file = getFileContent(fileToConvert.toString());

        String replacedContent = file.replaceAll(fromString, toString);

        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();

    }

    public void replaceLastOccurrence(String fromString, String toString, File fileToConvert) throws IOException {

        String replaceFromString = fromString;
        String replaceToString = toString;

        String fileContent = getFileContent(fileToConvert.toString());

        String replacedContent = fileContent;

        int lastIndex = fileContent.lastIndexOf(replaceFromString);
        if (lastIndex >= 0) {
            replacedContent = fileContent.substring(0, lastIndex) + replaceToString + fileContent.substring(lastIndex + replaceFromString.length());
        }

        FileWriter newFile = new FileWriter(fileToConvert, false);
        newFile.write(replacedContent);
        newFile.close();


    }
}


