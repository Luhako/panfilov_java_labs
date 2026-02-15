import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //чтение с консоли для имени файла
        WordReader reader = new WordReader(scanner.nextLine());
        WordCounter wordCounter = new WordCounter();
        String word = reader.getWord();
        while (word != ""){ // возвращает пустое слово когда кончился файл
            wordCounter.countWord(word);
            word = reader.getWord();
        }
        CSVWriter writer = new CSVWriter(wordCounter.getWordsAtAll());
        Map.Entry<String, Integer> wordAndFrequency = wordCounter.getLongestWord();
        while (wordAndFrequency != null){
            writer.write(wordAndFrequency);
            wordAndFrequency = wordCounter.getLongestWord();
        }
    }
}
