package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static edu.mills.cs180a.RomanNumeral.*;

class RomanNumeralTest {
	//please remember, these tests WILL FAIL because we have not written any working code

	//TESTING convertToInteger METHOD
	
	//the purpose of this test is to convert proper roman numeral notation into the 
	//correct numeric value, only using Is
	//for this test, you pass a valid RN to the method and the code you will write later
	//should give you back the correct numeric value
	@Test
	void convertFromStringConvertsI() 
	{
		assertEquals(1, convertFromString("I"));
		assertEquals(2, convertFromString("II"));
		assertEquals(3, convertFromString("III"));
	}
	
	//this is a parameterized version of the first test, but a good thing to note is 
	//the use of @CsvSource. With @CsvSource, you have the ability to test more 
	//than one parameter at a time. In this test we are passing pairs of data, consisting
	//of a RN and its corresponding numeric value.
	@ParameterizedTest
	@CsvSource(value = {"I,1", "II,2", "III,3"})
	void convertToIntegerIs(String RN, String num)
	{
		assertEquals(Integer.parseInt(num), convertFromString(RN));
	}
	
	//this test takes input that is not proper RN notation and throws an exception error
	@Test
	void convertToIntegerThrowsExceptionForIllegalInput() 
	{
		assertThrows(IllegalArgumentException.class, 
				() -> convertFromString("F"));
	}
	
	//this test is a parameterized verison of the test above
	@ParameterizedTest
	@ValueSource(strings = {"RV" ,"FG" ,"IR",""})
	void convertToIntegerThrowsExceptionForIllegalInput2(String s) 
	{
		assertThrows(IllegalArgumentException.class, 
				() -> convertFromString(s));
	}

	
	//TESTING convertToText METHOD
	//DID NOT HAVE ENOUGH TIME TO IMPLEMENT convertToText
	
	//the purpose of this test is to take proper RN notation and convert it to the
	//correct numeric value
	@Test
	void convertFromInttMajorSymbols()
	{
		assertEquals("V", convertFromInt(5));
		assertEquals("X", convertFromInt(10));
		assertEquals("L", convertFromInt(50));
		assertEquals("C", convertFromInt(100));
		assertEquals("D", convertFromInt(500));
		assertEquals("M", convertFromInt(1000));
	}

	//the purpose of this test is to ensure that all numbers are converted
	//to standard notaion rather than additive notation
	@ParameterizedTest
	@CsvSource(value = {"4,IV", "9,IX", "40,XL"})
	void convertFromIntConvertToStandardNotation(String s, String exp)
	{
		assertEquals(exp, convertFromInt(Integer.parseInt(s)));
	}
	
	//the purpose of this test is to throw an exception error for numeric 
	//values that can't be represented in RN notation
	@ParameterizedTest
	@ValueSource(ints = {0, -1})
	void convertFromIntThrowsExceptionForIllegalInput(int n) 
	{
		assertThrows(IllegalArgumentException.class,
				() -> convertFromInt(n));
	}
}
