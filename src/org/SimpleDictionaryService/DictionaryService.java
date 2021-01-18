package org.SimpleDictionaryService;

import org.SimpleDictionaryService.throwable.WrongEncodingException;

import java.io.*;

/**
 * @author RedElephant
 */
public class DictionaryService {

    private Dictionary dictionary;

    public DictionaryService(Dictionary dictionary){
        this.dictionary = dictionary;
        dictionary.isEncodingsCorrect();
    }

    public void createRecord(){

    }

    public void readRecord(){

    }

    public void updateRecord(){

    }

    public void deleteRecord(){

    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
