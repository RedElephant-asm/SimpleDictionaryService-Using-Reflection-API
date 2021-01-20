package org.SimpleDictionaryService.handlers;

import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;
import org.SimpleDictionaryService.Symbol;

import java.util.*;

public class EncodingHandler {

    private Encoding encoding;
    private Language language;

    public EncodingHandler(Encoding encoding, Language language){
        this.encoding = encoding;
        this.language = language;
    }

    public boolean isArrayOfBytesEncodingCorrect(byte[] bytes) {
        return isArrayOfBytesEncodingCorrect(bytes, encoding, language);
    }

    public static boolean isArrayOfBytesEncodingCorrect(byte[] bytes, Encoding encoding, Language language){
        return ((double)getCountOfRelevantBytes(bytes, encoding, language) / bytes.length) > Encoding.MINIMAL_RATIO;
    }

    public int getCountOfRelevantBytes(byte[] bytes){
        return getCountOfRelevantBytes(bytes, encoding, language);
    }

    public boolean isArrayOfBytesEncodingCorrect(byte[] bytes, double ratio){
        return isArrayOfBytesEncodingCorrect(bytes, encoding, language, ratio);
    }

    public static boolean isArrayOfBytesEncodingCorrect(byte[] bytes, Encoding encoding, Language language, double ratio){
        return (double)getCountOfRelevantBytes(bytes, encoding, language) / bytes.length > ratio;
    }

    public static int getCountOfRelevantBytes(byte[] bytes, Encoding encoding, Language language){
        boolean isCorrect = false;
        ArrayList<Integer> symbolLengths = new ArrayList<>(encoding.getSymbolLengths());
        int countOfRelevantBytes = 0;
        for(int byteNumber = 0; byteNumber < bytes.length;){
            for (Integer symbolLength : symbolLengths) {
                if (byteNumber + symbolLength < bytes.length){
                    Symbol currentSymbol = new Symbol(Arrays.copyOfRange(bytes, byteNumber, byteNumber + symbolLength), language, encoding);
                    isCorrect = currentSymbol.isEncodingCorrect();
                }
                if (isCorrect) {
                    byteNumber += symbolLength;
                    countOfRelevantBytes += symbolLength;
                    break;
                }
            }
            if (!isCorrect) byteNumber++;
        }
        return countOfRelevantBytes;
    }
}
