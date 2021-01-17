import org.SimpleDictionaryService.Dictionary;
import org.SimpleDictionaryService.DictionaryService;
import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;

public class Main {
    public static void main(String[] args){
        String filepath = "D:\\Code\\Java\\projects\\SimpleDictionaryService-Using-Reflection-API\\src\\TEXT.txt";
        Dictionary dictionary = new Dictionary(filepath, Language.UNICODE_RUSSIAN, Language.UNICODE_FOURLATINLETTERS, Encoding.UTF8);
        DictionaryService dictionaryService = new DictionaryService(dictionary);
    }
}
