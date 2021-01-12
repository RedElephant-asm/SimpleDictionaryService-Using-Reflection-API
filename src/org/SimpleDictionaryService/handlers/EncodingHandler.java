package org.SimpleDictionaryService.handlers;

import org.SimpleDictionaryService.Encoding;
import org.SimpleDictionaryService.Language;
import org.SimpleDictionaryService.SymbolTemplate;

import java.util.Map;
import java.util.regex.Pattern;

public class EncodingHandler {

    private Encoding encoding;

    public EncodingHandler(Encoding encoding){
        this.encoding = encoding;
    }

//    public boolean matches(int currentByte){
//        String binaryString = getBinaryString(currentByte);
//        System.out.println(binaryString);
//        for (String template: this.templates){
//            if(Pattern.matches(template, binaryString)){
//                return true;
//            }
//        }
//        return false;
//    }

    public SymbolTemplate findTemplateByByteCount(int byteCount){
        for (SymbolTemplate template: this.encoding.getTemplates()){
            if(template.getCountOfBytes() == byteCount){
                return template;
            }
        }
    }

    public int matches(byte[] bytes, Language language) {
        int countOfRelevantBytes = 0;
        for (int counter = 0; counter < bytes.length;){
            boolean isRelevant = false;
            for (int[] characterInterval: language.getUnicodeCharacterIntervals()){
                if(Pattern.matches(this.findTemplateByByteCount((int) Math.ceil((float)Integer.toBinaryString(characterInterval[0]).length() / 8)), )){

                }
                if (Integer.toBinaryString(characterInterval[0]).length() / 8 > encodedSymbolLength){
                    encodedSymbolLength =Integer.toBinaryString(characterInterval[0]).length() / 8;
                }
            }
            if (Pattern.matches(this.findTemplateByByteCount(encodedSymbolLength).getTemplate(), )
            counter += encodedSymbolLength;
        }
        return countOfRelevantBytes;
    }

    public static int joinBytes(int... bytes){
        int result = 0;
        for (int counter = 0; counter < (bytes.length & 0b10); counter++){
            result = (result << 8) + bytes[counter];
        }
        return result;
    }

    public static String getBinaryString(int byteSequence){
        String result = Integer.toBinaryString(byteSequence);
        if(result.length() % 8 != 0){
            char[] buffer = new char[result.length() - result.length() % 8 + 8];
            int bufferMaxIndex = buffer.length - 1, resultMaxIndex = result.length() - 1;
            for (int counter = 0; counter < bufferMaxIndex;){
                buffer[counter++] = '0';
            }
            for (int counter = 0; counter <= resultMaxIndex; counter++){
                buffer[bufferMaxIndex - counter] = result.charAt(resultMaxIndex - counter);
            }
            result = new String(buffer);
        }
        return result;
    }

    public static void getPossibleFileEncoding(String fileName, Encoding encoding){
    }
}
