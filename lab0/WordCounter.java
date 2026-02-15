import java.util.HashMap;
import java.util.Map;

// класс, который считает слова, а затем может выдать самое большое (несколько раз...)

public class WordCounter {
    private Map<String, Integer> countOfWords;
    private int WordsAtAll = 0;

    public WordCounter(){
        countOfWords = new HashMap<>();
    }

    public void countWord(String word){
        WordsAtAll +=1;
        countOfWords.put(word, countOfWords.getOrDefault(word, 0) + 1);
    } // берёт значение по ключу если есть или 0, прибавляет 1 и записывает по тому же ключу, значит заменяет...

    // Map.Entry<String, Integer> тип пары,
    //часть множества .entrySet(), которое делает из map множетсво пар ключ и значение,
    // а не доступ по ключу...
    public Map.Entry<String, Integer> getLongestWord() { //возвращает самое частое слово и сколько раз оно встретилось
        Map.Entry<String, Integer> longestWord = null;
        for (Map.Entry<String, Integer> chosenWord : countOfWords.entrySet()){
            if (longestWord == null || chosenWord.getValue() > longestWord.getValue()) longestWord = chosenWord;
        }
        if (longestWord == null) return null;
        countOfWords.remove(longestWord.getKey());
        return longestWord;
    }

    public int getWordsAtAll(){
        return WordsAtAll;
    }
}
