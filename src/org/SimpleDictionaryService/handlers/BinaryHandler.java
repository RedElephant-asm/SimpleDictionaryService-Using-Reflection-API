package org.SimpleDictionaryService.handlers;

import java.nio.CharBuffer;

public class BinaryHandler {

    public static int getNumberLength(int number){
        String binaryString = Integer.toBinaryString(number);
        return binaryString.length() % 8 == 0 ? binaryString.length() / 8 : binaryString.length() / 8 + 1;
    }

    public static int getInteger(byte... bytes){
        int result = 0;
        for (int byteNumber = 0; byteNumber < bytes.length; byteNumber++){
            result += (bytes[byteNumber] & 0xFF) << (8 * (bytes.length - byteNumber - 1));
        }
        return result;
    }

    public static String getBinaryString(byte... bytes){
        String result = "";
        for (byte currentByte : bytes) {
            result += Integer.toBinaryString((1 << 8) + (currentByte & 0xFF)).substring(1);
        }
        return result;
    }

    public static String getBinaryString(Integer number){
        String result = Integer.toBinaryString(number);
        if(result.length() % 8 != 0){
            byte[] buffer = new byte[getNumberLength(number)];
            for (int counter = 0; counter < buffer.length; counter++){
                buffer[buffer.length - counter - 1] = (byte)(number >> counter * 8);
            }
            return getBinaryString(buffer);
        }
        return result;
    }
}
