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
     * Returns the number corresponding to the given string representing a Roman Numeral.
     *
     * @param s the Roman Numeral
     * @return the corresponding number
     */
    @VisibleForTesting
    protected static int convertFromString(String s) {
        // take string and break it down to single characters and save them into an array
        char[] letters = s.toUpperCase().toCharArray();

        // keep track of the numeric value, set the total to the very first character
        int total = LETTERS_TO_VALUES.get(letters[0]);

        // keep track of where you are in the array
        char current_letter, previous_letter;


        // if the RN is just one character, then just give the symbol value
        if (letters.length == 1) {
            // check to see if the character does not exist in the map
            // if that's the case then throw an error
            if (!LETTERS_TO_VALUES.containsKey(letters[0])) {
                throw new IllegalArgumentException(letters[0] + "is not a valid roman numeral");
            } else {
                total = LETTERS_TO_VALUES.get(letters[0]);
            } // end if tree

        } else {
            // cycle through the array list
            for (int i = 1; i < letters.length; i++) {
                // set the iteration to the current letter and the previous iteration to the
                // previous letter
                previous_letter = letters[i - 1];
                current_letter = letters[i];

                // check to see if the character does not exist in the map
                // if that's the case then throw an error
                if (!LETTERS_TO_VALUES.containsKey(previous_letter)) {
                    throw new IllegalArgumentException(
                            letters[0] + " is not a valid roman numeral");
                } else if (!LETTERS_TO_VALUES.containsKey(current_letter)) {
                    throw new IllegalArgumentException(
                            letters[0] + " is not a valid roman numeral");
                }

                // compare the value of the current letter to the value of the previous letter
                // if current is smaller next, subtract current value from the previous value
                // if current is larger next, add the two values
                // if current is equal next, add the two values
                // this if tree should work for values 1 through 10
                if (LETTERS_TO_VALUES.get(previous_letter) == LETTERS_TO_VALUES
                        .get(current_letter)) {
                    total = total + LETTERS_TO_VALUES.get(current_letter);
                } else if (LETTERS_TO_VALUES.get(previous_letter) > LETTERS_TO_VALUES
                        .get(current_letter)) {
                    total = total + LETTERS_TO_VALUES.get(current_letter);
                } else if (LETTERS_TO_VALUES.get(previous_letter) < LETTERS_TO_VALUES
                        .get(current_letter)) {
                    total = LETTERS_TO_VALUES.get(current_letter) - total;
                } // end if
            } // end for loop
        } // end if statement

        return total;
    }// end convertFromString


    /**
     * Returns the Roman Numeral representation of the given number by determining which keys in
     * VALUES_TO_LETTERS the number falls between, setting the lesser key as a lower bound. These
     * variables and a declared StringBuilder are then passed to a helper method createNotation,
     * which will append the appropriate Roman symbol to the StringBuilder and return the number, of
     * which the value has decreased based on the numeric value of the appended symbol.
     *
     * @param n the number to convert
     * @return the Roman Numeral representation
     */
    @VisibleForTesting
    protected static String convertFromInt(int n) {
        // numeric value of LETTERS_TO_VALUES key which is next to lower than the number
        int lowerBound = 0;
        StringBuilder RomanNumeral = new StringBuilder();

        while (n > 0) {
            // determine the lower bound to be passed into createNotation
            for (int i = 1; i <= LETTERS_TO_VALUES.size(); i++) {
                if (LETTERS_TO_VALUES.get(i - 1) <= n && n < LETTERS_TO_VALUES.get(i)) {
                    lowerBound = LETTERS_TO_VALUES.get(i - 1);
                } // end if
            } // end for

            // pass the number, the bound and StringBuilder to createNotation, which will update the
            // value for n
            n = createNotation(n, lowerBound, RomanNumeral);
        } // end while

        return RomanNumeral.toString();

    }// end convertFromInt

    // the purpose of this helper method is to evaluate the number, append the largest appropriate
    // Roman Numeral symbol to a StringBuilder, decrease the value of the number.
    private static int createNotation(int number, int min, StringBuilder RN) {
        // append the largest appropriate Roman Numeral symbol to the StringBuilder
        for (int i = 0; i < (number / min); i++) {
            RN.append(VALUES_TO_LETTERS.get(min));
        }

        // reduce the number by the amount of the numeric value associated with the symbol
        return number % min;

    }// end createNotation method

}// end RomanNumeral class


