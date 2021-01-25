package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.EncodingHandler;
import org.SimpleDictionaryService.throwable.InvalidWordLengthException;
import org.SimpleDictionaryService.throwable.OriginSubject;
import org.SimpleDictionaryService.throwable.WrongEncodingException;

public class Record implements Encoded{
    private String key;
    private String word;
    private Encoding encoding;
    private Language keyLanguage;
    private Language wordLanguage;

    private Record(){}

    public Record(String recordString, Encoding encoding, Language keyLanguage, Language wordLanguage){
        this(recordString.split(";")[0], recordString.split(";")[1], encoding, keyLanguage, wordLanguage);
    }

    public Record(String key, String word, Encoding encoding, Language keyLanguage, Language wordLanguage){
        this.key = key;
        this.word = word;
        this.encoding = encoding;
        this.keyLanguage = keyLanguage;
        this.wordLanguage = wordLanguage;
    }

    @Override
    public String toString() {
        return String.format("%s;%s\n", key, word);
    }

    public boolean isCorrect(){
        return isEncodingCorrect() && isLengthCorrect();
    }

    public boolean isLengthCorrect(){
        try {
            if (!keyLanguage.isWordLengthCorrect(key)){
                throw new InvalidWordLengthException(key);
            }else if(!wordLanguage.isWordLengthCorrect(word)){
                throw new InvalidWordLengthException(word);
            }
        }catch (InvalidWordLengthException exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isEncodingCorrect() {
        try {
            isKeyEncodingCorrect();
            isWordEncodingCorrect();
        }catch (WrongEncodingException exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public void isKeyEncodingCorrect() throws WrongEncodingException{
        if (!EncodingHandler.isArrayOfBytesEncodingCorrect(encoding.encodeString(key).getBytes(), encoding, keyLanguage,Dictionary.KEY_ENCODING_MINIMAL_RATIO)){
            throw new WrongEncodingException(encoding, wordLanguage, key, OriginSubject.WORD);
        }
    }

    public void isWordEncodingCorrect() throws WrongEncodingException{
        if(!EncodingHandler.isArrayOfBytesEncodingCorrect(encoding.encodeString(word).getBytes(), encoding, wordLanguage, Dictionary.WORD_ENCODING_MINIMAL_RATIO)){
            throw new WrongEncodingException(encoding, wordLanguage, word, OriginSubject.WORD);
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
