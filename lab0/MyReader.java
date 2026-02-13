import java.io.*; // импорт всяких полезностей
import java.lang.*; // импорт полезностей 2 (IsLetterOrDigit может ещё чего-то)

// класс, который открывает файл и может возвращать слова

public class MyReader implements AutoCloseable{ 
    private Reader reader = null;
    // я же верно понимаю тут перед всем надо ставить уровень приватности?

     public MyReader(String filename){ // конструктор
        try { //FileInputStream - класс читатель байтов файла теперь можно снова вкладывать друг в друга
            this.reader = new InputStreamReader(new FileInputStream(filename));// InputStreamReader - класс переводчик байтов в символы
            // Везде ли нужен this?
        }
        catch (IOException e){
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
    }

    public String getWord(){ // метод отдающий слова
         try {
             char[] symvol = new char[1];
             int coutOfSymvols = this.reader.read(symvol); // если -1 выдаст значит конец потока, а иначе даёт количество символов считанных
             while (!Character.isLetterOrDigit(symvol[0]) && coutOfSymvols >-1) { // идём по массиву пока не найдём слово
                 coutOfSymvols = this.reader.read(symvol);
             }
             String slovo = "";
             if (coutOfSymvols == -1) return "";
             while (Character.isLetterOrDigit(symvol[0]) && coutOfSymvols >-1){ // записываем слово
                 slovo += symvol[0];
                 coutOfSymvols = this.reader.read(symvol);
             }
             return slovo;
         }
         catch (IOException e){
             System.err.println("Error while reading file: " + e.getLocalizedMessage());
         }
         return ""; // даже в случае ошибки нужно что-то вренуть?
    }

    @Override
    public void close() throws IOException {
        if (reader != null) reader.close();
    }
}
