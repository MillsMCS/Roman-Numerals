package edu.mills.cs180a;

import java.util.Map;

/**
 * Immutable Roman Numeral, representing a value between {@link #MIN_VALUE} and
 * {@link #MAX_VALUE}, inclusive.
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
    public static final int MAX_VALUE = 10;

    @VisibleForTesting
    protected static final Map<Character, Integer> LETTERS_TO_VALUES = Map.of(
   	 'I', 1,
   	 'V', 5,
   	 'X', 10,
   	 'L', 50,
   	 'C', 100,
   	 'D', 500,
   	 'M', 1000
    );
   		 
    private final int value;
    private String text;

    /**
     * Constructs a newly-allocated {@code RomanNumeral} object that represents
     * the specified value. The argument must be in the range {@link #MIN_VALUE}
     * to {@link #MAX_VALUE}, inclusive.
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
   	 text = convertToText(value);
    }

    /**
     * Constructs a newly-allocated {@code RomanNumeral} object for the
     * specified text. The argument must be in the range {@link #MIN_VALUE}
     * to {@link #MAX_VALUE}, inclusive.
     *
     * @param text the Roman Numeral
     * @throws IllegalArgumentException if the argument is out of bounds
     */
    public RomanNumeral(String text) {
   	 this.text = text;
   	 value = convertToInteger(text);
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
     * Returns the number corresponding to the given string representing
     * a Roman Numeral.
     *
     * @param s the Roman Numeral
     * @return the corresponding number
     * @throws IllegalArgumentException if the argument is not a valid
     * Roman Numeral or if it is out of range
     */
    @VisibleForTesting
    protected static int convertToInteger(String s) 
    {
   	 	//GOAL: convert string (Roman Numerals) into numeric value
    	//NOTE: have a Map interface that has a list of defined keys and values
    	//we should utilize this interface, while writing our method
    	
    	//acquire input, compare it to the keys in the map
    	//return the numeric value
    	if (LETTERS_TO_VALUES.containsKey(s))
    	{
    		return LETTERS_TO_VALUES.get(s);
    	}
    	
    	//if the input does not match any of the keys in the map, return 0
    	return 0;
    }
    
    /**
     * Returns the Roman Numeral representation of the given number.
     *
     * @param n the number to convert
     * @return the Roman Numeral representation
     * @throws IllegalArgumentException if the number cannot be represented
     * by a Roman Numeral in the given range
     */
    @VisibleForTesting
    protected static String convertToText(int n) 
    {
    	//GOAL: convert number into strings(Roman Numerals)
    	//Refer to note in convertToInteger
    	
    	//acquire input, compare it to the values in the map
    	//return the key
    	if(n==MIN_VALUE)
    	{
    		LETTERS_TO_VALUES.containsValue(n);
    	}
    	else if (n==)
    	
   	 	return null;
    }
}//end RomanNumeral class


