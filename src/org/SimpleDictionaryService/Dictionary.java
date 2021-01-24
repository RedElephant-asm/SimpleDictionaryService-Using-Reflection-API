package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.EncodingHandler;
import org.SimpleDictionaryService.throwable.WrongEncodingException;

import java.io.*;

public class Dictionary extends File implements Encoded{

    public static double WORD_ENCODING_MINIMAL_RATIO    = 0.66 * Encoding.MINIMAL_RATIO;
    public static double KEY_ENCODING_MINIMAL_RATIO     = 0.33 * Encoding.MINIMAL_RATIO;

    private Language wordLanguage;
    private Language keyLanguage;
    private Encoding encoding;

    public Dictionary(String fullFileName, Language wordLanguage, Language keyLanguage, Encoding fileEncoding)
    {
        super(fullFileName);
        this.wordLanguage = wordLanguage;
        this.keyLanguage = keyLanguage;
        this.encoding = fileEncoding;
    }

    @Override
    public boolean isEncodingCorrect(){
        byte[] bytes = readAllBytes();
        if (bytes.length < Encoding.MINIMAL_FILE_LENGTH){
            return false;
        }
        try {
            isWordEncodingCorrect(bytes);
            isKeyEncodingCorrect(bytes);
        } catch (WrongEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private byte[] readAllBytes(){
        byte[] bytes = new byte[]{};
        try(InputStream inputStream = new FileInputStream(this)){
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return bytes;
    }

    private void isWordEncodingCorrect(byte[] bytes) throws WrongEncodingException{
        if(!EncodingHandler.isArrayOfBytesEncodingCorrect(bytes, encoding, wordLanguage, WORD_ENCODING_MINIMAL_RATIO))
            throw new WrongEncodingException(encoding.name(), this.getPath(), wordLanguage.name());
    }

    private void isKeyEncodingCorrect(byte[] bytes) throws WrongEncodingException{
        if(!EncodingHandler.isArrayOfBytesEncodingCorrect(bytes, encoding, keyLanguage, KEY_ENCODING_MINIMAL_RATIO))
            throw new WrongEncodingException(encoding.name(), this.getPath(), keyLanguage.name());
    }

    public Language getWordLanguage() {
        return wordLanguage;
    }

    public Language getKeyLanguage() {
        return keyLanguage;
    }

    public Encoding getEncoding() {
        return encoding;
    }
}