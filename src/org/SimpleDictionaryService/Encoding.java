package org.SimpleDictionaryService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.SimpleDictionaryService.handlers.BinaryHandler.getBinaryString;

public enum Encoding {

    UTF8            ("UTF-8",
                     new SymbolTemplate("0[01]{7}", 1), // needs serving-byte intervals
                     new SymbolTemplate("110[01]{5}10[01]{6}", 2),
                     new SymbolTemplate("1110[01]{4}10[01]{6}10[01]{6}", 3),
                     new SymbolTemplate("11110[01]{3}10[01]{6}10[01]{6}10[01]{6}", 4)),

    UTF16           ("UTF-16BE",
                     new SymbolTemplate("[01]{16}", 2),
                     new SymbolTemplate("1101100[01]{9}1101111[01]{9}", 4, new int[]{0, 0}, new int[]{2, 1})),

    ASCII           ("ASCII",
                     new SymbolTemplate("0[01]{7}", 1));

    public static final int UNICODE_TABLE_LENGTH = 0x110000;
    public static final double MINIMAL_RATIO = 0.8;

    private final SymbolTemplate[] templates;

    private final String charsetName;

    private List<Integer> symbolLengths;

    Encoding(String charsetName, SymbolTemplate... templates){
        this.templates = templates;
        this.charsetName = charsetName;
        symbolLengths = getSymbolPossibleLengths();
    }

    public boolean isBelongsToTheEncoding(Symbol symbol){
        return findTemplateByByteCount(symbol.getLength()).isMatchesSymbol(symbol);
    }

    public SymbolTemplate findTemplateByByteCount(int byteCount){
        SymbolTemplate result = SymbolTemplate.ASCII128DEFAULT_TEMPLATE;
        for (SymbolTemplate template: this.templates){
            if(template.getCountOfBytes() == byteCount){
                result = template;
            }
        }
        return result;
    }

    private List<Integer> getSymbolPossibleLengths(){
        ArrayList<Integer> possibleLengths = new ArrayList<>();
        for (SymbolTemplate template: this.templates) {
            if (possibleLengths.stream().noneMatch(existingInterval -> existingInterval == template.getCountOfBytes())){
                possibleLengths.add(template.getCountOfBytes());
            }
        }
        return possibleLengths;
    }

    public String encodeString(String string){
        try {
            return new String(string.getBytes("UTF-8"), this.getCharsetName());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    public SymbolTemplate[] getTemplates() {
        return templates;
    }

    public String getCharsetName() {
        return charsetName;
    }

    public List<Integer> getSymbolLengths() {
        return symbolLengths;
    }
}

