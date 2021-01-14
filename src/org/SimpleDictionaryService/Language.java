package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.ReflectionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.SimpleDictionaryService.handlers.BinaryHandler.getNumberLength;

public enum Language {
    UNICODE_FOURLATINLETTERS   (4, new int[]{65, 90}, new int[]{97, 122}),
    UNICODE_FIVEDIGITS         (5, new int[]{48, 57}),
    UNICODE_RUSSIAN            (20, new int[]{1040, 1103}, new int[]{1025, 1025});

    private final int[][] unicodeCharacterIntervals;
    private final int wordLength;

    Language(final int wordLength, final int[]... unicodeCharacterIntervals){
        this.wordLength = wordLength;
        this.unicodeCharacterIntervals = unicodeCharacterIntervals;
    }

    public List<Integer> getSymbolPossibleLengths(){
        ArrayList<Integer> possibleLengths = new ArrayList<>();
        for (int[] interval: this.unicodeCharacterIntervals){
            if (possibleLengths.stream().noneMatch(existingInterval -> existingInterval == getNumberLength(interval[0]))){
                possibleLengths.add(getNumberLength(interval[0]));
            }
            if (possibleLengths.stream().noneMatch(existingInterval -> existingInterval == getNumberLength(interval[1]))){
                possibleLengths.add(getNumberLength(interval[1]));
            }
        }
        return possibleLengths;
    }

    public int getWordLength() {
        return wordLength;
    }

    public int[][] getUnicodeCharacterIntervals() {
        return unicodeCharacterIntervals;
    }
}
