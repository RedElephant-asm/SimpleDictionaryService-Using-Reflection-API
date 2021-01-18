package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.EncodingHandler;
import org.SimpleDictionaryService.throwable.WrongEncodingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Dictionary{

    public static double WORD_ENCODING_MINIMAL_RATIO    = 0.5 * Encoding.MINIMAL_RATIO;
    public static double KEY_ENCODING_MINIMAL_RATIO     = 0.5 * Encoding.MINIMAL_RATIO;

    private Language wordLanguage;
    private Language keyLanguage;
    private Encoding encoding;
    private File file;

    public Dictionary(String fullFileName, Language wordLanguage, Language keyLanguage, Encoding fileEncoding)
            throws WrongEncodingException
    {
        this.file = new File(fullFileName);
        this.wordLanguage = wordLanguage;
        this.keyLanguage = keyLanguage;
        this.encoding = fileEncoding;
    }

    public void isEncodingsCorrect(){
        byte[] bytes = readAllBytes();
        isWordEncodingCorrect(bytes);
        isKeyEncodingCorrect(bytes);
    }

    private byte[] readAllBytes(){
        byte[] bytes = new byte[]{};
        try(InputStream inputStream = new FileInputStream(file)){
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return bytes;
    }

    private void isWordEncodingCorrect(byte[] bytes){
        if(!EncodingHandler.isArrayOfBytesEncodingCorrect(bytes, encoding, wordLanguage, WORD_ENCODING_MINIMAL_RATIO))
            throw new WrongEncodingException(encoding.name(), file.getName(), wordLanguage.name());
    }

    private void isKeyEncodingCorrect(byte[] bytes){
        if(!EncodingHandler.isArrayOfBytesEncodingCorrect(bytes, encoding, keyLanguage, KEY_ENCODING_MINIMAL_RATIO))
            throw new WrongEncodingException(encoding.name(), file.getName(), keyLanguage.name());
    }
}
