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
  protected static final Map<Character, Integer> LETTERS_TO_VALUES =
      Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

  @VisibleForTesting
  protected static final Map<Integer, Character> VALUES_TO_LETTERS =
      Map.of(1, 'I', 5, 'V', 10, 'X', 50, 'L', 100, 'C', 500, 'D', 1000, 'M');

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
   * @throws IllegalArgumentException if the argument is not a valid Roman Numeral or if it is out
   *         of range
   */
  @VisibleForTesting
  protected static int convertFromString(String s) {
    // GOAL: convert string (Roman Numerals) into numeric value
    // NOTE: have a Map interface that has a list of defined keys and values
    // we should utilize this interface, while writing our method

    // acquire input, compare it to the keys in the map, return the numeric value

    // take string and break it down to single characters and save them into an array
    char[] letter = s.toCharArray();

    // keep track of the numeric value
    int total = 0;
    // keep track of where you are in the array
    char current_letter, next_letter;

    // cycle through the array list
    for (int i = 0; i < letter.length; i++) {
      // set the iteration to the current letter
      current_letter = letter[i];
      next_letter = letter[i + 1];

      // check to see if the character does not exist in the map
      // if that's the case then throw an error
      if (LETTERS_TO_VALUES.get(current_letter) == null) {
        throw new IllegalArgumentException(current_letter + "is not a valid roman numeral");
      } // end if

      // Letters_to_value.get(s[i]).getValue()

      // compare the current letter to the next letter
      // if the current letter is smaller than the next letter, you subtract that value from the
      // total
      // if the current letter is larger than the next letter, you add that value to the total
      if (LETTERS_TO_VALUES.get(current_letter) < LETTERS_TO_VALUES.get(next_letter)) {
        total -= LETTERS_TO_VALUES.get(current_letter);
      } else {
        total += LETTERS_TO_VALUES.get(current_letter);
      } // end ifS

    } // end for loop

    return total;
  }// end convertToInteger

  /**
   * Returns the Roman Numeral representation of the given number.
   *
   * @param n the number to convert
   * @return the Roman Numeral representation
   * @throws IllegalArgumentException if the number cannot be represented by a Roman Numeral in the
   *         given range
   */
  @VisibleForTesting
  protected static String convertFromInt(int n) {
    // GOAL: convert number into strings(Roman Numerals)
    // Refer to note in convertToInteger

    // acquire input, compare it to the values in the map
    // return the key

    // create min and max variables to represent consecutive roman numeral values
    int min, max, n_rounded;
    // create string variable to hold the strings passed back from createNotation
    String RN_piece;
    StringBuilder final_numeral = new StringBuilder();

    // loop the application until the number reaches 0
    while (n > 0) {
      if (n <= 5) {
        // set the min and max to be 1 and 5
        min = 1;
        max = 5;
        // pass the value, and the constraints to createNotation, the store the string
        RN_piece = createNotation(n, min, max);
        // alter the number, in this case bring it to 0
        n = 0;
        // append the string to the string builder
        final_numeral.append(RN_piece);
      } else if (n <= 10) {
        // set constraints
        min = 5;
        max = 10;
        // pass value/constraints to createNotation and store string
        RN_piece = createNotation(n, min, max);
        // alter number, bring it to 0
        n = 0;
        // append the string
        final_numeral.append(RN_piece);
      } else if (n <= 50) {
        // set constraints
        min = 10;
        max = 50;
        // round number down to nearest 10
        // pass THIS value to createNotation
        n_rounded = n - (n / 10);
        // pass value/constraint to createNotation and store string
        RN_piece = createNotation(n_rounded, min, max);
        // alter number
        n = n - n_rounded;
        // append the string
        final_numeral.append(RN_piece);
      } else if (n <= 100) {
        // set constraints
        min = 50;
        max = 100;
        // round number down to nearest 10
        // pass THIS value to createNotation
        n_rounded = n - (n / 10);
        // pass value/constraint to createNotation and store string
        RN_piece = createNotation(n_rounded, min, max);
        // alter number, but using n_rounded
        n = n - n_rounded;
        // append the string
        final_numeral.append(RN_piece);
      } else if (n <= 500) {
        // set constraints
        min = 100;
        max = 500;
        // round number down to nearest 10
        // pass THIS value to createNotation
        n_rounded = n - (n / 10);
        // pass value/constraint to createNotation and store string
        RN_piece = createNotation(n_rounded, min, max);
        // alter number
        n = n - n_rounded;
        // append the string
        final_numeral.append(RN_piece);
      } else {
        // set constraints
        min = 500;
        max = 1000;
        // round number down to nearest 10
        // pass THIS value to createNotation
        n_rounded = n - (n / 10);
        // pass value/constraint to createNotation and store string
        RN_piece = createNotation(n_rounded, min, max);
        // alter number
        n = n - n_rounded;
        // append the string
        final_numeral.append(RN_piece);
      } // end if statment
    } // end while

    return final_numeral.toString();
  }// end convertToText

  /**
   * Returns a part of the Roman Numeral representation of the main number.
   *
   * @param num the part of number to convert
   * @param min the minimum constraint value
   * @param max the maximum constraint value
   * @return the Roman Numeral representation
   */
  // helper method for convertToText
  private static String createNotation(int num, int min, int max) {
    // create variable to hold the string
    // but we will need to use StringBuilder
    StringBuilder roman_numeral = new StringBuilder();

    if (num == min) {
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
    } else if (num == min * 2) {
      for (int i = 0; i < 2; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(min));
      }
    } else if (num == min * 3) {
      for (int i = 0; i < 3; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(min));
      }
    } else if (num == max - 1) {
      roman_numeral.append(VALUES_TO_LETTERS.get(1));
      roman_numeral.append(VALUES_TO_LETTERS.get(max));
    } else if (num == min + 10) {
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      roman_numeral.append(VALUES_TO_LETTERS.get(10));
    } else if (num == min + 20) {
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      for (int i = 0; i < 2; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(10));
      }
    } else if (num == min + 30) {
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      for (int i = 0; i < 3; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(10));
      }
    } else if (num == max - 10) {
      roman_numeral.append(VALUES_TO_LETTERS.get(10));
      roman_numeral.append(VALUES_TO_LETTERS.get(max));
    } else {
      roman_numeral.append(VALUES_TO_LETTERS.get(max));
    }

    return roman_numeral.toString();

  }// end createNotation method

}// end RomanNumeral class


