// Designed by: Amir Elkased

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Throwable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Input the file path:");
            String path = scanner.nextLine();
            List<String> list = takeInput(path);
            printResult(list);
        } catch (Throwable e) {
            System.out.println("File not found!");
        }
    }

    public static String readAllFileAsString(String name) throws Throwable {
        return new String(Files.readAllBytes(Paths.get(name)));
    }

    public static List<String> takeInput(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (scan.hasNextLine()) {
            list.add(scan.nextLine());
        }
        return list;
    }

    public static int maxLength(List<String> lines) {
        return lines.stream().max(Comparator.comparing(String::length)).get().length();
    }

    public static StringBuilder replaceChar(String src) {
        StringBuilder str = new StringBuilder(src);
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            switch (x) {
                case '(' -> str.setCharAt(i, ')');
                case ')' -> str.setCharAt(i, '(');
                case '\\' -> str.setCharAt(i, '/');
                case '/' -> str.setCharAt(i, '\\');
                case '<' -> str.setCharAt(i, '>');
                case '>' -> str.setCharAt(i, '<');
                case '{' -> str.setCharAt(i, '}');
                case '}' -> str.setCharAt(i, '{');
                case '[' -> str.setCharAt(i, ']');
                case ']' -> str.setCharAt(i, '[');
                default -> {
                }
            }
        }
        return reflect(str);
    }

    public static StringBuilder reflect(StringBuilder src) {
        for (int i = 0, j = src.length() - 1; i < src.length() / 2; ++i, --j) {
            char temp = src.charAt(i);
            src.setCharAt(i, src.charAt(j));
            src.setCharAt(j, temp);
        }
        return src;
    }

    public static void printResult(List<String> lines) {
        int maxLength = maxLength(lines);
        for (String string : lines) {
            string += " ".repeat(maxLength - string.length());
            System.out.printf("%s | %s%n", string, replaceChar(string));
        }
    }
}