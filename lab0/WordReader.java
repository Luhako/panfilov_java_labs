import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

// Класс, который открывает файл и может возвращать слова

public class WordReader implements AutoCloseable{
    private Reader reader = null;

     public WordReader(String filename){
        try {
            reader = new InputStreamReader(new FileInputStream(filename));
        }
        catch (IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
    }

    public String getWord(){
         try {
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
