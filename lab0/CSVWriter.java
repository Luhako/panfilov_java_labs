import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

// класс, который открывает файл и может возвращать слова

// implements AutoCloseable наследование или вроде того интерфейса, от которого наследуются штуки с "автодеструктором"
// теперь надо переопределить метод close и он вызовется когда ресурс не будет нужен...
public class CSVWriter implements AutoCloseable{
    private Writer writer = null;
    double WordsAtAll;

    public CSVWriter(int WordsAtAll) {
        this.WordsAtAll = WordsAtAll;
        try{
            writer = new FileWriter("output.txt", false);
        }
        catch (IOException e){
            /* IOException — базовый класс для исключений,
        которые возникают при работе с файлами, каталогами и потоками.
        Ловим исключение открытия файла*/
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
            // вывод ошибок в ошибочный поток
        }
    }

    public void write(Map.Entry<String, Integer> wordAndFrequency){ //frequency = Частота по-английски
        try { // я так понимаю вокруг любой работы с файлами надо писать try catch
            writer.write(wordAndFrequency.getKey());
            writer.write(" ");
            writer.write("" + wordAndFrequency.getValue());
            writer.write(" ");
            writer.write("" + wordAndFrequency.getValue()/ WordsAtAll);
            writer.write("\n");
            writer.flush(); // нужен для записи данных в файл или в буфер иначе они остаются
            // там, где могут пропасть при закрытии потока
        }
        catch (IOException e){
            /* IOException — базовый класс для исключений,
        которые возникают при работе с файлами, каталогами и потоками.
        Ловим исключение открытия файла*/
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
            // вывод ошибок в ошибочный поток?
        }
    }


    @Override
    public void close() throws IOException {
        if (writer != null) writer.close();
    }
}
