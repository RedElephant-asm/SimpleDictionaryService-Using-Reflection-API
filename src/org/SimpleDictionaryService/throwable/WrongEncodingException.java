package org.SimpleDictionaryService.throwable;

import java.io.IOException;

public class WrongEncodingException extends IOException {
    public WrongEncodingException(String encodingName, String fullFileName){
        super(String.format("Encoding of the file \"%s\" most likely is not %s.", fullFileName, encodingName));
    }
}
