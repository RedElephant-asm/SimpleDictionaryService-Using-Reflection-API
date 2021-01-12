package org.SimpleDictionaryService;

public class SymbolTemplate {
    private String template;
    private int countOfBytes;

    public SymbolTemplate(String template){
        this(template, 1);
    }

    public SymbolTemplate(String template, int countOfBytes){
        this.template = template;
        this.countOfBytes = countOfBytes;
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
