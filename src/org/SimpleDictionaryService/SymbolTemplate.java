package org.SimpleDictionaryService;

import java.util.regex.Pattern;

public class SymbolTemplate {
    private String template;
    private int countOfBytes;
    public static final SymbolTemplate ASCII128DEFAULT_TEMPLATE = new SymbolTemplate("0[01]{7}", 1);

    public SymbolTemplate(String template){
        this(template, 1);
    }

    public SymbolTemplate(String template, int countOfBytes){
        this.template = template;
        this.countOfBytes = countOfBytes;
    }

    public boolean matches(String binaryString){
        return Pattern.matches(this.template, binaryString);
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getCountOfBytes() {
        return countOfBytes;
    }

    public void setCountOfBytes(int countOfBytes) {
        this.countOfBytes = countOfBytes;
    }
}
