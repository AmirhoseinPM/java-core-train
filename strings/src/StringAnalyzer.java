import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class StringAnalyzer {

    public static int countWords(String str) {
        String regexSeparator = "[\\W]+";
        String[] strArray = str.split(regexSeparator);
        return strArray.length;
    }
    public static String firstLongestWord(String str) {
        String regexSeparator = "[\\W]+";
        String[] strArray = str.split(regexSeparator);

        int longestLength = 0;
        String longestWord = "";
        for(String word: strArray)
            if(word.length() > longestLength) {
                longestWord = word;
                longestLength = word.length();
            }
        return longestWord;
    }
    public static String lastLongestWord(String str) {
        String regexSeparator = "[\\W]+";
        String[] strArray = str.split(regexSeparator);

        int longestLength = 0;
        String longestWord = "";
        for(String word: strArray)
            if(word.length() >= longestLength) {
                longestWord = word;
                longestLength = word.length();
            }
        return longestWord;
    }

    public static Set<String> longestWords(String str) {
        String regexSeparator = "[\\W]+";
        String[] strArray = str.split(regexSeparator);

        Set<String> longestWords = new HashSet<>();
        int longestLength = 0;

        for(String word: strArray)
            if(word.length() > longestLength) {
                longestWords.clear();
                longestWords.add(word);
                longestLength = word.length();
            } else if (word.length() == longestLength)
                longestWords.add(word);

        return longestWords;
    }

    public static int countUpperChar(String str) {
        char[] charArray = str.toCharArray();
        int count = 0;
        for(char c: charArray)
            if (Character.isUpperCase(c))
                count++;
        return count;
    }
    public static int countLowerChar(String str) {
        char[] charArray = str.toCharArray();
        int count = 0;
        for(char c: charArray)
            if (Character.isLowerCase(c))
                count++;
        return count;
    }
    public static int countNonAlphabetic(String str) {
        char[] charArray = str.toCharArray();
        int count = 0;
        for(char c: charArray)
            if (!Character.isLetter(c))
                count++;
        return count;
    }
    public static int countDigits(String str) {
        char[] charArray = str.toCharArray();
        int count = 0;
        for(char c: charArray)
            if (Character.isDigit(c))
                count++;
        return count;
    }
}
