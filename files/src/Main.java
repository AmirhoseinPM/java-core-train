import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        writeData("sample.txt", "abcdefgh\n11111111111111");
        writeData("sample.txt", "\npostjkljbvbrfvn", true);
        printInfo("sample.txt");

        System.out.println("--------File content--------");
        System.out.println(getContent("sample.txt"));

        System.out.println("--------Replced content--------");
        replaceAll("sample.txt", "\\d", "*");
        System.out.println(getContent("sample.txt"));
    }

    public static void create(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile())
                System.out.println("File does not exist, empty file created");
            else
                System.out.println(fileName + " already exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printInfo(String fileName) {
        System.out.println("--------File information--------");
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("Name: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Read permission: " + file.canRead());
            System.out.println("Write permission: " + file.canWrite());
            System.out.println("Execute permission: " + file.canExecute());
            System.out.println("Size(byte): " + file.length());
        }
        else
            System.out.println("File does not exists");
    }

    public static void writeData(String fileName, String data) {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                ) {
            fileWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getContent(String fileName) {
        StringBuffer content = new StringBuffer();
        File file = new File(fileName);
        try (
                Scanner fileScanner = new Scanner(file);
                ) {
            while(fileScanner.hasNextLine())
                content.append(fileScanner.nextLine() + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    public static void writeData(String fileName, String data, Boolean append) {
        File file = new File(fileName);
        try (
                FileWriter fileWriter = new FileWriter(file, append);
                ) {
            fileWriter.write(data);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void replaceAll(String fileName, String regex, String newStr) {
        String oldContent = getContent(fileName);
        String newContent = oldContent.replaceAll(regex, newStr);
        writeData(fileName, newContent);
    }

}