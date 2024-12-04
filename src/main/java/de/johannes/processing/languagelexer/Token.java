package de.johannes.processing.languagelexer;

public class Token {

    public final String value;
    public final String type;

    public Token(String value, String type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return "[type: "+type+", value: \""+value+"\"]\n";
    }
}
