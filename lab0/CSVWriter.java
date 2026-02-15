import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

// класс, который открывает файл и может возвращать слова

public class CSVWriter implements AutoCloseable{
    private Writer writer = null;
    double WordsAtAll;

    public CSVWriter(int WordsAtAll) {
        this.WordsAtAll = WordsAtAll;
        try{
            writer = new FileWriter("output.csv", false);
        }
        catch (IOException e){
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
    }

    public void write(Map.Entry<String, Integer> wordAndFrequency){ 
        try { 
            writer.write(wordAndFrequency.getKey());
            writer.write(", ");
            writer.write("" + wordAndFrequency.getValue());
            writer.write(", ");
            writer.write("" + wordAndFrequency.getValue()/ WordsAtAll);
            writer.write("\n");
            writer.flush(); 
        }
        catch (IOException e){
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
    }


    @Override
    public void close() throws IOException {
        if (writer != null) writer.close();
    }
}
