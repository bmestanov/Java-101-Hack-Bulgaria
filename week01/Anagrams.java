package week01;

import java.util.Scanner;

public class Anagrams {
    public static boolean areAnagrams(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        int i = 0;
        for (; i < a.length() && i < b.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }

        return i == a.length() && i == b.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String a = scanner.next();
        String b = scanner.next();

        if (areAnagrams(a, b)) {
            System.out.println("ANAGRAMS");
        } else {
            System.out.println("NOT ANAGRAMS");
        }
    }
}
