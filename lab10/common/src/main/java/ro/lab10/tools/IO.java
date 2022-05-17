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
        return scanner.nextInt();
    }

    public static String readString(String prompt) {
        write(prompt);
        var scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static double readDouble(String prompt) {
        write(prompt);
        var scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
}
