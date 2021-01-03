package org.SimpleDictionaryService.throwable;

public class UnknownFieldException extends Exception{
    public UnknownFieldException(String fieldName){
        super(String.format("Unknown field \"%s\". Possibly field was not creating or changed the name during refactoring.", fieldName));
    }
}
