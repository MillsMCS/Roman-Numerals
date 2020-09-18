package edu.mills.cs180a;

import static edu.mills.cs180a.RomanNumeral.convertFromInt;
import static edu.mills.cs180a.RomanNumeral.convertFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RomanNumeralTest {
  // //please remember, these tests WILL FAIL because we have not written any working code
  //
  // //TESTING convertFromString METHOD
  //
  // //the purpose of this test is to convert proper roman numeral notation into the
  // //correct numeric value, only using Is
  // //for this test, you pass a valid RN to the method and the code you will write later
  // //should give you back the correct numeric value
  // @Test
  // void convertFromStringConvertsI()
  // {
  // assertEquals(1, convertFromString("I"));
  // assertEquals(2, convertFromString("II"));
  // assertEquals(3, convertFromString("III"));
  // }
  //
  // //this is a parameterized version of the first test, but a good thing to note is
  // //the use of @CsvSource. With @CsvSource, you have the ability to test more
  // //than one parameter at a time. In this test we are passing pairs of data, consisting
  // //of a RN and its corresponding numeric value.
  // @ParameterizedTest
  // @CsvSource(value = {"I,1", "II,2", "III,3"})
  // void convertToIntegerIs(String RN, String num)
  // {
  // assertEquals(Integer.parseInt(num), convertFromString(RN));
  // }
  //
  // //this test takes input that is not proper RN notation and throws an exception error
  // @Test
  // void convertToIntegerThrowsExceptionForIllegalInput()
  // {
  // assertThrows(IllegalArgumentException.class,
  // () -> convertFromString("F"));
  // }
  //
  // //this test is a parameterized verison of the test above
  // @ParameterizedTest
  // @ValueSource(strings = {"RV" ,"FG" ,"IR",""})
  // void convertToIntegerThrowsExceptionForIllegalInput2(String s)
  // {
  // assertThrows(IllegalArgumentException.class,
  // () -> convertFromString(s));
  // }
  //
  //
  // //TESTING convertFromInt METHOD
  // //DID NOT HAVE ENOUGH TIME TO IMPLEMENT convertToText
  //
  // //the purpose of this test is to take proper RN notation and convert it to the
  // //correct numeric value
  // @Test
  // void convertFromIntMajorSymbols()
  // {
  // assertEquals("V", convertFromInt(5));
  // assertEquals("X", convertFromInt(10));
  // assertEquals("L", convertFromInt(50));
  // assertEquals("C", convertFromInt(100));
  // assertEquals("D", convertFromInt(500));
  // assertEquals("M", convertFromInt(1000));
  // }
  //
  // //the purpose of this test is to ensure that all numbers are converted
  // //to standard notaion rather than additive notation
  // @ParameterizedTest
  // @CsvSource(value = {"4,IV", "9,IX", "40,XL"})
  // void convertFromIntConvertToStandardNotation(String s, String exp)
  // {
  // assertEquals(exp, convertFromInt(Integer.parseInt(s)));
  // }
  //
  // //the purpose of this test is to throw an exception error for numeric
  // //values that can't be represented in RN notation
  // @ParameterizedTest
  // @ValueSource(ints = {0, -1})
  // void convertFromIntThrowsExceptionForIllegalInput(int n)
  // {
  // assertThrows(IllegalArgumentException.class,
  // () -> convertFromInt(n));
  // }

  // // tests for convertFromString method
  // // checking for empty string input
  // @Test
  // void convertFromString_ThrowIllegalArguementException_EmptyString()
  // {
  // assertThrows(IllegalArgumentException.class,
  // () -> convertFromString(""));
  // }
  //
  // // checking against invalid input such as 0 and negatives
  // @ParameterizedTest
  // @ValueSource(ints = {0})
  // void convertFromInt_ThrowIllegalArgumentException_InvalidInput(int n)
  // {
  // assertThrows(IllegalArgumentException.class,
  // () -> convertFromInt(n));
  // } GET BACK TO THESE LATER!!!

  // checking that convertFromInt correctly outputs numbers 1 through 10
  @ParameterizedTest
  @CsvSource({"I,1", "II,2", "III,3", "IV,4", "V,5", "VI,6", "VII,7", "VIII,8", "IX,9", "X,10"})
  void convertFromInt_assertEquals_InttoString1through10(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking that convertFromInt correctly outputs markers
  @ParameterizedTest
  @CsvSource({"I,1", "V,5", "X,10", "L,50", "C,100", "D,500", "M,1000"})
  void convertFromInt_assertEquals_InttoStringMarkers(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking for all numbers comprised of 9s just up to 500
  @ParameterizedTest
  @CsvSource({"IX,9", "XIX,19", "XXIX,29", "XXXIX,39", "XLIX,49", "LIX,59", "LXIX,69", "LXXIX,79",
    "LXXXIX,89", "XCIX,99", "CIX,109", "CXIX,119", "CXXIX,129", "CXXXIX,139", "CXLIX,149",
    "CLIX,159", "CLXIX,169", "CLXXIX,179", "CLXXXIX,189", "CXCIX,199", "CCIX,209", "CCXIX,219",
    "CCXXIX,229", "CCXXXIX,239", "CCXLIX,249", "CCLIX,259", "CCLXIX,269", "CCLXXIX,279",
    "CCLXXXIX,289", "CCXCIX,299", "CCCIX,309", "CCCXIX,319", "CCCXXIX,329", "CCCXXXIX,339",
    "CCCXLIX,349", "CCCLIX,359", "CCCLXIX,369", "CCCLXXIX,379", "CCCLXXXIX,389", "CCCXCIX,399",
    "CDIX,409", "CDXIX,419", "CDXXIX,429", "CDXXXIX,439", "CDXLIX,449", "CDLIX,459", "CDLXIX,469",
    "CDLXXIX,479", "CDLXXXIX,489", "CDXCIX,499"})
  void convertFromInt_assertEquals_OneBeforeTens(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking against additives
  @ParameterizedTest
  @CsvSource({"IV,4", "IX,9", "XL,40", "XC,90", "CD,400", "CM,900"})
  void convertFromInt_assertEquals_StandardOverAdditive(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking in 10s
  @ParameterizedTest
  @CsvSource({"X,10", "XX,20", "XXX,30", "XL,40", "L,50", "LX,60", "LXX,70", "LXXX,80", "XC,90",
    "C,100", "CX,110", "CXX,120", "CXXX,130", "CXL,140", "CL,150", "CLX,160", "CLXX,170",
    "CLXXX,180", "CXC,190", "CC,200", "CCX,210", "CCXX,220", "CCXXX,230", "CCXL,240", "CCL,250",
    "CCLX,260", "CCLXX,270", "CCLXXX,280", "CCXC,290", "CCC,300", "CCCX,310", "CCCXX,320",
    "CCCXXX,330", "CCCXL,340", "CCCL,350", "CCCLX,360", "CCCLXX,370", "CCCLXXX,380", "CCCXC,390",
    "CD,400", "CDX,410", "CDXX,420", "CDXXX,430", "CDXL,440", "CDL,450", "CDLX,460", "CDLXX,470",
    "CDLXXX,480", "CDXC,490", "D,500", "DX,510", "DXX,520", "DXXX,530", "DXL,540", "DL,550",
    "DLX,560", "DLXX,570", "DLXXX,580", "DXC,590", "DC,600", "DCX,610", "DCXX,620", "DCXXX,630",
    "DCXL,640", "DCL,650", "DCLX,660", "DCLXX,670", "DCLXXX,680", "DCXC,690", "DCC,700",
    "DCCX,710", "DCCXX,720", "DCCXXX,730", "DCCXL,740", "DCCL,750", "DCCLX,760", "DCCLXX,770",
    "DCCLXXX,780", "DCCXC,790", "DCCC,800", "DCCCX,810", "DCCCXX,820", "DCCCXXX,830",
    "DCCCXL,840", "DCCCL,850", "DCCCLX,860", "DCCCLXX,870", "DCCCLXXX,880", "DCCCXC,890",
    "CM,900", "CMX,910", "CMXX,920", "CMXXX,930", "CMXL,940", "CML,950", "CMLX,960", "CMLXX,970",
    "CMLXXX,980", "CMXC,990", "M,1000"})
  void convertFromInt_assertEquals_Tens(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking random numbers
  @ParameterizedTest
  @CsvSource({"CDXXIII,423", "CCCLXXIX,379", "CCXXXIV,234", "CDXXV,425", "CMLVI,956", "CCXLI,241",
    "CCCXCVIII,398", "DCCLXVI,766", "XXXVIII,38", "CLXXXVII,187", "XCIII,93", "II,2"})
  void convertFromInt_assertEquals_CorrectNotationForRandInts(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking that convertFromString correctly outputs numbers 1 through 10
  @ParameterizedTest
  @CsvSource({"1,I", "2,II", "3,III", "4,IV", "5,V", "6,VI", "7,VII", "8,VIII", "9,IX", "10,X"})
  void convertFromString_assertEquals_StringtoInt1through10(String num, String RN) {
    assertEquals(Integer.parseInt(num), convertFromString(RN));
  }

  // checking that convertFromString correctly outputs numbers 1 through 10
  @ParameterizedTest
  @CsvSource({"11,XI", "12,XII", "13,XIII", "14,XIV", "15,XV", "16,XVI", "17,XVII", "18,XVIII",
    "19,XIX", "20,XX"})
  void convertFromString_assertEquals_StringtoInt11through20(String num, String RN) {
    assertEquals(Integer.parseInt(num), convertFromString(RN));
  }
}// end RomanNumeralTest class
