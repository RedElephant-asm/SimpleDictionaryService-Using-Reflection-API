package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.BinaryHandler;

public class SymbolTemplate {

    public static final SymbolTemplate ASCII128DEFAULT_TEMPLATE = new SymbolTemplate("0[01]{7}", 1);

    private String template;
    private int countOfBytes;
    private int[][] disorderedServiceByteNumbers;


    public SymbolTemplate(String template, int countOfBytes, int[]... disorderedServiceByteNumbers){
        this.template = template;
        this.countOfBytes = countOfBytes;
        this.disorderedServiceByteNumbers = disorderedServiceByteNumbers;
    }

    public boolean isMatchesSymbol(Symbol symbol){
        return BinaryHandler.getBinaryString(symbol.getBytes()).matches(this.template);
    }

    public String[] getServiceBitSets(){
        return this.template
                .replaceAll("\\[[01]{1,2}]\\{[1-9]{1,2}}", ",")
                .split(",");
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

    public int[][] getDisorderedServiceByteNumbers() {
        return disorderedServiceByteNumbers;
    }

    public void setDisorderedServiceByteNumbers(int[][] serviceBiteNumbers) {
        this.disorderedServiceByteNumbers = serviceBiteNumbers;
    }
}
