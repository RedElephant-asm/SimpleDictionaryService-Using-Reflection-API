package org.SimpleDictionaryService.handlers;

import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;
import org.SimpleDictionaryService.SymbolTemplate;

import java.util.*;
import java.util.regex.Pattern;

import static org.SimpleDictionaryService.handlers.BinaryHandler.getBinaryString;

public class EncodingHandler {

    private Encoding encoding;
    private Language language;

    public EncodingHandler(Encoding encoding, Language language){
        this.encoding = encoding;
        this.language = language;
    }

    public int matches(byte[] bytes) {
        ArrayList<Integer> symbolLengths = new ArrayList<>(this.language.getSymbolPossibleLengths());
        Optional<Integer> theoreticalSymbolLength;
        int countOfRelevantBytes = 0;
        for (int[] byteNumber = new int[]{0}; byteNumber[0] < bytes.length;){
            System.out.println(getBinaryString(bytes[byteNumber[0]]));
            theoreticalSymbolLength = symbolLengths.stream()
                    .filter(length -> Pattern.matches(this.encoding.findTemplateByByteCount(length).getTemplate(),
                            getBinaryString(Arrays.copyOfRange(bytes, byteNumber[0], byteNumber[0] + length)))).findAny();
            if(theoreticalSymbolLength.isPresent()){
                byteNumber[0] += theoreticalSymbolLength.get();
                countOfRelevantBytes += theoreticalSymbolLength.get();
            }else byteNumber[0]++;
        }
        return countOfRelevantBytes;
    }
}
