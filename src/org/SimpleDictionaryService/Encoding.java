package org.SimpleDictionaryService;

import java.util.regex.Pattern;

public enum Encoding {

    UTF8            (new SymbolTemplate("110[01]{5}10[01]{6}", 2),
                     new SymbolTemplate("1110[01]{4}10[01]{6}10[01]{6}", 3),
                     new SymbolTemplate("11110[01]{3}10[01]{6}10[01]{6}10[01]{6}", 4)),

    UTF16           (new SymbolTemplate("1101100[01]{9}1101111[01]{9}", 4));

    public static final int UNICODE_TABLE_LENGTH = 0x110000;

    private final SymbolTemplate[] templates;

    Encoding(SymbolTemplate... templates){
        this.templates = templates;
    }

    public SymbolTemplate[] getTemplates() {
        return templates;
    }


}
