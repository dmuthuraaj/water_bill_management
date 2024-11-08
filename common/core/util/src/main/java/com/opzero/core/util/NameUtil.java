package com.opzero.core.util;

public class NameUtil {

    public static String generateShortName(String input) {
        // Step 1: Remove all special characters
        String cleanedInput = input.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();

        // Step 2: Split the string into words
        String[] words = cleanedInput.split("\\s+");

        StringBuilder shortName = new StringBuilder();

        // Step 3: Iterate through each word and apply the character picking logic
        for (String word : words) {
            int wordLength = word.length();
            for (int i = 0; i < wordLength; i += 4) {
                shortName.append(Character.toUpperCase(word.charAt(i)));
                if (i + 1 < wordLength)
                    shortName.append(Character.toUpperCase(word.charAt(i + 1)));
                if (shortName.length() >= 8)
                    break;
            }
            if (shortName.length() >= 8)
                break;
        }

        // Ensure the result is exactly 8 characters long
        return shortName.length() > 8 ? shortName.substring(0, 8) : shortName.toString();
    }

    public static String getFirstName(String name) {
        String[] parts = name.trim().split("\\s+");
        return parts.length > 0 ? parts[0] : "";
    }

    public static String getLastName(String name) {
        String[] parts = name.trim().split("\\s+");
        return parts.length > 1 ? parts[parts.length - 1] : "";
    }

    public static String markAsDeleted(String data) {
        return data + "_deleted_" + System.currentTimeMillis() / 1000;
    }

}
