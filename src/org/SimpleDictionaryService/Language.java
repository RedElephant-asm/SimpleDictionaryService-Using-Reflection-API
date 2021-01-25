package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.BinaryHandler;
import org.SimpleDictionaryService.throwable.InvalidWordLengthException;

public enum Language {

    UNICODE_FOURLATINLETTERS   (4, new int[]{65, 90}, new int[]{97, 122}),
    UNICODE_FIVEDIGITS         (5, new int[]{48, 57}),
    UNICODE_RUSSIAN            (20, new int[]{1040, 1103}, new int[]{1025, 1025}),
    UNKNOWN_LANGUAGE           (0);

    private final int[][] unicodeCharacterIntervals;
    private final int wordLength;

    Language(final int wordLength, final int[]... unicodeCharacterIntervals){
        this.wordLength = wordLength;
        this.unicodeCharacterIntervals = unicodeCharacterIntervals;
    }


    public boolean isBelongsToTheLanguage(Symbol symbol){
        int unicodeCharacterNumber = symbol.getValue();
        for (int[] interval: this.unicodeCharacterIntervals){
            if ((unicodeCharacterNumber >= interval[0] && unicodeCharacterNumber <= interval[1]) || isASCIIServiceSymbol(symbol)){
                return true;
            }
        }
        return false;
    }

    public static boolean isASCIIServiceSymbol(Symbol symbol){
        int asciiCharacterNumber = BinaryHandler.getInteger(symbol.getBytes());
        return asciiCharacterNumber >= 0 && asciiCharacterNumber < 65;
    }

    public static Language getLanguageByName(String languageName){
        for (Language language : Language.values()) {
            if(language.name().toLowerCase().equals(languageName)){
                return language;
            }
        }
        return UNKNOWN_LANGUAGE;
    }

    public static boolean isLanguageExist(String languageName){
        return !(getLanguageByName(languageName) == UNKNOWN_LANGUAGE);
    }

    public boolean isWordLengthCorrect(String word){
        return word.length() == wordLength;
    }

    public int getWordLength() {
        return wordLength;
    }

    public int[][] getUnicodeCharacterIntervals() {
        return unicodeCharacterIntervals;
    }
}
