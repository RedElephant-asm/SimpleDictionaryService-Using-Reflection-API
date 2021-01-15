package org.SimpleDictionaryService;

public enum Encoding {

    UTF8            ("UTF-8",
                     new SymbolTemplate("110[01]{5}10[01]{6}", 2),
                     new SymbolTemplate("1110[01]{4}10[01]{6}10[01]{6}", 3),
                     new SymbolTemplate("11110[01]{3}10[01]{6}10[01]{6}10[01]{6}", 4)),

    UTF16           ("UTF-16BE",
                     new SymbolTemplate("[0]{8}[01]{8}", 2),
                     new SymbolTemplate("[01]{16}", 2),
                     new SymbolTemplate("1101100[01]{9}1101111[01]{9}", 4)),

    ASCII           ("ASCII",
                     new SymbolTemplate("0[01]{7}", 1));

    public static final int UNICODE_TABLE_LENGTH = 0x110000;

    private final SymbolTemplate[] templates;

    private final String charsetName;

    Encoding(String charsetName, SymbolTemplate... templates){
        this.templates = templates;
        this.charsetName = charsetName;
    }

    public SymbolTemplate findTemplateByByteCount(int byteCount){
        SymbolTemplate result = SymbolTemplate.ASCII128DEFAULT_TEMPLATE;
        for (SymbolTemplate template: this.getTemplates()){
            if(template.getCountOfBytes() == byteCount){
                result = template;
            }
        }
        return result;
    }

//    public boolean matchSymbolEncoding(byte... symbolBytes){
//
//    }

    public SymbolTemplate[] getTemplates() {
        return templates;
    }

    public String getCharsetName() {
        return charsetName;
    }
}
