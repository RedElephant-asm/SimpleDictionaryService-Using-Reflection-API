package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.EncodingHandler;
import org.SimpleDictionaryService.throwable.WrongEncodingException;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author RedElephant
 */
public class DictionaryService {

    private Dictionary dictionary;

    public DictionaryService(Dictionary dictionary){
        this.dictionary = dictionary;
        dictionary.isEncodingsCorrect();


        System.out.println(this.dictionary.getPath());
        HashSet<String> data = (HashSet<String>)readAll();
        for (String record : data){
            System.out.println(record);
        }
    }

    public void createRecord(String key, String word){
        String record = formatRecord(key, word);
        if(isRecordCorrect(record)){

        }
    }

    public void readRecord(){

    }

    public void updateRecord(){

    }

    public void deleteRecord(){

    }

    public Set<String> readAll(){
        Set<String> data = new LinkedHashSet<>();
        try(LineNumberReader lineReader = new LineNumberReader(new FileReader(dictionary))){
            String record = lineReader.readLine();
            while (record != null){
                data.add(record);
                record = lineReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public boolean isRecordCorrect(String record) {
        return isRecordCorrect(record, dictionary.getEncoding(), dictionary.getKeyLanguage(), dictionary.getWordLanguage());
    }

    public static boolean isRecordCorrect(String record, Encoding encoding, Language keyLanguage, Language wordLanguage){
        return EncodingHandler.isArrayOfBytesEncodingCorrect(record.getBytes(), encoding, keyLanguage, Dictionary.KEY_ENCODING_MINIMAL_RATIO)
                && EncodingHandler.isArrayOfBytesEncodingCorrect(record.getBytes(), encoding, wordLanguage, Dictionary.WORD_ENCODING_MINIMAL_RATIO);
    }

    public static String formatRecord(String key, String word){
        return key.concat(";").concat(word).concat("\n");
    }

    public void getInputStream(){

    }

    public void getOutputStream(){

    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
