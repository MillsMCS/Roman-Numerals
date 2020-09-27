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
        // set the iteration to the current letter and the previous iteration to the previous letter
        previous_letter = letters[i - 1];
        current_letter = letters[i];

        // check to see if the character does not exist in the map
        // if that's the case then throw an error
        if (!LETTERS_TO_VALUES.containsKey(previous_letter)) {
          throw new IllegalArgumentException(letters[0] + " is not a valid roman numeral");
        } else if (!LETTERS_TO_VALUES.containsKey(current_letter)) {
          throw new IllegalArgumentException(letters[0] + " is not a valid roman numeral");
        }

        // compare the value of the current letter to the value of the previous letter
        // if current is smaller next, subtract current value from the previous value
        // if current is larger next, add the two values
        // if current is equal next, add the two values
        // this if tree should work for values 1 through 10
        if (LETTERS_TO_VALUES.get(previous_letter) == LETTERS_TO_VALUES.get(current_letter)) {
          total = total + LETTERS_TO_VALUES.get(current_letter);
        } else if (LETTERS_TO_VALUES.get(previous_letter) > LETTERS_TO_VALUES.get(current_letter)) {
          total = total + LETTERS_TO_VALUES.get(current_letter);
        } else if (LETTERS_TO_VALUES.get(previous_letter) < LETTERS_TO_VALUES.get(current_letter)) {
          total = LETTERS_TO_VALUES.get(current_letter) - total;
        } // end if
      } // end for loop
    } // end if statement

    return total;
  }// end convertFromString


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
    // create min and max variables to represent consecutive roman numeral values
    int min, max, nRounded, number=n;
    // create string variable to hold the strings passed back from createNotation
    String RN_piece;
    StringBuilder final_numeral = new StringBuilder();

    // loop the application until the number reaches 0
    while (number > 0) {
      if (number <= 5) {
        // set the min and max to be 1 and 5
        min = 1;
        max = 5;
        // pass the value, and the constraints to createNotation, the store the string
        RN_piece = createNotation(number, min, max);
        // alter the number, in this case bring it to 0
        number = 0;
        // append the string to the string builder
        final_numeral.append(RN_piece);
      } else if (number <= 10) {
        // set constraints
        min = 5;
        max = 10;
        // pass value/constraints to createNotation and store string
        RN_piece = createNotation(number, min, max);
        // alter number, bring it to 0
        number = 0;
        // append the string
        final_numeral.append(RN_piece);
      } else if (number <= 50) {
        // set constraints
        min = 10;
        max = 50;
        // round number down to nearest 10
        // pass THIS value to createNotation
        nRounded = (number / 10) * 10;
        // pass value/constraint to createNotation and store string
        RN_piece = createNotation(nRounded, min, max);
        // alter number to remove the 10 from the number
        number = number - nRounded;
        // append the string
        final_numeral.append(RN_piece);
      } else if (number <= 100) {
        // set constraints
        min = 50;
        max = 100;
        // round number down to nearest 10
        // pass THIS value to createNotation
        nRounded = (number / 10) * 10;
        // pass value/constraint to createNotation and store string
        RN_piece = createNotation(nRounded, min, max);
        // alter number to remove the 10 from the number
        number = number - nRounded;
        // append the string
        final_numeral.append(RN_piece);
      } else if (number <= 500) {
        // set constraints
        min = 100;
        max = 500;
        // round number down to nearest 100
        // pass THIS value to createNotation
        nRounded = (number / 100) * 100;
        // pass value/constraint to createNotation and store string
        RN_piece = createNotation(nRounded, min, max);
        // alter number to remove the 100 from the number
        number = number - nRounded;
        // append the string
        final_numeral.append(RN_piece);
      } else {
        // set constraints
        min = 500;
        max = 1000;
        // round number down to nearest 100
        // pass THIS value to createNotation
        nRounded = (number / 100) * 100;
        // pass value/constraint to createNotation and store string
        RN_piece = createNotation(nRounded, min, max);
        // alter number to remove the 100 from the number
        number = number - nRounded;
        // append the string
        final_numeral.append(RN_piece);
      } // end if statement
    } // end while

    return final_numeral.toString();
  }// end convertFromInt

  /**
   * Returns a part of the Roman Numeral representation of the main number.
   *
   * @param num the part of number to convert
   * @param min the minimum constraint value
   * @param max the maximum constraint value
   * @return the Roman Numeral representation
   */
  private static String createNotation(int num, int min, int max) {
    // create variable to hold the string
    // but we will need to use StringBuilder
    StringBuilder roman_numeral = new StringBuilder();

    if (num == min) {
      // this clause is for all numbers that are the lower constraint
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
    } else if (num == max) {
      // this clause is for all numbers that are the upper constraints
      roman_numeral.append(VALUES_TO_LETTERS.get(max));
    } else if (num == min * 2) {
      // this clause works for numbers in range of 1-5, 10-50, 100-500
      for (int i = 0; i < 2; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(min));
      }
    } else if (num == min * 3) {
      // this clause works for numbers in range of 1-5, 10-50, 100-500
      for (int i = 0; i < 3; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(min));
      }
    } else if (num == max - min) {
      // this clause works for numbers in range of 1-5, 10-50, 100-500
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      roman_numeral.append(VALUES_TO_LETTERS.get(max));
    } else if (num == min + 1) {
      // this clause works for numbers in range of 5-10
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      roman_numeral.append(VALUES_TO_LETTERS.get(1));
    } else if (num == min + 2) {
      // this clause works for numbers in range of 5-10
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      for (int i = 0; i < 2; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(1));
      }
    } else if (num == min + 3) {
      // this clause works for numbers in range of 5-10
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      for (int i = 0; i < 3; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(1));
      }
    } else if (num == min + 10) {
      // this clause works for numbers in range of 50-100
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      roman_numeral.append(VALUES_TO_LETTERS.get(10));
    } else if (num == min + 20) {
      // this clause works for numbers in range of 50-100
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      for (int i = 0; i < 2; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(10));
      }
    } else if (num == min + 30 && num != 40) {
      // this clause works for numbers in range of 50-100
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      for (int i = 0; i < 3; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(10));
      }
    } else if (num == max - 10) {
      // this clause is mainly for numbers like 40 and 90
      roman_numeral.append(VALUES_TO_LETTERS.get(10));
      roman_numeral.append(VALUES_TO_LETTERS.get(max));
    } else if (num == min + 100) {
      // this clause works for numbers in range of 500-1000
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      roman_numeral.append(VALUES_TO_LETTERS.get(100));
    } else if (num == min + 200) {
      // this clause works for numbers in range of 50-1000
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      for (int i = 0; i < 2; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(100));
      }
    } else if (num == min + 300) {
      // this clause works for numbers in range of 50-1000
      roman_numeral.append(VALUES_TO_LETTERS.get(min));
      for (int i = 0; i < 3; i++) {
        roman_numeral.append(VALUES_TO_LETTERS.get(100));
      }
    } else if (num == max - 100) {
      // this clause works for numbers in range of 50-1000
      roman_numeral.append(VALUES_TO_LETTERS.get(100));
      roman_numeral.append(VALUES_TO_LETTERS.get(max));
    } else if (num == max - 1) {
      // this clause is mainly for numbers containing as a last digit 9
      roman_numeral.append(VALUES_TO_LETTERS.get(1));
      roman_numeral.append(VALUES_TO_LETTERS.get(10));
    } // end if statement

    return roman_numeral.toString();
  }// end createNotation method

}// end RomanNumeral class


