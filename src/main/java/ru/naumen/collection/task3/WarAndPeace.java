package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace
{
    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        final int TOP_WORDS_COUNT = 10;
        /**
         * Выбран LinkedHashMap для операций вставки и поиска за O(1),
         * так как String имеет хэш-функцию, не вызывающую коллизий,
         * а также для итерации за O(n)
         */
        final Map<String, Long> frequencyMap = new LinkedHashMap<>();

        /**
         * Выбран PriorityQueue для поддержания отсортированности слов по их частотам
         * Вставка и удаление за O(logN), N - 10
         */
        final PriorityQueue<Map.Entry<String, Long>> leastUsedWords = new PriorityQueue<>(
                TOP_WORDS_COUNT,
                (firstFrequencyMap, secondWordMap) -> Long.compare(secondWordMap.getValue(), firstFrequencyMap.getValue())
        );
        final PriorityQueue<Map.Entry<String, Long>> mostUsedWords = new PriorityQueue<>(
                TOP_WORDS_COUNT,
                Comparator.comparingLong(Map.Entry::getValue)
        );

        /** Перебор за O(n), поиск и вставка по O(1) */
        new WordParser(WAR_AND_PEACE_FILE_PATH)
                .forEachWord(word -> {
                    long frequency = frequencyMap.getOrDefault(word, 0L);
                    frequencyMap.put(word, frequency + 1);
                });

        /** Перебор за O(n), вставка и удаление за O(log10) */
        for (Map.Entry<String, Long> entry : frequencyMap.entrySet()) {
            mostUsedWords.offer(entry);
            leastUsedWords.offer(entry);

            if (mostUsedWords.size() > TOP_WORDS_COUNT) {
                mostUsedWords.poll();
            }

            if (leastUsedWords.size() > TOP_WORDS_COUNT) {
                leastUsedWords.poll();
            }
        }

        System.out.printf("ТОП %d наиболее используемых слов:\n", TOP_WORDS_COUNT);
        for (int i = TOP_WORDS_COUNT; i >= 1; i--) {
            Map.Entry<String, Long> wordStats = mostUsedWords.poll();
            System.out.printf("%d. %s - %d раз\n", i, wordStats.getKey(), wordStats.getValue());
        }

        System.out.printf("\nТОП %d наименее используемых слов:\n", TOP_WORDS_COUNT);
        for (int i = TOP_WORDS_COUNT; i >= 1; i--) {
            Map.Entry<String, Long> wordStats = leastUsedWords.poll();
            System.out.printf("%d. %s - %d раз\n", i, wordStats.getKey(), wordStats.getValue());
        }

        /** Итого O(n + n * 2log10 + 2log10) */
    }
}
