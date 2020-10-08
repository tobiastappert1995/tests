
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestCalculateMedian {

    private Double[] parseToArray(String test) throws NumberFormatException {
        String[] str = test.split(",");
            int size = str.length;


            Double[] arr = new Double[size];
            for (int i = 0; i < size; i++) {
                arr[i] = Double.parseDouble(str[i]);
            }
            return arr;
    }

    public boolean checkValues(Double[] array){
        for (Double aDouble : array) {
            if (aDouble > 10000 || aDouble < -10000) {
                System.out.println("number is to small or big.");
                return true;
            }
        }
        return false;
    }

    private Double calculateMedian(String valueList) {

        //check if List is empty
        if (valueList.length() == 0) {
            return 0.0;
        }

        Double[] parsedArray = parseToArray(valueList);

        if(checkValues(parsedArray)){
            System.out.println("a number is over 10000");
            return 0.0;
        }

        if(parsedArray.length > 10000){
            System.out.println("number is to big");
            return 0.0;
        }

        Arrays.sort(parsedArray);

        int length = parsedArray.length;


        if (length % 2 > 0) {
            int pos = length / 2;

            return parsedArray[pos];
        } else {
            int pos1 = (length / 2) - 1;
            int pos2 = (length / 2);

            Double valuePosition1 = parsedArray[pos1];
            Double valuePosition2 = parsedArray[pos2];

            return (valuePosition1 + valuePosition2) / 2;
        }
    }

    //Test1
    @ParameterizedTest
    @ValueSource(strings = {"1,7,21,32"})
    void checSortedkEvenList(String test) {
        assertThat(calculateMedian(test)).isEqualTo(14);
    }

    //Test2
    @ParameterizedTest
    @ValueSource(strings = {"7,32,21,1"})
    void checkNotSortedkEvenList(String test) {
        assertThat(calculateMedian(test)).isEqualTo(14);
    }

    //Test3
    @ParameterizedTest
    @ValueSource(strings = {"hallo, hi, fünfzehn"})
    void testNoneAcceptedValueAllStrings(String test) {
        assertThrows(NumberFormatException.class, () -> {
            calculateMedian(test);
        });
    }

    //Test4
    @ParameterizedTest
    @ValueSource(strings = {"12,dreiundzwanzig,44"})
    void testNoneAcceptedValue(String test) {
        assertThrows(NumberFormatException.class, () -> {
            calculateMedian(test);
        });
    }

    //Test5
    @ParameterizedTest
    @ValueSource(strings = {"true,hallo,23"})
    void testNoneAcceptedValueWithBoolean(String test) {
        assertThrows(NumberFormatException.class, () -> {
            calculateMedian(test);
        });
    }

    //Test6
    @ParameterizedTest
    @ValueSource(strings = {"-2,-12,-23"})
    void testNegativeValues(String test) {
        assertThat(calculateMedian(test)).isEqualTo(-12);
    }

    //Test7
    @ParameterizedTest
    @ValueSource(strings = {"2,4,6"})
    void testUnevenList(String test) {
            assertThat(calculateMedian(test)).isEqualTo(4);
    }

    //Test8
    @ParameterizedTest
    @ValueSource(strings = {"1,3,5,7"})
    void testEvenList(String test) {
        assertThat(calculateMedian(test)).isEqualTo(4);
    }

    //Test9
    @ParameterizedTest
    @ValueSource(strings = {""})
    void testNoneValueList(String test) {
        assertThat(calculateMedian(test)).isZero();
    }

    //Test10
    @ParameterizedTest
    @ValueSource(strings = {"123,63728,65536"})
    void testValuesOutOfBorderZonePositive(String test) {
        assertThat(calculateMedian(test)).isEqualTo(0.0);
    }

    //Test10
    @ParameterizedTest
    @ValueSource(strings = {"123,63728,-65536"})
    void testValuesOutOfZoneNegative(String test) {
        assertThat(calculateMedian(test)).isEqualTo(0.0);
    }

    //Test11
    @ParameterizedTest
    @ValueSource(strings = {"12.5,23.8,38.1"})
    void testValuesDouble(String test) {
        assertThat(calculateMedian(test)).isEqualTo(23.8);
    }
}