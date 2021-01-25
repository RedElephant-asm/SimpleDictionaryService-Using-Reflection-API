package org.SimpleDictionaryService.throwable;

public class UnknownLanguageException extends Exception{
    public UnknownLanguageException(String languageName){
        super(String.format("Unknown language \"%s\".", languageName));
    }
}
