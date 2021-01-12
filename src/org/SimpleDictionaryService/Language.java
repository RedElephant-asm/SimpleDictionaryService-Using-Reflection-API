package org.SimpleDictionaryService;

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

    public int getWordLength() {
        return wordLength;
    }

    public int[][] getUnicodeCharacterIntervals() {
        return unicodeCharacterIntervals;
    }
}
