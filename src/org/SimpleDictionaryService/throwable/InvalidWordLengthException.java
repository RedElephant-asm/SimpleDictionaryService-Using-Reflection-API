package org.SimpleDictionaryService.throwable;

public class InvalidWordLengthException extends Exception {
    public InvalidWordLengthException(String word){
        super(String.format("Word \"%s\" has invalid length.", word));
    }
}
