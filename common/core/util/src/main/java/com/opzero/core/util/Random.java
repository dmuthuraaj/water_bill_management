package com.opzero.core.util;

import java.security.SecureRandom;

public class Random {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final String ALPHABETIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC_CHARACTERS = "0123456789";

    /**
     * Creates a random string whose length is the number of characters specified.
     * Characters will be chosen from the set of alpha-numeric characters as
     * indicated by the arguments.
     *
     * @param count   the length of random string to create
     * @param letters if true, generated string will include alphabetic characters
     * @param numbers if true, generated string will include numeric characters
     * @return the random string
     */
    public static String random(int count, boolean letters, boolean numbers) {
        if (count < 1)
            throw new IllegalArgumentException("Count must be a positive number.");
        if (!letters && !numbers)
            throw new IllegalArgumentException("At least one of letters or numbers must be true.");

        StringBuilder characters = new StringBuilder();
        if (letters)
            characters.append(ALPHABETIC_CHARACTERS);
        if (numbers)
            characters.append(NUMERIC_CHARACTERS);

        StringBuilder result = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            int index = secureRandom.nextInt(characters.length());
            result.append(characters.charAt(index));
        }

        return result.toString();
    }
}
