package net.cmas.system;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Unit test for simple App.
 */
class WordForNumberTest {

    private static final Map<Integer, String> integerToWordMap = Map.ofEntries(Map.entry(3, "good")
            , Map.entry(5, "systems")
            , Map.entry(13, "good")
            , Map.entry(15, "CMASsystems")
            , Map.entry(45, "CMASsystems")
            , Map.entry(100, "systems"));

    private static final Map<Integer, String> getIntegerToSentenceMap = Map.ofEntries(Map.entry(20, "1 2 good 4 systems CMAS 7 8 CMAS systems 11 CMAS good 14 CMASsystems 16 17 CMAS 19 systems ")
            , Map.entry(15, "1 2 good 4 systems CMAS 7 8 CMAS systems 11 CMAS good 14 CMASsystems ")
            , Map.entry(31, "1 2 good 4 systems CMAS 7 8 CMAS systems 11 CMAS good 14 CMASsystems 16 17 CMAS 19 systems CMAS 22 good CMAS systems 26 CMAS 28 29 good good "));



    /**
     * verify correctness of each string against a number
     * Reflection to test private method
     *
     * @param number number against which will get word
     * @throws NoSuchMethodException     reflection exception if method not found
     * @throws InvocationTargetException reflection exception if can't invoke method
     * @throws IllegalAccessException    reflection exception if Access can't access
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 13, 15, 45, 100})
    void shouldReturnCorrectWord(int number) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        WordForNumber wordForNumber = new WordForNumber();
        Method method = WordForNumber.class.getDeclaredMethod("getWordForNumber", Integer.class);
        method.setAccessible(true);
        if (integerToWordMap.containsKey(number)) {
            Assertions.assertEquals(integerToWordMap.get(number), method.invoke(wordForNumber, number), "Word is equal | Success");
        } else {
            Assertions.assertEquals(String.valueOf(number), method.invoke(wordForNumber, number), "Number is equal | Success");
        }
    }

    /**
     * verify correctness of complete string against a range
     * Reflection to test private method
     *
     * @param range range against which will get sentence
     * @throws NoSuchMethodException     reflection exception if method not found
     * @throws InvocationTargetException reflection exception if can't invoke method
     * @throws IllegalAccessException    reflection exception if Access can't access
     */
    @ParameterizedTest
    @ValueSource(ints = {20, 15, 31})
    void shouldReturnCorrectSentence(int range) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        WordForNumber wordForNumber = new WordForNumber();
        Method method = WordForNumber.class.getDeclaredMethod("getSentence", Integer.class);
        method.setAccessible(true);
        Assertions.assertEquals(getIntegerToSentenceMap.get(range), method.invoke(wordForNumber, range), "Sentence is equal | Success");
    }

    /**
     *  verify counter of words
     * @param text printed text
     * @param textCounter string value of wordCounter map
     * @throws NoSuchMethodException     reflection exception if method not found
     * @throws InvocationTargetException reflection exception if can't invoke method
     * @throws IllegalAccessException    reflection exception if Access can't access
     */
    @ParameterizedTest
    @CsvFileSource(resources = {"/testData/dataFile.csv"}, delimiter = ';')
    void shouldReturnCorrectWordOccurence(String text, String textCounter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        WordForNumber wordForNumber = new WordForNumber();
        Method method = WordForNumber.class.getDeclaredMethod("getWordCountData", String.class);
        method.setAccessible(true);
        Assertions.assertEquals(textCounter, method.invoke(wordForNumber, text).toString(), "Map counter is equal | Success");
    }
}
