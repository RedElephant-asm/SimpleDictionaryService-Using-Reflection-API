package org.SimpleDictionaryService.throwable;

public class WrongEncodingException extends Exception {
    public WrongEncodingException(String encodingName, String fullFileName){
        super(String.format("Encoding of the file \"%s\" most likely is not %s.", fullFileName, encodingName));
    }

    public WrongEncodingException(String encodingName, String fullFileName, String languageName){
        super(String.format("Encoding of the file \"%s\" most likely is not %s\n or the characters that the text file" +
                " contains are not characters of %s language.", fullFileName, encodingName, languageName));
    }
}
