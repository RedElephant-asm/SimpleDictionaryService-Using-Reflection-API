package org.SimpleDictionaryService.throwable;

import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;

public class WrongEncodingException extends Exception {

    public WrongEncodingException(String encodingName){
        super(String.format("Unknown encoding \"%s\".", encodingName));
    }

    public WrongEncodingException(Encoding encoding, Language language, String subjectName, OriginSubject originSubject){
        super(formatMessage(encoding, language, subjectName, originSubject));
    }

    private static String formatMessage(Encoding encoding, Language language, String subjectName, OriginSubject originSubject){
        String message = "";
        switch (originSubject){
            case FILE:
                message = String.format("Encoding of the file \"%s\" most likely is not %s\n or the characters that the text file" +
                        " contains are not characters of %s language.", subjectName, encoding.name(), language.name());
                break;
            case WORD:
                message = String.format("Encoding of the string/word \"%s\" most likely is not %s\n or the characters that the string/word" +
                        " contains are not characters of %s language.", subjectName, encoding.name(), language.name());
                break;
        }
        return message;
    }
}
