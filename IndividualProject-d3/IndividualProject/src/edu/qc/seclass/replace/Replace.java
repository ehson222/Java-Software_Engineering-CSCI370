// NEEDED THIS CLASS BEFORE
// //
// package edu.qc.seclass.replace;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Replace {
//
//    static boolean replaceFirst;
//    static boolean replaceLast;
//    static boolean replaceAll;
//    static boolean replaceAllCaseSensitive;
//    static boolean optBackUp;
//    static boolean firstReplaceFromAll;
//
//    static int numberOfFiles = 0;
//    static int fileStartPosition = 0;
//    static String fromString = "";
//    static String toString = "";
//    static int numberOfDoubleDash = 0;
//    static int delimiterPosition = -1;
//    static int numberOfReplacements = 0;
//    static int trueReplaceOptions = 0;
//
//    static Main newMain = new Main();
//
//    public static void numberOfFiles(String[] arguments) {
//
//        numberOfFiles = arguments.length - delimiterPosition;
//        fileStartPosition = delimiterPosition + 1;
//        /*if (delimiterPosition -2 < 0){
//            throw new usage();
//        }*/
//
//        fromString = arguments[delimiterPosition - 2];
//        toString = arguments[delimiterPosition - 1];
//
//
//        numberOfReplacements = trueReplaceOptions;
//
//        if ((delimiterPosition - 2 == 0) || (delimiterPosition - 1 == 1)) {
//            //replaceAllCaseSensitive = true;
//            newMain.usage();
//            //System.exit(0);
//        }
//
//    }
//
//    public static void optionsCategory(String[] args) {
//
//        if (numberOfDoubleDash == 0) {
//
//            throw new NullPointerException("");
//        }
////        File[] files = new File[numberOfFiles];
//        for (int i = fileStartPosition; i < args.length; i++){
//
//            if (optBackUp) {
//                File oldFile = new File(args[i]);
//
//                try {
//                    newMain.backUpOption(oldFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            if (replaceFirst) {
//                File oldFile = new File(args[i]);
//
//                try {
//                    newMain.replaceFirstOccurrence(fromString, toString, oldFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            if (replaceLast) {
//                File oldFile = new File(args[i]);
//                try {
//                    newMain.replaceLastOccurrence(fromString, toString, oldFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            if (replaceAll) {
//                File oldFile = new File(args[i]);
//                try {
//                    newMain.replaceCaseInsensitive(fromString, toString, oldFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//
//            if (replaceAllCaseSensitive) {
//                File oldFile = new File(args[i]);
//                try {
//                    newMain.replaceCaseSensitive(fromString, toString, oldFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            if (firstReplaceFromAll) {
//                File oldFile = new File(args[i]);
//                try {
//                    newMain.firstReplaceForAll(fromString, toString, oldFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//    public static void differentReplacements(String[] args) {
//        List<String> replaceOptionList = new ArrayList<>(Arrays.asList());
//        for (int j = 0; j < numberOfReplacements; j++) {
//            if (args[j] == null) {
//                throw new NullPointerException();
//            }
//            /*else if(args[j] == "--"){
//                newMain.usage();
//            }*/
//            if ((args[j].equals("-i") || args[j].equals("-l") || args[j].equals("-f")
//                    || args[j].equals("-b")) && (!replaceOptionList.contains(args[j]))) {
//                replaceOptionList.add(args[j]);
//            }
//        }
//
//        if (replaceOptionList.contains("-b")) {
//            optBackUp = true;
//        }
//
//
//        if (replaceOptionList.size() == 1) {
//            if (replaceOptionList.contains("-f")) {
//                replaceFirst = true;
//            } else if (replaceOptionList.contains("-l")) {
//                replaceLast = true;
//            } else if (replaceOptionList.contains("-i")) {
//                replaceAll = true;
//            } else if (replaceOptionList.contains("-b")) {
//                optBackUp = true;
//                replaceAllCaseSensitive = true;
//            } else {
//                replaceAllCaseSensitive = true;
//                //newMain.usage();
//            }
//
//        } else if (replaceOptionList.size() == 0) {
//            replaceAllCaseSensitive = true;
//            //newMain.usage();
//        } else if (replaceOptionList.size() == 2) {
//            if (replaceOptionList.contains("-f") && (replaceOptionList.contains("-l"))) {
//                replaceFirst = true;
//                replaceLast = true;
//            } else if (replaceOptionList.contains("-l") && (replaceOptionList.contains("-i"))) {
//                //lastCaseSensitive = true;
//            } else if (replaceOptionList.contains("-i") && (replaceOptionList.contains("-f"))) {
//                firstReplaceFromAll = true;
//            } else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-f"))) {
//                replaceFirst = true;
//            } else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-l"))) {
//                replaceLast = true;
//            } else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-i"))) {
//                replaceAll = true;
//            }
//
//
//        } else if (replaceOptionList.size() == 3) {
//            if (replaceOptionList.contains("-f") && (replaceOptionList.contains("-l")) && (replaceOptionList.contains("-i"))) {
//                replaceAll = true;
//            } else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-f")) && (replaceOptionList.contains("-l"))) {
//                replaceFirst = true;
//                replaceLast = true;
//            } else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-l")) && (replaceOptionList.contains("-i"))) {
//                replaceLast = true;
//            } else if (replaceOptionList.contains("-b") && (replaceOptionList.contains("-i")) && (replaceOptionList.contains("-f"))) {
//                firstReplaceFromAll = true;
//            }
//        } else if (replaceOptionList.size() == 4) {
//            if (replaceOptionList.contains("-f") && (replaceOptionList.contains("-l")) && (replaceOptionList.contains("-l")) && (replaceOptionList.contains("-i"))) {
//                firstReplaceFromAll = true;
//            }
//
//        }
//    }
//}//end Replace
//
