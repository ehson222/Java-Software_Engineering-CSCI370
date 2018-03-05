package edu.qc.seclass;

class MyCustomString implements MyCustomStringInterface {

    private String string;

    /**
     * Returns the current string. If the string is null, it should return null.
     *
     * @return Current string
     */
    @Override
    public String getString() {
        if (string.equals(""))
            return null;
        return string;
    }

    /**
     * Sets value of current string
     * @param string The value to be set
     */
    @Override
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Returns the number of numbers in the current string, where a number is defined as a
     * contiguous sequence of digits.
     *
     * If the current string is empty, the method should return 0.
     *
     * Examples:
     * - countNumbers would return 2 for string "My numbers are 11 and 96".
     *
     * @return Number of numbers in the current string
     * @throws NullPointerException If the current string is null
     */
    //taken from https://stackoverflow.com/questions/39823137/java-count-the-number-of-integers-in-a-string
    @Override
    public int countNumbers() throws NullPointerException{
        int count = 0;
        boolean isPreviousDigit = false;

        //go through string length
        for (int i = 0; i < string.length(); i++) {
            //check character which digit
            if (Character.isDigit(string.charAt(i))) {
                //if character before this is a digit
                if (!isPreviousDigit) {
                    count++;
                    isPreviousDigit = true;
                }
            } else {
                isPreviousDigit = false;
            }
        }
        return count;
    }

    /**
     * Returns a string that consists of all and only the characters in positions n, 2n, 3n, and
     * so on in the current string, starting either from the beginning or from the end of the
     * string. The characters in the resulting string should be in the same order and with the
     * same case as in the current string.
     *
     * If the current string is empty or has less than n characters, the method should return an
     * empty string.
     *
     * Examples:
     * - For n=2 and a string of one character, the method would return an empty string.
     * - For n=2 and startFromEnd=false, the method would return the 2nd, 4th, 6th, and so on
     *   characters in the string.
     * - For n=3 and startFromEnd=true, the method would return the 3rd from the last character
     *   in the string, 6th from the last character in the string, and so on (in the order in
     *   which they appear in the string).
     *
     * Values n and startFromEnd are passed as parameters. The starting character, whether it is
     * the first one or the last one in the string, is considered to be in Position 1.
     *
     * @param n Determines the positions of the characters to be returned
     * @param startFromEnd Determines whether to start counting from the end or from the
     *                     beginning when identifying the characters in position n, 2n, 3n, and so
     *                     on. Please note that the characters are always returned in the order in
     *                     which they appear in the string.
     * @return String made of characters at positions n, 2n, and so on in the current string
     * @throws IllegalArgumentException If "n" less than or equal to zero
     * @throws NullPointerException If the current string is null and "n" is greater than zero
     *
     */
    @Override
    public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {

        //String result = "";  //store result
        if (!startFromEnd) {
            String result = "";
            for (int i = n; i < string.length(); i += n) {
                result += string.charAt(i - 1);
            }
            return result;  //return this if !startFromEnd is false
        } else {
            String res = "";
            String reverse = "";
            int index = string.length() - n;
            int count = 1;
            //reverse string, then re reverse it for proper format
            while (index >= 0) {
                res += string.charAt(index);
                index = string.length() - (n * ++count);
            }
            for (int i = res.length() - 1; i >= 0; i--) {
                reverse = reverse + res.charAt(i);
            }
            return reverse; //return proper formatted string
        }
    }

    /**
     * Replace the individual digits in the current string, between startPosition and endPosition
     * (included), with the corresponding English names of those digits, with the first letter
     * capitalized. The first character in the string is considered to be in Position 1.
     * Unlike for the previous method, digits are converted individually, even if contiguous.
     *
     * Examples:
     * - String 460 would be converted to FourSixZero
     * - String 416 would be converted to FourOneSix
     *
     * @param startPosition Position of the first character to consider
     * @param endPosition   Position of the last character to consider
     * @throws IllegalArgumentException    If "startPosition" > "endPosition"
     * @throws MyIndexOutOfBoundsException If "startPosition" <= "endPosition", but either
     *                                     "startPosition" or "endPosition" are out of
     *                                     bounds (i.e., either less than 1 or greater than the
     *                                     length of the string)
     * @throws NullPointerException        If "startPosition" <= "endPosition", "startPosition" and
     *                                     "endPosition" are greater than 0, and the current
     *                                     string is null
     */
    @Override
    public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) throws IllegalArgumentException, MyIndexOutOfBoundsException, NullPointerException
    {

        String phrase = string;
        int len = phrase.length();

        //Arguments to catch errors
        if (startPosition > endPosition)
            throw new IllegalArgumentException("IllegalArgumentException");

        else if (startPosition <= endPosition && (startPosition < 0 || endPosition > string.length()))
            throw new MyIndexOutOfBoundsException("MyIndexOutOfBoundException");

        else if (startPosition <= endPosition && startPosition > 0 && endPosition > 0 && phrase.equals(""))
            throw new NullPointerException("NullPointerException");

        else {
            String array[] = new String[100];
            for (int i = 0; i < len; i++) {
                array[i] = Character.toString(phrase.charAt(i));  //get char as string and save it to the array
            }
            //get all the characters between startPosition and endPosition, call name function to compare which digit
            //can be replaced to a word, story them in an array.
            for (int j = startPosition - 1; j < endPosition; j++) {
                array[j] = name(array[j]);
            }
            //get array of replaced words from startPosition to endPosition and store them to names
            String names = "";
            for (int i = 0; i < len; i++) {
                names += array[i];
            }
            string = names;  //use names values and put them in the original string.
        }
    }

    /**
     * Helper method for convertDigitsToNamesInSubstring
     * to return digit name
     * @param letter
     * @return
     */
    public static String name(String letter) {
        switch (letter) {
            case "0":
                return "Zero";
            case "1":
                return "One";
            case "2":
                return "Two";
            case "3":
                return "Three";
            case "4":
                return "Four";
            case "5":
                return "Five";
            case "6":
                return "Six";
            case "7":
                return "Seven";
            case "8":
                return "Eight";
            case "9":
                return "Nine";
            default:
                return letter;
        }
    }
}
