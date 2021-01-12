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
        this.encodingHandler = new EncodingHandler(fileEncoding);
        if(!this.isEncodingsMatches()){
            throw new WrongEncodingException(this.fileEncoding.name(), fullFileName);
        }

    }

    public boolean isEncodingsMatches(){
        int encByteCount = 0;
        try(InputStream inputStream = new FileInputStream(this.file)) {
            int fileByteCount = inputStream.available();
            for (int counter = 0; counter < fileByteCount; counter++){
                if (fileEncoding.matches(inputStream.read())){
                    encByteCount++;
                }
            }
            return ((float)encByteCount / fileByteCount) > 0.6;
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return false;
    }

    public Language getWordLanguage() {
        return wordLanguage;
    }

    public void setWordLanguage(Language wordLanguage) {
        this.wordLanguage = wordLanguage;
    }

    public Language getKeyLanguages() {
        return keyLanguage;
    }

    public void setKeyLanguages(Language keyLanguage) {
        this.keyLanguage = keyLanguage;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Language getKeyLanguage() {
        return keyLanguage;
    }

    public void setKeyLanguage(Language keyLanguage) {
        this.keyLanguage = keyLanguage;
    }

    public EncodingHandler getEncodingHandler() {
        return encodingHandler;
    }

    public void setEncodingHandler(EncodingHandler encodingHandler) {
        this.encodingHandler = encodingHandler;
    }
}
