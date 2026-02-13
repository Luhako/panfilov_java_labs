import java.util.*; // импорт всяких полезностей

// класс, который считает слова, а затем может выдать самое большое (несколько раз...)

public class Chetovod {
    private Map<String, Integer> skolkoSlov; // какой-то особый инт нужен
    private int allWords = 0;

    public Chetovod(){ // конструктор
        skolkoSlov = new HashMap<>();
    }

    public void countSlovo(String slovo){ // метод счёта слов
        allWords+=1;
        skolkoSlov.put(slovo, skolkoSlov.getOrDefault(slovo, 0) + 1);
    } // берёт значение по ключу если есть или 0 прибавляет 1 и записывает по тому же ключу, значит заменяет?

    // Map.Entry<String, Integer> тип пары,
    //часть множества .entrySet(), которое делает из map множетсво пар ключ и значение, а не доступ по ключу...
    public Map.Entry<String, Integer> getBigWord() { //возвращает самое частое слово и сколько раз оно встретилось и удаляет его
        Map.Entry<String, Integer> bigSlovo = null;
        for (Map.Entry<String, Integer> chosenWord : skolkoSlov.entrySet()){
            if (bigSlovo == null || chosenWord.getValue() > bigSlovo.getValue()) bigSlovo = chosenWord;
        }
        if (bigSlovo == null) return null;
        skolkoSlov.remove(bigSlovo.getKey());
        return bigSlovo;
    }

    public int getAllWords(){
        return allWords;
    }
}
