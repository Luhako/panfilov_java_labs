import java.io.*; // импорт всяких полезностей

public class MyReader { // изначально Reader, но мне кажется, или такой класс уже есть в java?
    private Reader reader = null; // ну да вот он класс для ввода символов

     public MyReader(String filename){ // конструктор
        try{ /* Это было в задании... задание файла может выдать исключение, и, похоже, его надо ловить,
        а не просто потом проверить открылось оно или нет */
            reader = new InputStreamReader(new FileInputStream(filename)); // попытка открыть файл?
        } /* FileInputStream - класс читатель байтов файла
        InputStreamReader - класс переводчик байтов в символы
        Запутанно? Но вроде что-то понятно...*/

        catch (IOException e){ /* IOException — базовый класс для исключений,
        которые возникают при работе с файлами, каталогами и потоками.
        Ловим исключение открытия файла*/
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
            // вывод ошибок в ошибочный поток?
        }

        /*finally { //Код, который выполняется в другом случае
            if (null != reader) // говорят это уже не актуально тк те классы сами освобождают ресурсы,
            // но их надо указывать в аргументе к try, а говорили, что само память очищает,
            // а ещё больше без вложенности!
            {
                try
                {
                    reader.close(); //Попытка закрыть если открыто
                }
                catch (IOException e) // Но ещё какое-то исключение?
                {
                    e.printStackTrace(System.err);
                }
            }
        } */
    }
}
