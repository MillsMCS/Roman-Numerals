package edu.mills.cs180a;

import java.util.Map;

/**
 * Immutable Roman Numeral, representing a value between {@link #MIN_VALUE} and {@link #MAX_VALUE},
 * inclusive.
 *
 * @author Ellen Spertus
 * @author Irene Serrano
 */
public class RomanNumeral {
    /**
     * The lowest number that can be represented.
     */
    public static final int MIN_VALUE = 1;

    /**
     * The highest number that can be represented.
     */
    public static final int MAX_VALUE = 9999;

    @VisibleForTesting
    protected static final Map<String, Integer> LETTERS_TO_VALUES =
    Map.of("I", 1, "IV", 4, "V", 5, "IX", 9, "X", 10, "XL", 40, "L", 50, "XC", 90, "C", 100,
            "CD", 400, "D", 500, "CM", 900, "M", 1000);

    @VisibleForTesting
    protected static final Map<Integer, String> VALUES_TO_LETTERS =
    Map.of(1, "I", 4, "IV", 5, "V", 9, "IX", 10, "X", 40, "XL", 50, "L", 90, "XC", 100, "C",
            400, "CD", 500, "D", 900, "CM", 1000, "M");

    private final int value;
    private String text;

    /**
     * Constructs a newly-allocated {@code RomanNumeral} object that represents the specified value.
     * The argument must be in the range {@link #MIN_VALUE} to {@link #MAX_VALUE}, inclusive.
     *
     * @param value the value to be represented
     * @throws IllegalArgumentException if the argument is out of bounds or malformed
     */
    public RomanNumeral(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException(
                    "Value out of bounds [" + MIN_VALUE + "..." + MAX_VALUE + "]: " + value);
        }
        this.value = value;
        text = convertFromInt(value);
    }

    /**
     * Constructs a newly-allocated {@code RomanNumeral} object for the specified text. The argument
     * must be in the range {@link #MIN_VALUE} to {@link #MAX_VALUE}, inclusive.
     *
     * @param text the Roman Numeral
     * @throws IllegalArgumentException if the argument is out of bounds
     */
    public RomanNumeral(String text) {
        this.text = text;
        value = convertFromString(text);
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException(
                    "Value out of bounds [" + MIN_VALUE + "..." + MAX_VALUE + "]: " + value);
        }
    }

    /**
     * Returns the numeric value of this {@code RomanNumeral}.
     *
     * @return the numeric value of this {@code RomanNumeral}
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return text;
    }

    /**
     * Returns the numeric value of any given Roman Numeral by taking any String, breaking it down
     * into an array of char and then setting an int variable numericValue to the numeric value of
     * the first char in the array. The method then cycles through the array, comparing the current
     * char to the next char, and determines whether to add the current char's numeric value to
     * numericValue or to add the difference between the value of the next char and twice the value
     * of the current char.
     *
     * @param s the Roman Numeral
     * @return the numeric value of the Roman Numeral
     * @throws IllegalArgumentException if the argument is out of bounds
     */
    @VisibleForTesting
    protected static int convertFromString(String s) {
        // allow for Roman Numerals in lower case to be accepted
        char[] letters = s.toUpperCase().toCharArray();
        int numericValue = LETTERS_TO_VALUES.get(letters[0]);
        char current;
        char next;


        // if the string is just one character, then just give the symbol value
        if (letters.length == 1) {
            // throw an error if the char does not exist in the map
            if (!LETTERS_TO_VALUES.containsKey(letters[0])) {
                throw new IllegalArgumentException(letters[0] + "is not a valid roman numeral");
            }

            numericValue = LETTERS_TO_VALUES.get(letters[0]);
        }

        for (int i = 0; i < letters.length; i++) {
            current = letters[i];
            next = letters[i + 1];

            // once we reach the end of the array, return the end value
            if (i == letters.length - 1) {
                return numericValue;
            }

            // throw an error if the chars in current or next do not exist in the map
            if (!LETTERS_TO_VALUES.containsKey(current)) {
                throw new IllegalArgumentException(letters[i] + " is not a valid roman numeral");
            }

            if (!LETTERS_TO_VALUES.containsKey(next)) {
                throw new IllegalArgumentException(
                        letters[i + 1] + " is not a valid roman numeral");
            }

            if (LETTERS_TO_VALUES.get(current) >= LETTERS_TO_VALUES.get(next)) {
                numericValue += LETTERS_TO_VALUES.get(next);
            }

            // this course of action is a way to account for Roman Numerals that contain Subtractive
            // forms of some numbers, ie CDXLIV (444; 40=XL and 4=IV) or DCLXXIX (679; 9=IX) etc
            if (LETTERS_TO_VALUES.get(current) < LETTERS_TO_VALUES.get(next)) {
                numericValue += LETTERS_TO_VALUES.get(next) - (2 * LETTERS_TO_VALUES.get(current));
            }
        }

        return numericValue;
    }// end convertFromString


    /**
     * Returns the Roman Numeral representation of any given number by first determining which keys
     * in VALUES_TO_LETTERS the number falls between and setting the lesser key as a lowerBound. The
     * number, lowerBound and a StringBuilder are passed to a helper method, which will append the
     * appropriate Roman symbol to the StringBuilder and return the number, of which the value has
     * decreased based on the numeric value of the appended symbol.
     *
     * @param n the number to convert
     * @return the Roman Numeral representation
     */
    @VisibleForTesting
    protected static String convertFromInt(int n) {
        // the lower bound is essentially the largest Roman Numeral symbol that is meant to be
        // appended first since the general format of Roman Numerals is largest values first
        int lowerBound = 0;
        StringBuilder RomanNumeral = new StringBuilder();

        while (n > 0) {
            for (int i = 1; i <= LETTERS_TO_VALUES.size(); i++) {
                // once we reach the end of the array, just set the bound to the last value
                if (i == LETTERS_TO_VALUES.size() - 1) {
                    lowerBound = LETTERS_TO_VALUES.get(i);
                }

                if (LETTERS_TO_VALUES.get(i - 1) <= n && n < LETTERS_TO_VALUES.get(i)) {
                    lowerBound = LETTERS_TO_VALUES.get(i - 1);
                }
            }

            // once the value n reaches 0, the Roman Numeral string is complete
            n = createNotation(n, lowerBound, RomanNumeral);
        }

        return RomanNumeral.toString();

    }// end convertFromInt

    // the purpose of this helper method is to append the Roman Numeral symbol stored in lowerBound
    // to the passed in StringBuilder. The number of times any symbol is appended to the String
    // Builder depends on the quotient between number and lowerBound. The value of the number is
    // then decreased by the value associated with the number of times the Roman Numeral is appended
    // into the StringBuilder. Subtractive forms are accounted for by adding them to the
    // VALUES_TO_LETTERS map interface.
    private static int createNotation(int number, int min, StringBuilder RN) {
        for (int i = 0; i < (number / min); i++) {
            RN.append(VALUES_TO_LETTERS.get(min));
        }

        return number % min;

    }// end createNotation method

}// end RomanNumeral class


