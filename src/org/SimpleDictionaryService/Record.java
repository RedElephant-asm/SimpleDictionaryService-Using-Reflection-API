package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.EncodingHandler;

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

    @Override
    public boolean isEncodingCorrect() {
        return isKeyEncodingCorrect() && isWordEncodingCorrect();
    }

    public boolean isKeyEncodingCorrect(){
        return EncodingHandler.isArrayOfBytesEncodingCorrect(encoding.encodeString(key).getBytes(), encoding, keyLanguage,Dictionary.KEY_ENCODING_MINIMAL_RATIO);
    }

    public boolean isWordEncodingCorrect(){
        return EncodingHandler.isArrayOfBytesEncodingCorrect(encoding.encodeString(word).getBytes(), encoding, wordLanguage, Dictionary.WORD_ENCODING_MINIMAL_RATIO);
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
