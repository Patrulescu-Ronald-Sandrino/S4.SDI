package ro.lab10.tools;

import java.util.Scanner;

public class IO {
    public static void write(String line) {
        System.out.print(line);
    }

    public static void writeLine(String line) {
        System.out.println(line);
    }

    public static int readInteger(String prompt) {
        write(prompt);
        var scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public static int readInteger() {
        return readInteger("");
    }

    public static String readString(String prompt) {
        write(prompt);
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static double readDouble(String prompt) {
        write(prompt);
        var scanner = new Scanner(System.in);
        return Double.parseDouble(scanner.nextLine());
    }
}
