package org.SimpleDictionaryService.throwable;

public class UnknownMethodException extends Exception{
    public UnknownMethodException(String methodName){
        super(String.format("Unknown method \"%s\". Possibly method was not creating or changed the name during refactoring.", methodName));
    }
}
