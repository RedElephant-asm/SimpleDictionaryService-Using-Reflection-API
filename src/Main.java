import org.SimpleDictionaryService.Dictionary;
import org.SimpleDictionaryService.DictionaryService;
import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;
import org.SimpleDictionaryService.handlers.BinaryHandler;
import org.SimpleDictionaryService.handlers.EncodingHandler;
import org.SimpleDictionaryService.throwable.WrongEncodingException;
import sun.rmi.transport.tcp.TCPTransport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


public class Main {
    public static void main(String[] args){
        byte[] bytes = new byte[]{(byte) 0b10000000, (byte) 0b11000000};
        String filepath = "D:\\Code\\Java\\projects\\SimpleDictionaryService-Using-Reflection-API\\src\\TEXT.txt";
        EncodingHandler encodingHandler = new EncodingHandler(Encoding.UTF8, Language.UNICODE_RUSSIAN);
        try(InputStream inputStream = new FileInputStream(filepath)) {
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            System.out.println(encodingHandler.matches(data));
        }catch (IOException exceprion){
            exceprion.printStackTrace();
        }
        System.out.println("\n");
        System.out.println(BinaryHandler.getBinaryString(0b0110000010000000));

    }
}
