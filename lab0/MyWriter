import java.io.*; // импорт всяких полезностей
import java.lang.*; // импорт полезностей 2 (IsLetterOrDigit может ещё чего-то)
import java.util.Map;

// класс, который открывает файл и может возвращать слова

public class MyWriter implements AutoCloseable{ 
    private Writer writer = null;
    double allWords;
    // я же верно понимаю тут перед всем надо ставить уровень приватности?

    public MyWriter(int allWords) { // конструктор
        this.allWords = allWords;
        try{
            this.writer = new FileWriter("output.txt", false);
        }
        catch (IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
    }

    public void write(Map.Entry<String, Integer> wordChastota){
        try { 
            writer.write(wordChastota.getKey());
            writer.write(" ");
            writer.write("" + wordChastota.getValue());
            writer.write(" ");
            writer.write("" + wordChastota.getValue()/allWords);
            writer.write("\n");
            writer.flush(); // нужен для записи данных в файл или в буфер иначе они остаются
            // там где могут пропасть при закрытии потока
        }
        catch (IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());

        }
    }


    @Override
    public void close() throws IOException {
        if (writer != null) writer.close();
    }
}
