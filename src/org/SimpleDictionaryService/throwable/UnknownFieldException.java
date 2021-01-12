package org.SimpleDictionaryService.throwable;

public class UnknownFieldException extends Exception{
    public UnknownFieldException(String fieldName, OccurrenceTime occurrenceTime){
        super(formatMessage(fieldName, occurrenceTime));
    }

    private static String formatMessage(String fieldName, OccurrenceTime occurrenceTime){
        String message = "";
        switch (occurrenceTime){
            case ON_INIT:
                message = String.format("Unknown field \"%s\". Possibly field was not created or changed the name during refactoring.", fieldName);
                break;
            case ON_USE:
                message = String.format("Field \"%s\" not included in the list of fields used for reflection.", fieldName);
                break;
        }
        return message;
    }
}
