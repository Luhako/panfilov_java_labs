import java.io.*; // импорт всяких полезностей
import java.lang.*; // импорт полезностей 2 (IsLetterOrDigit может ещё чего-то)

// класс, который открывает файл и может возвращать слова

// implements AutoCloseable наследование или вроде того интерфейса, от которого наследуются штуки с "автодеструктором"
// теперь надо переопределить метод close и он вызовется когда ресурс не будет нужен...
public class MyReader implements AutoCloseable{ // изначально Reader, но такой класс уже есть в java?
    private Reader reader = null; // ну да вот он класс для ввода символов
    // я же верно понимаю тут перед всем надо ставить уровень приватности?

     public MyReader(String filename){ // конструктор
        try (FileInputStream fis = new FileInputStream(filename);){ //FileInputStream - класс читатель байтов файла
            this.reader = new InputStreamReader(fis); // InputStreamReader - класс переводчик байтов в символы
            // Везде ли нужен this?
            /* Подобное было в задании... открытие файла может выдать исключение, и, похоже, его надо ловить,
        а не просто потом проверить открылось оно или нет, а теперь оно само закроется всё */
            //А что надо делать ещё в конструкторе кроме создания?
        }
        catch (IOException e){ 
        /* IOException — базовый класс для исключений,
        которые возникают при работе с файлами, каталогами и потоками.
        Ловим исключение открытия файла*/
            System.err.println("Error while reading file: " + e.getLocalizedMessage()); 
            // вывод ошибок в ошибочный поток?
        }
    }

    public String getWord(){ // метод отдающий слова
         try { // я так понимаю вокруг любой работы с файлами надо писать try catch
             char[] symvol = new char[0];
             int coutOfSymvols = this.reader.read(symvol); // если -1 выдаст значит конец потока
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
        if (reader != null) reader.close(); //похоже одну строчку после условия можно и так написать без {}
    }
}
