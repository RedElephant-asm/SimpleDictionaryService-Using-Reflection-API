import org.SimpleDictionaryService.Dictionary;
import org.SimpleDictionaryService.DictionaryService;
import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        String filepath = "D:\\Code\\Java\\projects\\SimpleDictionaryService-Using-Reflection-API\\src\\TEXT.txt";
        try {
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(filepath));
            System.out.println(streamReader.getEncoding());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Dictionary dictionary = new Dictionary(filepath, Language.UNICODE_RUSSIAN, Language.UNICODE_FOURLATINLETTERS, Encoding.UTF8);
        DictionaryService dictionaryService = new DictionaryService(dictionary);

    }
}
