package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.EncodingHandler;
import org.SimpleDictionaryService.throwable.WrongEncodingException;

import java.io.*;
import java.util.*;

/**
 * @author RedElephant
 */
public class DictionaryService {

    private Dictionary dictionary;

    public DictionaryService(){}

    public DictionaryService(Dictionary dictionary){
        this.dictionary = dictionary;
        dictionary.isEncodingCorrect();
    }

    public void addRecord(Record record){
        if(record.isEncodingCorrect()){
            BufferedWriter outputStream = getOutputStream(true);
            try {
                outputStream.write(record.toString().concat("\n"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            closeStream(outputStream);
        }
    }

    public Optional<Record> readRecord(String key){
        return readAll().stream().filter(record -> record.getKey().equals(key)).findAny();
    }

    public boolean updateRecord(String key, Record newRecord){
        Set<Record> data = readAll();
        data.stream().filter(record -> record.getKey().equals(key)).forEach(record -> {
            record.setKey(newRecord.getKey());
            record.setWord(newRecord.getWord());
        });
        saveChanges(data);
        return false;
    }

    public boolean deleteRecord(String key){
        Set<Record> data = readAll();
        Optional<Record> toBeDeleted = data.stream().filter(record -> record.getKey().equals(key)).findAny();
        boolean isDeleted = data.remove(toBeDeleted.isPresent() ? toBeDeleted.get() :
                new Record("Mock;Record", dictionary.getEncoding(), dictionary.getKeyLanguage(), dictionary.getWordLanguage()));
        saveChanges(data);
        return isDeleted;
    }

    public Set<Record> readAll(){
        Set<Record> data = new LinkedHashSet<>();
        LineNumberReader lineReader = getInputStream();
        try {
            String recordString = lineReader.readLine();
            while (recordString != null){
                if (!recordString.isEmpty() && recordString.split(";").length == 2){
                    data.add(new Record(recordString, dictionary.getEncoding(), dictionary.getKeyLanguage(), dictionary.getWordLanguage()));
                }
                recordString = lineReader.readLine();
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
        closeStream(lineReader);
        return data;
    }

    private LineNumberReader getInputStream(){
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new FileReader(dictionary));
        }catch (FileNotFoundException exception){
            exception.printStackTrace();
        }
        return reader;
    }

    private BufferedWriter getOutputStream(boolean append){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(dictionary, append));
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return writer;
    }

    public void closeStream(Closeable stream){
        try {
            stream.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void saveChanges(Set<Record> data){
        BufferedWriter outputStream = getOutputStream(false);
        try {
            for (Record record : data){
                outputStream.write(record.toString());
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
        closeStream(outputStream);
    }

    public Record createRecord(String key, String word){
        return new Record(key, word, dictionary.getEncoding(), dictionary.getKeyLanguage(), dictionary.getWordLanguage());
    }

    public boolean isDictionarySelected(){
        return !Objects.isNull(dictionary);
    }

    public void setDictionary(Dictionary dictionary){
        this.dictionary = dictionary;
        dictionary.isEncodingCorrect();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}