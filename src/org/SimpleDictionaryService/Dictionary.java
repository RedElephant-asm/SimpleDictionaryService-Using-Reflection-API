package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.EncodingHandler;
import org.SimpleDictionaryService.throwable.WrongEncodingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Dictionary{

    private Language wordLanguage;

    private Language keyLanguage;

    private EncodingHandler encodingHandler;

    private File file;

    public Dictionary(String fullFileName, Language wordLanguage, Language keyLanguage, Encoding fileEncoding)
            throws WrongEncodingException
    {
        this.file = new File(fullFileName);
        this.wordLanguage = wordLanguage;
        this.keyLanguage = keyLanguage;
        this.encodingHandler = new EncodingHandler(fileEncoding, wordLanguage);
        if(!this.isEncodingCorrect()){
            throw new WrongEncodingException(fileEncoding.name(), fullFileName);
        }

    }

    public boolean isEncodingCorrect(){
        boolean isCorrect = false;
        try(InputStream inputStream = new FileInputStream(file)){
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            isCorrect = encodingHandler.isArrayOfBytesEncodingCorrect(bytes);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return isCorrect;
    }
}
