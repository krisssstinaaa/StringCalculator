package org.krissssxxxx;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    //паттерн для роздільника
    private static final String pattern = "(?<=\\d)%s(?=\\d|-)";
    //паттерн для пошуку роздільників у рядку
    private static final Pattern del_pattern = Pattern.compile("(?:\\/\\/([^\\d\\s]+)\\n)?([\\s\\S]+)");
    public int add(String numbers) {
        //перевірка введеного користувачем рядка на наявність символів
        if (numbers.isEmpty()){
            return 0;
        }
        String n;
        //пошук в рядку роздільників, заданих користувачем, і чисел, що ними розділені
        Matcher matcher = del_pattern.matcher(numbers);
        matcher.matches();
        String delimiters = matcher.group(1);
        String strNumbers = matcher.group(2);
        //якщо не знайдено заданих користувачем роздільників, то застосовуються стандартні: кома та символ нового рядка
        if (delimiters == null){
            n = joinPattern(List.of("\n", ","));
        } else {
            n = parse(delimiters);
        }
        //створюється масив з чисел, які були у рядку
        String[] numbers_array = strNumbers.split(n);
        int sum = 0;
        //масив від'ємних чисел (при наявності)
        List<Integer> negatives = new ArrayList<>();
        //сума чисел
        for (String a : numbers_array){
            int int_a = Integer.parseInt(a);
            if (int_a < 0){
                negatives.add(int_a);
                continue;
            }
            if (int_a <= 1000) {
                sum += int_a;
            }
        }
        //перевірка на наявність від'ємних чисел і механізм запуску виключення
        if (!negatives.isEmpty()){
            throw new RuntimeException("У рядку знаходяться від'ємні числа!" + negatives.toString());
        }
        return sum;
    }
    //приватний допоміжний метод для пошуку кількох роздільників і створення патерну для них
    private String parse(String delimiters) {
        //перевірка рядка на наявність символів
        if (delimiters.isEmpty()) {
            throw new RuntimeException("Хибні роздільники!");
        }

        if (!delimiters.startsWith("[")) {
            //перевірка на те, чи є роздільник числом
            for (var c : delimiters.toCharArray()) {
                if (Character.isDigit(c) || c == '[' || c == ']') {
                    throw new RuntimeException("Хибні роздільники!");
                }
            }
            //додає до числа роздільників
            return joinPattern(List.of("\n", ",", delimiters));
        }

        List<String> strings = new ArrayList<>();
        String current = "";
        int number = 0;

        for (Character c : delimiters.toCharArray()) {
            //перевірка на те, чи є роздільник числом
            if (Character.isDigit(c)) {
                throw new RuntimeException("Хибні роздільники!");
            }
            //захоплюємо роздільник, всередині квадратних дужок
            if (c.equals('[')) {
                if (number != 0) {
                    throw new RuntimeException("Хибні роздільники!");
                }
                number++;
            }

            else if (c.equals(']')) {
                if (number != 1 || current.isEmpty()) {
                    throw new RuntimeException("Хибні роздільники!");
                }
                number--;
                strings.add(current);
                current = "";
            }

            else {
                if (number == 1) {
                    current += c;
                }
                else {
                    throw new RuntimeException("Хибні роздільники!");
                }
            }
        }

        if (strings.isEmpty()) {
            throw new RuntimeException("Хибні роздільники!");
        }
        strings.add("\n");
        strings.add(",");
        return joinPattern(strings);
    }
    //приватний допоміжний метод для створення паттерну
    private String createPattern(String del){

        return pattern.formatted(Pattern.quote(del));
    }
    //приватний допоміжний метод для з'єднання патернів кількох роздільників
    private String joinPattern(List<String> delimiters){
        List<String> patterns = new ArrayList<>();
        for (String del : delimiters){
            patterns.add(createPattern(del));
        }
        return String.join("|", patterns);
    }
}
