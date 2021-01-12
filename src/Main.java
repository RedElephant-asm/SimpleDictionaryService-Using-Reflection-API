import org.SimpleDictionaryService.Dictionary;
import org.SimpleDictionaryService.DictionaryService;
import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;
import org.SimpleDictionaryService.throwable.WrongEncodingException;

public class Main {
    public static void main(String[] args){
        String filepath = "C:\\Dev heap\\Java\\projects\\SimpleDictionaryService-Using-Reflection-API\\src\\TEXT.txt";
        Dictionary dictionary = null;
        try {
            dictionary = new Dictionary(filepath, Language.UNICODE_RUSSIAN, Language.UNICODE_FIVEDIGITS, Encoding.UTF16);
        } catch (WrongEncodingException e) {
            e.printStackTrace();
        }
        DictionaryService dictionaryService = new DictionaryService(dictionary);

    }
}
