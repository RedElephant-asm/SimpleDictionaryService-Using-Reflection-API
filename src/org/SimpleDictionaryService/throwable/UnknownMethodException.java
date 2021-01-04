package org.SimpleDictionaryService.throwable;

public class UnknownMethodException extends Exception{
    public UnknownMethodException(String methodName, OccurrenceTime occurrenceTime){
        super(formatMessage(methodName, occurrenceTime));
    }

    private static String formatMessage(String methodName, OccurrenceTime occurrenceTime){
        String message = "";
        switch (occurrenceTime){
            case ON_INIT:
                message = String.format("Unknown method \"%s\". Possibly method was not creating or changed the name during refactoring.", methodName);
                break;
            case ON_USE:
                message = String.format("Method \"%s\" not included in the list of methods used for reflection.", methodName);
                break;
        }
        return message;
    }
}
