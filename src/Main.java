import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;
import org.SimpleDictionaryService.handlers.BinaryHandler;
import org.SimpleDictionaryService.handlers.EncodingHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Main {
    public static void main(String[] args){
        byte[] bytes = new byte[]{(byte) 0b10000000, (byte) 0b11000000};
        String filepath = "D:\\Code\\Java\\projects\\SimpleDictionaryService-Using-Reflection-API\\src\\TEXT.txt";
        EncodingHandler encodingHandler = new EncodingHandler(Encoding.UTF16, Language.UNICODE_RUSSIAN);
        try(InputStream inputStream = new FileInputStream(filepath)) {
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            System.out.println(encodingHandler.isArrayOfSymbolsEncodingCorrect(data));
        }catch (IOException exceprion){
            exceprion.printStackTrace();
        }
        System.out.println("\n");
        System.out.println(BinaryHandler.getBinaryString(BinaryHandler.getInteger(bytes)));

    }
}
