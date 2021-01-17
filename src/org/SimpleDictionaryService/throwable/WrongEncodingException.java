package org.SimpleDictionaryService.throwable;

public class WrongEncodingException extends RuntimeException {
    public WrongEncodingException(String encodingName, String fullFileName){
        super(String.format("Encoding of the file \"%s\" most likely is not %s.", fullFileName, encodingName));
    }
}
