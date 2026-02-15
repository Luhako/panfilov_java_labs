import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

// Класс, который открывает файл и может возвращать слова

// implements AutoCloseable наследование или вроде того интерфейса, от которого наследуются штуки с "автодеструктором"
// теперь надо переопределить метод close и он вызовется когда ресурс не будет нужен...
public class WordReader implements AutoCloseable{
    private Reader reader = null; //класс для ввода символов

     public WordReader(String filename){
        try { //FileInputStream - класс читатель байтов файла, InputStreamReader - класс переводчик байтов в символы
            reader = new InputStreamReader(new FileInputStream(filename));
            /* Подобное было в задании... задание файла может выдать исключение, и, похоже, его надо ловить,
        а не просто потом проверить открылось оно или нет, а теперь оно само закроется всё */
        }
        catch (IOException e){
            /* IOException — базовый класс для исключений,
        которые возникают при работе с файлами, каталогами и потоками.
        Ловим исключение открытия файла*/
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
            // вывод ошибок в ошибочный поток
        }
    }

    public String getWord(){ // метод отдающий слова
         try { // я так понимаю вокруг любой работы с файлами надо писать try catch
             char[] symbol = new char[1];
             int countOfSymbols = reader.read(symbol); // если -1 выдаст значит конец потока, а иначе даёт количество символов считанных
             while (!Character.isLetterOrDigit(symbol[0]) && countOfSymbols >-1) { // идём по массиву пока не найдём слово
                 countOfSymbols = reader.read(symbol);
             }
             if (countOfSymbols == -1) return "";
             String word = "";
             
             while (Character.isLetterOrDigit(symbol[0]) && countOfSymbols >-1){ // записываем слово
                 word += symbol[0];
                 countOfSymbols = reader.read(symbol);
             }
             return word;
         }
         catch (IOException e){
             System.err.println("Error while reading file: " + e.getLocalizedMessage());
         }
         return ""; // даже в случае ошибки нужно что-то вернуть...
    }

    @Override
    public void close() throws IOException {
        if (reader != null) reader.close();
    }
}
