package org.SimpleDictionaryService;

import org.SimpleDictionaryService.handlers.BinaryHandler;

public class Symbol {
    private byte[] bytes;
    private String[] serviceBitSets;
    private int[][] disorderedServiceBytes;

    public Symbol(byte[] bytes, Language language, Encoding encoding){
        this.bytes = bytes;
        serviceBitSets = encoding.findTemplateByByteCount(this.bytes.length).getServiceBitSets();
        disorderedServiceBytes = encoding.findTemplateByByteCount(this.bytes.length).getDisorderedServiceByteNumbers();
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
        return this.serviceBitSets.length == 0;
    }

    public boolean isSymbolHaveDisorderedServiceBytes(){
        return disorderedServiceBytes.length > 0;
    }

    public int getSymbolValueByDisorderedServiceBytes(){
        String binaryString = "";
        for (int[] disorderedServiceByte : disorderedServiceBytes) {
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
}