import java.util.*; // импорт всяких полезностей

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //чтение с консоли для имени файла
        MyReader reader = new MyReader(scanner.nextLine());
        Chetovod chetovod = new Chetovod();
        String word = reader.getWord();
        while (word != ""){
            chetovod.countSlovo(word);
            word = reader.getWord();
        }
        MyWriter writer = new MyWriter(chetovod.getAllWords());
        Map.Entry<String, Integer> bigWordChastota = chetovod.getBigWord();
        while (bigWordChastota != null){
            writer.write(bigWordChastota);
            bigWordChastota = chetovod.getBigWord();
        }
    }
}
