package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.BinaryHandler;

public class Symbol implements Encoded{
    private byte[] bytes;
    private Language language;
    private Encoding encoding;
    private String[] serviceBitSets;

    public Symbol(byte[] bytes, Language language, Encoding encoding){
        this.bytes = bytes;
        this.language = language;
        this.encoding = encoding;
        serviceBitSets = encoding.findTemplateByByteCount(this.bytes.length).getServiceBitSets();
    }

    @Override
    public boolean isEncodingCorrect() {
        return language.isBelongsToTheLanguage(this) && encoding.isBelongsToTheEncoding(this);
    }

    public int getValue(){
        if(isSymbolHaveServiceBitSets()){
            return BinaryHandler.getInteger(bytes);
        }
        if(isSymbolHaveDisorderedServiceBytes()){
            return getSymbolValueByDisorderedServiceBytes();
        } else {
            return getSymbolValueByOrderedServiceBytes();
        }
    }

    public boolean isSymbolHaveServiceBitSets(){
        return serviceBitSets.length == 0;
    }


    public boolean isSymbolHaveDisorderedServiceBytes(){
        return getDisorderedServiceBytes().length > 0;
    }

    private int[][] getDisorderedServiceBytes(){
        return encoding.findTemplateByByteCount(this.bytes.length).getDisorderedServiceByteNumbers();
    }

    public int getSymbolValueByDisorderedServiceBytes(){
        String binaryString = "";
        for (int[] disorderedServiceByte : getDisorderedServiceBytes()) {
            binaryString += BinaryHandler.getBinaryString(bytes[disorderedServiceByte[0]])
                    .replaceFirst(serviceBitSets[disorderedServiceByte[1]], "");
        }
        return Integer.parseInt(binaryString, 2);
    }

    public int getSymbolValueByOrderedServiceBytes(){
        String binaryString = "";
        for (int counter = 0; counter < bytes.length; counter++){
            binaryString += BinaryHandler.getBinaryString(bytes[counter]).replaceFirst(serviceBitSets[counter], "");
        }
        return Integer.parseInt(binaryString, 2);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public int getLength(){
        return this.bytes.length;
    }

    public Language getLanguage() {
        return language;
    }

    public Encoding getEncoding() {
        return encoding;
    }
}