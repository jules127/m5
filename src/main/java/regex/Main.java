package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println(checkForPassword(userInput, 6));
        System.out.println(extractEmails(userInput));
        System.out.println(checkForDoubles(userInput));
    }

    /**
     * Checks whether a string is non-empty, has at least one lowercase letter,
     * one uppercase letter, one digit, and meets the minimum length.
     *
     * @param str       the string to check
     * @param minLength the minimum length required
     * @return true if str satisfies all password requirements, false otherwise
     */
    public static boolean checkForPassword(String str, int minLength) {
        // Handle null explicitly: a null string cannot be a valid password
        if (str == null) {
            return false;
        }

        // Regex: at least one lowercase letter, one uppercase letter, one digit,
        // and total length ≥ minLength.
        final String regex =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{" + minLength + ",}$";

        return Pattern.matches(regex, str);
    }

    /**
     * Extracts all email addresses ending with "@utoronto.ca" or "@mail.utoronto.ca"
     * that contain at least one character before the '@' symbol.
     * Emails are returned in the order they appear in the string.
     *
     * @param str the string to search
     * @return a list of matching email addresses (empty if none or if str is null)
     */
    public static List<String> extractEmails(String str) {
        // If input is null, just return an empty list instead of throwing an exception.
        final List<String> result = new ArrayList<>();
        if (str == null) {
            return result;
        }

        // Regex: one or more non-space, non-@ characters + @ + (optional "mail.") + utoronto.ca
        final Pattern pattern =
                Pattern.compile("\\b[^\\s@]+@(mail\\.)?utoronto\\.ca\\b");

        final Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

    /**
     * Checks whether the string contains the same capital letter at least twice.
     * Example: "Amazing Apple" → true (two 'A's)
     *
     * @param str the string to search
     * @return true if the same capital letter appears at least twice, false otherwise
     */
    public static boolean checkForDoubles(String str) {
        // Null cannot contain any letters
        if (str == null) {
            return false;
        }

        // Capture a capital letter and check if it appears again later.
        return str.matches(".*([A-Z]).*\\1.*");
    }
}
