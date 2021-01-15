package org.SimpleDictionaryService;

@Deprecated
public class Symbol {
    private byte[] bytes;
    private Language language;

    private Symbol(byte[] bytes, Language language){
        this.bytes = bytes;
        this.language = language;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
