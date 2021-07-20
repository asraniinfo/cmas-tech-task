package net.cmas.system;

import net.cmas.system.util.Constants;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class WordForNumber {
    public static void main(String[] args) {
        WordForNumber wordForNumber = new WordForNumber();
        System.out.println("enter the range(number greater than 0)  \n for range one to twenty enter: 20 ");
        var input = new Scanner(System.in);
        String sentence = wordForNumber.getSentence(input.nextInt());
        System.out.println(sentence);
        wordForNumber.getWordCountData(sentence).forEach((key, value) -> System.out.println(String.format("%s: %s", key, value)));

    }

    private String getSentence(Integer range) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(1, range + 1).forEach(value -> stringBuilder.append(getWordForNumber(value)).append(" "));
        return stringBuilder.toString();
    }

    private String getWordForNumber(Integer number) {
        // return good if number contains 3
        if (ifThreePresent(number)) {
            return Constants.WORD_FOR_THREE;
        }

        // initiate string builder with a space(to add before previous character).
        StringBuilder sb = new StringBuilder();
        Constants.factorMap.forEach((key, value) -> {
            if (number % key == 0) {
                sb.append(value);
            }
        });

        if (sb.length() == 0) {
            sb.append(number);
        }
        return sb.toString();
    }

    private boolean ifThreePresent(Integer number) {
        while (number > 0) {
            if (number % 10 == 3)
                break;
            number = number / 10;
        }
        return (number > 0);
    }

    private Map<String, Integer> getWordCountData(String text) {
        Map<String, Integer> wordCounter = new LinkedHashMap<>();
        wordCounter.put(Constants.CMAS, 0);
        wordCounter.put(Constants.SYSTEMS, 0);
        wordCounter.put(Constants.CMAS_SYSTEMS, 0);
        wordCounter.put(Constants.GOOD, 0);
        wordCounter.put(Constants.INTEGER, 0);

        Arrays.stream(text.split("\\s")).collect(Collectors.toMap(word -> word, word -> 1, Integer::sum)).entrySet().stream().forEach(stringIntegerEntry -> {
            if (wordCounter.containsKey(stringIntegerEntry.getKey())) {
                wordCounter.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            } else {
                wordCounter.put(Constants.INTEGER, stringIntegerEntry.getValue() + wordCounter.get(Constants.INTEGER));
            }
        });
        return wordCounter;
    }
}
