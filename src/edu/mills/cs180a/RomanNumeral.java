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
    protected static final Map<String, Integer> LETTERS_TO_VALUES = Map.ofEntries(Map.entry("I", 1),
            Map.entry("IV", 4), Map.entry("V", 5), Map.entry("IX", 9), Map.entry("X", 10),
            Map.entry("XL", 40), Map.entry("L", 50), Map.entry("XC", 90), Map.entry("C", 100),
            Map.entry("CD", 400), Map.entry("D", 500), Map.entry("CM", 900), Map.entry("M", 1000));

    @VisibleForTesting
    protected static final Map<Integer, String> VALUES_TO_LETTERS = Map.ofEntries(Map.entry(1, "I"),
            Map.entry(4, "IV"), Map.entry(5, "V"), Map.entry(9, "IX"), Map.entry(10, "X"),
            Map.entry(40, "XL"), Map.entry(50, "L"), Map.entry(90, "XC"), Map.entry(100, "C"),
            Map.entry(400, "CD"), Map.entry(500, "D"), Map.entry(900, "CM"), Map.entry(1000, "M"));

    private static final int[] NUMERICS =
            new int[] {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};


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
     * Returns the number corresponding to the given string representing a Roman Numeral.
     *
     * @param s the Roman Numeral
     * @return the numeric value of the Roman Numeral
     * @throws IllegalArgumentException if the argument does not exist in Map
     */
    @VisibleForTesting
    protected static int convertFromString(String s) {
        // allow for lower case Roman Numerals to be accepted
        char[] letters = s.toUpperCase().toCharArray();

        // throw an error if any of the chars do not exist in the map
        for (int i = 0; i < letters.length; i++) {
            if (!LETTERS_TO_VALUES.containsKey(String.valueOf(letters[i]))) {
                throw new IllegalArgumentException(letters[i] + "is not a valid roman numeral");
            }
        }

        int numericValue = LETTERS_TO_VALUES.get(String.valueOf(letters[0]));

        // set the limit to one before the full length of the char array so that we don't run into
        // any issues with variable next
        for (int i = 0; i < letters.length - 1; i++) {
            String current = String.valueOf(letters[i]);
            String next = String.valueOf(letters[i + 1]);

            if (LETTERS_TO_VALUES.get(current) >= LETTERS_TO_VALUES.get(next)) {
                numericValue += LETTERS_TO_VALUES.get(next);
            } else {
                // Check if current starts a subtractive form (e.g., IX), where the second symbol
                // has a higher value than the first. Subtract current twice: once because it is the
                // start of a subtractive form and once because it was added in a previous
                // iteration.
                numericValue += LETTERS_TO_VALUES.get(next) - (2 * LETTERS_TO_VALUES.get(current));
            }
        }
        return numericValue;
    }// end convertFromString


    /**
     * Returns the Roman Numeral representation of any given number by first determining which keys
     * in {@link #VALUES_TO_LETTERS} the number falls between and setting the lesser key as a
     * {@link #lowerBound}. The number, {@link #lowerBound} and a StringBuilder are passed to a
     * helper method, which will append the appropriate Roman symbol to the StringBuilder and return
     * the number, of which the value has decreased based on the numeric value of the appended
     * symbol.
     *
     * @param n the number to convert
     * @return the Roman Numeral representation
     * @throws IllegalArgumentException if the argument is out of bounds
     */
    @VisibleForTesting
    protected static String convertFromInt(int n) {
        // lowerBound should be set to the Roman Numeral that closely matches the outermost place
        // value of any given number. Ex n = 56, lowerBound = L; n = 383, lowerBound = C
        String lowerBound = "";
        StringBuilder romanNumeral = new StringBuilder();

        if (n > MAX_VALUE || n < MIN_VALUE) {
            throw new IllegalArgumentException(n + "is out of bounds");
        }

        // once the value of n reaches 0, the Roman Numeral string is complete
        while (n > 0) {
            for (int i = 0; i < NUMERICS.length; i++) {
                // if the numeric value of the integer is 1000 or greater, which will cause the loop
                // to reach the end of NUMERICS, set lowerBound to M.
                if (i == (NUMERICS.length - 1) && NUMERICS[i] <= n) {
                    lowerBound = VALUES_TO_LETTERS.get(NUMERICS[i]);
                } else if (NUMERICS[i] <= n && n < NUMERICS[i + 1]) {
                    lowerBound = VALUES_TO_LETTERS.get(NUMERICS[i]);
                    // break out of the loop as soon as lowerBound is determined
                    break;
                }
            }

            appendNTimes(romanNumeral, n, lowerBound);

            // Decrease the value of n by determining the remainder value of n divided by the
            // numeric value of the symbol stored in lowerBound.
            n = n % LETTERS_TO_VALUES.get(lowerBound);
        }

        return romanNumeral.toString();

    }// end convertFromInt

    // the purpose of this helper method is to append the Roman Numeral symbol to a StringBuilder.
    // The number of times any symbol is appended to the String Builder depends on the quotient
    // between n and the numeric value of symbol.
    private static void appendNTimes(StringBuilder RN, int n, String symbol) {
        for (int i = 0; i < (n / LETTERS_TO_VALUES.get(symbol)); i++) {
            RN.append(symbol);
        }
    }// end createNotation method

}// end RomanNumeral class


