package org.SimpleDictionaryService;

import org.SimpleDictionaryService.throwable.WrongEncodingException;

import java.io.*;

/**
 * @author RedElephant
 */
public class DictionaryService {

    private Dictionary dictionary;
    private InputStream inputStream;
    private OutputStream outputStream;

    public DictionaryService(Dictionary dictionary){
        this.dictionary = dictionary;
    }

    public void createInDictionary(){

    }

    public void readFromDictionary(){

    }

    public void updateInDictionary(){

    }

    public void deleteFromDictionary(){

    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
