package org.SimpleDictionaryService.handlers;

import com.sun.java_cup.internal.runtime.Symbol;
import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;
import org.SimpleDictionaryService.SymbolTemplate;

import java.util.*;
import java.util.regex.Pattern;

import static org.SimpleDictionaryService.handlers.BinaryHandler.getBinaryString;
import static org.SimpleDictionaryService.handlers.BinaryHandler.getInteger;

public class EncodingHandler {

    private Encoding encoding;
    private Language language;

    public EncodingHandler(Encoding encoding, Language language){
        this.encoding = encoding;
        this.language = language;
    }

    public boolean isSymbolsEncodingCorrect(byte... byteSequence){
        if (this.language.isBelongsToTheLanguage(byteSequence) &&
                this.encoding.findTemplateByByteCount(byteSequence.length).matches(getBinaryString(byteSequence))){
            return true;
        }
        return false;
    }

    public int isArrayOfSymbolsEncodingCorrect(byte[] bytes) {
        ArrayList<Integer> symbolLengths = new ArrayList<>(this.language.getSymbolPossibleLengths());
        int countOfRelevantBytes = 0;
        for(int byteNumber = 0; byteNumber < bytes.length;){
            for(int lengthNumber = 0; lengthNumber < symbolLengths.size(); lengthNumber++){
                if(isSymbolsEncodingCorrect(Arrays.copyOfRange(bytes, byteNumber, byteNumber + symbolLengths.get(lengthNumber)))){
                    byteNumber += symbolLengths.get(lengthNumber);
                    countOfRelevantBytes += symbolLengths.get(lengthNumber);
                    break;
                }else {
                    byteNumber++;
                }
            }
//            theoreticalSymbolLength = symbolLengths.stream()
//                    .filter(length -> Pattern.matches(this.encoding.findTemplateByByteCount(length).getTemplate(),
//                            getBinaryString(Arrays.copyOfRange(bytes, byteNumber[0], byteNumber[0] + length)))).findAny();
//            if(theoreticalSymbolLength.isPresent()){
//                byteNumber[0] += theoreticalSymbolLength.get();
//                countOfRelevantBytes += theoreticalSymbolLength.get();
//            }else byteNumber[0]++;
        }
        return countOfRelevantBytes;
    }


}
