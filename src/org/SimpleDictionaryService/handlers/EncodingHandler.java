package org.SimpleDictionaryService.handlers;

import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;
import org.SimpleDictionaryService.Symbol;

import java.util.*;

import static org.SimpleDictionaryService.handlers.BinaryHandler.getBinaryString;

public class EncodingHandler {

    private Encoding encoding;
    private Language language;

    public EncodingHandler(Encoding encoding, Language language){
        this.encoding = encoding;
        this.language = language;
    }

    public boolean isSymbolCorrect(Symbol symbol){
        System.out.println((char) symbol.getValue());
        return language.isBelongsToTheLanguage(symbol) && encoding.isBelongsToTheEncoding(symbol);
    }

    public boolean isArrayOfBytesEncodingCorrect(byte[] bytes) {
        boolean isCorrect = false;
        ArrayList<Integer> symbolLengths = new ArrayList<>(encoding.getSymbolLengths());
        float countOfRelevantBytes = 0;
        for(int byteNumber = 0; byteNumber < bytes.length;){
            for (Integer symbolLength : symbolLengths) {
                isCorrect = isSymbolCorrect(new Symbol(Arrays.copyOfRange(bytes, byteNumber, byteNumber + symbolLength), this.language, this.encoding));
                if (isCorrect) {
                    byteNumber += symbolLength;
                    countOfRelevantBytes += symbolLength;
                    break;
                }
            }
            if (!isCorrect) byteNumber++;
        }
        return (countOfRelevantBytes / bytes.length) > Encoding.MINIMAL_RATIO;
    }
}
