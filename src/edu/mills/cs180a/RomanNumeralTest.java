package edu.mills.cs180a;

import static edu.mills.cs180a.RomanNumeral.convertFromInt;
import static edu.mills.cs180a.RomanNumeral.convertFromString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RomanNumeralTest {
    // test takes input that is not proper RN notation and throws an exception error
    @ParameterizedTest
    @ValueSource(strings = {"wL", "IIO", "iio", "xb", "XB", "DLR"})
    void convertFromString_ThrowIllegalArgumentExeption_InvalidInput(String s) {
        assertThrows(IllegalArgumentException.class, () -> convertFromString(s));
    }

    // checking against integers that are outside of MIN/MAX_VALUE
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 10000})
    void convertFromInt_ThrowIllegalArgumentException_OutOfBounds(int n) {
        assertThrows(IllegalArgumentException.class, () -> convertFromInt(n));
    }

    // checking that convertFromInt correctly outputs numbers 1 through 10
    @ParameterizedTest
    @CsvSource({"I,1", "II,2", "III,3", "IV,4", "V,5", "VI,6", "VII,7", "VIII,8", "IX,9", "X,10"})
    void convertFromInt_assertEquals_InttoString1through10(String RN, int num) {
        assertEquals(RN, convertFromInt(num));
    }

    // checking that convertFromInt correctly outputs markers
    @ParameterizedTest
    @CsvSource({"I,1", "V,5", "X,10", "L,50", "C,100", "D,500", "M,1000"})
    void convertFromInt_assertEquals_InttoStringMarkers(String RN, int num) {
        assertEquals(RN, convertFromInt(num));
    }

    // checking for all numbers comprised of 9s just up to 500
    @ParameterizedTest
    @CsvSource({"IX,9", "XIX,19", "XXIX,29", "XXXIX,39", "XLIX,49", "LIX,59", "LXIX,69", "LXXIX,79",
        "LXXXIX,89", "XCIX,99", "CIX,109", "CXIX,119", "CXXIX,129", "CXXXIX,139", "CXLIX,149",
        "CLIX,159", "CLXIX,169", "CLXXIX,179", "CLXXXIX,189", "CXCIX,199", "CCIX,209",
        "CCXIX,219", "CCXXIX,229", "CCXXXIX,239", "CCXLIX,249", "CCLIX,259", "CCLXIX,269",
        "CCLXXIX,279", "CCLXXXIX,289", "CCXCIX,299", "CCCIX,309", "CCCXIX,319", "CCCXXIX,329",
        "CCCXXXIX,339", "CCCXLIX,349", "CCCLIX,359", "CCCLXIX,369", "CCCLXXIX,379",
        "CCCLXXXIX,389", "CCCXCIX,399", "CDIX,409", "CDXIX,419", "CDXXIX,429", "CDXXXIX,439",
        "CDXLIX,449", "CDLIX,459", "CDLXIX,469", "CDLXXIX,479", "CDLXXXIX,489", "CDXCIX,499"})
    void convertFromInt_assertEquals_OneBeforeTens(String RN, int num) {
        assertEquals(RN, convertFromInt(num));
    }

    // checking against additives
    @ParameterizedTest
    @CsvSource({"IV,4", "IX,9", "XL,40", "XC,90", "CD,400", "CM,900"})
    void convertFromInt_assertEquals_StandardOverAdditive(String RN, int num) {
        assertEquals(RN, convertFromInt(num));
    }

    // checking in 10s
    @ParameterizedTest
    @CsvSource({"X,10", "XX,20", "XXX,30", "XL,40", "L,50", "LX,60", "LXX,70", "LXXX,80", "XC,90",
        "C,100", "CX,110", "CXX,120", "CXXX,130", "CXL,140", "CL,150", "CLX,160", "CLXX,170",
        "CLXXX,180", "CXC,190", "CC,200", "CCX,210", "CCXX,220", "CCXXX,230", "CCXL,240",
        "CCL,250", "CCLX,260", "CCLXX,270", "CCLXXX,280", "CCXC,290", "CCC,300", "CCCX,310",
        "CCCXX,320", "CCCXXX,330", "CCCXL,340", "CCCL,350", "CCCLX,360", "CCCLXX,370",
        "CCCLXXX,380", "CCCXC,390", "CD,400", "CDX,410", "CDXX,420", "CDXXX,430", "CDXL,440",
        "CDL,450", "CDLX,460", "CDLXX,470", "CDLXXX,480", "CDXC,490", "D,500", "DX,510",
        "DXX,520", "DXXX,530", "DXL,540", "DL,550", "DLX,560", "DLXX,570", "DLXXX,580",
        "DXC,590", "DC,600", "DCX,610", "DCXX,620", "DCXXX,630", "DCXL,640", "DCL,650",
        "DCLX,660", "DCLXX,670", "DCLXXX,680", "DCXC,690", "DCC,700", "DCCX,710", "DCCXX,720",
        "DCCXXX,730", "DCCXL,740", "DCCL,750", "DCCLX,760", "DCCLXX,770", "DCCLXXX,780",
        "DCCXC,790", "DCCC,800", "DCCCX,810", "DCCCXX,820", "DCCCXXX,830", "DCCCXL,840",
        "DCCCL,850", "DCCCLX,860", "DCCCLXX,870", "DCCCLXXX,880", "DCCCXC,890", "CM,900",
        "CMX,910", "CMXX,920", "CMXXX,930", "CMXL,940", "CML,950", "CMLX,960", "CMLXX,970",
        "CMLXXX,980", "CMXC,990", "M,1000", "MX,1010", "MXX,1020", "MXXX,1030", "MXL,1040",
        "ML,1050", "MLX,1060", "MLXX,1070", "MLXXX,1080", "MXC,1090", "MM,2000", "MMX,2010",
        "MMXX,2020", "MMXXX,2030", "MMXL,2040", "MML,2050", "MMLX,2060", "MMLXX,2070",
        "MMLXXX,2080", "MMXC,2090", "MMM, 3000", "MMMX,3010", "MMMXX,3020", "MMMXXX,3030",
        "MMMXL,3040", "MMML,3050", "MMMLX,3060", "MMMLXX,3070", "MMMLXXX,3080", "MMMXC,3090",
        "MMMM,4000", "MMMMX,4010", "MMMMXX,4020", "MMMMXXX,4030", "MMMMXL,4040", "MMMML,4050",
        "MMMMLX,4060", "MMMMLXX,4070", "MMMMLXXX,4080", "MMMMXC,4090", "MMMMM,5000",
        "MMMMMX,5010", "MMMMMXX,5020", "MMMMMXXX,5030", "MMMMMXL,5040", "MMMMML,5050",
        "MMMMMLX,5060", "MMMMMLXX,5070", "MMMMMLXXX,5080", "MMMMMXC,5090", "MMMMMM,6000",
        "MMMMMMX,6010", "MMMMMMXX,6020", "MMMMMMXXX,6030", "MMMMMMXL,6040", "MMMMMML,6050",
        "MMMMMMLX,6060", "MMMMMMLXX,6070", "MMMMMMLXXX,6080", "MMMMMMXC,6090", "MMMMMMM,7000",
        "MMMMMMMX,7010", "MMMMMMMXX,7020", "MMMMMMMXXX,7030", "MMMMMMMXL,7040", "MMMMMMML,7050",
        "MMMMMMMLX,7060", "MMMMMMMLXX,7070", "MMMMMMMLXXX,7080", "MMMMMMMXC,7090",
        "MMMMMMMM,8000", "MMMMMMMMX,8010", "MMMMMMMMXX,8020", "MMMMMMMMXXX,8030",
        "MMMMMMMMXL,8040", "MMMMMMMML,8050", "MMMMMMMMLX,8060", "MMMMMMMMLXX,8070",
        "MMMMMMMMLXXX,8080", "MMMMMMMMXC,8090", "MMMMMMMMM,9000", "MMMMMMMMMX,9010",
        "MMMMMMMMMXX,9020", "MMMMMMMMMXXX,9030", "MMMMMMMMMXL,9040", "MMMMMMMMML,9050",
        "MMMMMMMMMLX,9060", "MMMMMMMMMLXX,9070", "MMMMMMMMMLXXX,9080", "MMMMMMMMMXC,9090"})
    void convertFromInt_assertEquals_Tens(String RN, int num) {
        assertEquals(RN, convertFromInt(num));
    }

    // checking random numbers
    @ParameterizedTest
    @CsvSource({"CDXXIII,423", "CCCLXXIX,379", "CCXXXIV,234", "CDXXV,425", "CMLVI,956", "CCXLI,241",
        "CCCXCVIII,398", "DCCLXVI,766", "XXXVIII,38", "CLXXXVII,187", "XCIII,93", "II,2",
        "MMMMMMMMMXCIX,9099", "MMMMMMMMLXIII,8063", "MMMMMMMCDLVI,7456","MMLXXXII,2082"})
    void convertFromInt_assertEquals_CorrectNotationForRandInts(String RN, int num) {
        assertEquals(RN, convertFromInt(num));
    }

    // checking that convertFromString correctly outputs numbers 1 through 10
    @ParameterizedTest
    @CsvSource({"1,I", "2,II", "3,III", "4,IV", "5,V", "6,VI", "7,VII", "8,VIII", "9,IX", "10,X"})
    void convertFromString_assertEquals_StringtoInt1through10(int num, String RN) {
        assertEquals(num, convertFromString(RN));
    }

    // checking that convertFromString correctly outputs numbers 1 through 10
    @ParameterizedTest
    @CsvSource({"11,XI", "12,XII", "13,XIII", "14,XIV", "15,XV", "16,XVI", "17,XVII", "18,XVIII",
        "19,XIX", "20,XX"})
    void convertFromString_assertEquals_StringtoInt11through20(int num, String RN) {
        assertEquals(num, convertFromString(RN));
    }

    // checking that convertFromString correctly outputs number from 1-9999 in 10s
    @ParameterizedTest
    @CsvSource({"X,10", "XX,20", "XXX,30", "XL,40", "L,50", "LX,60", "LXX,70", "LXXX,80", "XC,90",
        "C,100", "CX,110", "CXX,120", "CXXX,130", "CXL,140", "CL,150", "CLX,160", "CLXX,170",
        "CLXXX,180", "CXC,190", "CC,200", "CCX,210", "CCXX,220", "CCXXX,230", "CCXL,240",
        "CCL,250", "CCLX,260", "CCLXX,270", "CCLXXX,280", "CCXC,290", "CCC,300", "CCCX,310",
        "CCCXX,320", "CCCXXX,330", "CCCXL,340", "CCCL,350", "CCCLX,360", "CCCLXX,370",
        "CCCLXXX,380", "CCCXC,390", "CD,400", "CDX,410", "CDXX,420", "CDXXX,430", "CDXL,440",
        "CDL,450", "CDLX,460", "CDLXX,470", "CDLXXX,480", "CDXC,490", "D,500", "DX,510",
        "DXX,520", "DXXX,530", "DXL,540", "DL,550", "DLX,560", "DLXX,570", "DLXXX,580",
        "DXC,590", "DC,600", "DCX,610", "DCXX,620", "DCXXX,630", "DCXL,640", "DCL,650",
        "DCLX,660", "DCLXX,670", "DCLXXX,680", "DCXC,690", "DCC,700", "DCCX,710", "DCCXX,720",
        "DCCXXX,730", "DCCXL,740", "DCCL,750", "DCCLX,760", "DCCLXX,770", "DCCLXXX,780",
        "DCCXC,790", "DCCC,800", "DCCCX,810", "DCCCXX,820", "DCCCXXX,830", "DCCCXL,840",
        "DCCCL,850", "DCCCLX,860", "DCCCLXX,870", "DCCCLXXX,880", "DCCCXC,890", "CM,900",
        "CMX,910", "CMXX,920", "CMXXX,930", "CMXL,940", "CML,950", "CMLX,960", "CMLXX,970",
        "CMLXXX,980", "CMXC,990", "M,1000", "MX,1010", "MXX,1020", "MXXX,1030", "MXL,1040",
        "ML,1050", "MLX,1060", "MLXX,1070", "MLXXX,1080", "MXC,1090", "MM,2000", "MMX,2010",
        "MMXX,2020", "MMXXX,2030", "MMXL,2040", "MML,2050", "MMLX,2060", "MMLXX,2070",
        "MMLXXX,2080", "MMXC,2090", "MMM, 3000", "MMMX,3010", "MMMXX,3020", "MMMXXX,3030",
        "MMMXL,3040", "MMML,3050", "MMMLX,3060", "MMMLXX,3070", "MMMLXXX,3080", "MMMXC,3090",
        "MMMM,4000", "MMMMX,4010", "MMMMXX,4020", "MMMMXXX,4030", "MMMMXL,4040", "MMMML,4050",
        "MMMMLX,4060", "MMMMLXX,4070", "MMMMLXXX,4080", "MMMMXC,4090", "MMMMM,5000",
        "MMMMMX,5010", "MMMMMXX,5020", "MMMMMXXX,5030", "MMMMMXL,5040", "MMMMML,5050",
        "MMMMMLX,5060", "MMMMMLXX,5070", "MMMMMLXXX,5080", "MMMMMXC,5090", "MMMMMM,6000",
        "MMMMMMX,6010", "MMMMMMXX,6020", "MMMMMMXXX,6030", "MMMMMMXL,6040", "MMMMMML,6050",
        "MMMMMMLX,6060", "MMMMMMLXX,6070", "MMMMMMLXXX,6080", "MMMMMMXC,6090", "MMMMMMM,7000",
        "MMMMMMMX,7010", "MMMMMMMXX,7020", "MMMMMMMXXX,7030", "MMMMMMMXL,7040", "MMMMMMML,7050",
        "MMMMMMMLX,7060", "MMMMMMMLXX,7070", "MMMMMMMLXXX,7080", "MMMMMMMXC,7090",
        "MMMMMMMM,8000", "MMMMMMMMX,8010", "MMMMMMMMXX,8020", "MMMMMMMMXXX,8030",
        "MMMMMMMMXL,8040", "MMMMMMMML,8050", "MMMMMMMMLX,8060", "MMMMMMMMLXX,8070",
        "MMMMMMMMLXXX,8080", "MMMMMMMMXC,8090", "MMMMMMMMM,9000", "MMMMMMMMMX,9010",
        "MMMMMMMMMXX,9020", "MMMMMMMMMXXX,9030", "MMMMMMMMMXL,9040", "MMMMMMMMML,9050",
        "MMMMMMMMMLX,9060", "MMMMMMMMMLXX,9070", "MMMMMMMMMLXXX,9080", "MMMMMMMMMXC,9090"})
    void convertFromString_assertEquals_Tens(String RN, int num) {
        assertEquals(num, convertFromString(RN));
    }

    // checking random numbers
    @ParameterizedTest
    @CsvSource({"CDXXIII,423", "CCCLXXIX,379", "CCXXXIV,234", "CDXXV,425", "CMLVI,956", "CCXLI,241",
        "CCCXCVIII,398", "DCCLXVI,766", "XXXVIII,38", "CLXXXVII,187", "XCIII,93", "II,2",
        "MMMMMMMMMXCIX,9099", "MMMMMMMMLXIII,8063", "MMMMMMMCDLVI,7456","MMLXXXII,2082"})
    void convertFromString_assertEquals_CorrectNotationForRandInts(String RN, int num) {
        assertEquals(num, convertFromString(RN));
    }

    @Test
    void convertFromString_ThrowIllegalArgumentException_EmptyString() {
        assertThrows(IllegalArgumentException.class, () -> convertFromString(""));
    }
}// end RomanNumeralTest class
