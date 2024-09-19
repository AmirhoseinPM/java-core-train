import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sample string is: ");
        String sample = "2888888888888888 +-----------------_#%^#*((())))&&&&&^^^^^%%%%$$$$$#####@@@@!!!!!~~~@@\n" +
                " The  split() method splits a string into an array of substrings using a regular expression as the separator.\n" +
                "If a limit is specified, the returned array will not be longer than the limit.\n" +
                "The last element of the array will contain the remainder of the string, which may still have separators in it if the limit was reached.";
        System.out.println(sample + "\n");

        System.out.println("------------Results------------");
        System.out.println("Count words: " + StringAnalyzer.countWords(sample));
        System.out.println("First longest word: " + StringAnalyzer.firstLongestWord(sample));
        System.out.println("Last longest word: " + StringAnalyzer.lastLongestWord(sample));
        System.out.println("All longest word: " + StringAnalyzer.longestWords(sample));
        System.out.println();
        System.out.println("Upper characters count: " + StringAnalyzer.countUpperChar(sample));
        System.out.println("Lower characters count: " + StringAnalyzer.countLowerChar(sample));
        System.out.println("Non letter count: " + StringAnalyzer.countNonAlphabetic(sample));
        System.out.println("Digit characters count: " + StringAnalyzer.countDigits(sample));
        System.out.println();
    }
}