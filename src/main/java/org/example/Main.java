package org.example;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String output = wordParser("Creativity is thinking-up new things. Innovation is doing new things!");
        System.out.println(output);
    }
    public static String wordParser(String input) {
        // IMPLEMENT YOUR SOLUTION HERE
        /*1) The first letter of the word
2) The number of distinct characters between first and last character
3) The last letter of the word.
For example, Smooth would become S3h.*/

        return Arrays.stream(input.split(" ")).map(word -> {
            if (word.length() <= 2) return word;
            String firstLetter = word.charAt(0) + "";
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(word);
            String lastLetter = !m.find()
                    ? word.charAt(word.length() - 1) + ""
                    : word.chars()
                    .mapToObj(value -> (char) value)
                    .map(character -> !(character + "").matches("[a-zA-Z]") ?
                            String.valueOf(word.indexOf(character)) : character + "")
                    .map(posicion -> posicion.chars().allMatch(Character::isDigit) ?
                            word.substring(Integer.parseInt(posicion) - 1) : "")
                    .collect(Collectors.joining());

            String content = word.substring(1, word.length() - lastLetter.length());
            String count = content.chars()
                    .mapToObj(value -> (char) value)
                    .distinct()
                    .map(Object::toString)
                    .collect(Collectors.joining(""));
            return firstLetter + count.length() + lastLetter;
        }).collect(Collectors.joining(" "));
    }
}