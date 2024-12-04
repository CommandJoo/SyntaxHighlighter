package de.johannes.processing.languagelexer;

import de.johannes.processing.ColorPalette;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class SimpleLexer {

    public abstract List<String> keywords();
    public abstract List<String> operators();
    public abstract List<String> symbols();
    public abstract List<String> values(); //things like true and false
    public abstract List<String> strings();//characters for opening and closing strings
    public abstract List<String> comments();
    public abstract String identifier();

    private final List<String> tokenTypes = List.of("KEYWORD", "OPERATOR", "SYMBOL", "VALUE", "STRING", "IDENTIFIER", "INDENTATION", "COMMENT", "NEWLINE", "UNDEFINED");
    private final HashMap<List<String>, String> matchers = new HashMap<>();
    private final HashMap<String, HashMap<String, Color>> customColors = new HashMap<>();

    public void registerMatcher(List<String> matches, String type) {
        matchers.put(matches, type);
    }

    public void registerThemeVariable(String type, String colorPalette, Color color) {
        if(customColors.containsKey(colorPalette)) {
            customColors.get(colorPalette).put(type, color);
        }else{
            HashMap<String, Color> value = new HashMap<>();
            value.put(type, color);
            customColors.put(colorPalette, value);
        }
    }

    private LinkedList<Character> source;

    private final LinkedList<Character> toSource(String source) {
        LinkedList<Character> result = new LinkedList<>();
        for (String s : source.split("")) {
            result.add(s.charAt(0));
        }
        return result;
    }

    public SimpleLexer() {}

    public List<Token> lex(String code) {
        this.source = toSource(code);

        List<Token> result = new ArrayList<>();
        while (!source.isEmpty()) {
            char c = source.removeFirst();
            Token checked = checkCharacter((char)0, c);
            if(checked != null) {
                result.add(checked);
            }
            else {
                StringBuilder word = new StringBuilder(""+c);
                while(!source.isEmpty()) {
                    char first = source.removeFirst();
                    Token check = checkCharacter(c, first);
                    if(check != null) {
                        result.add(checkWord(word.toString()));
                        result.add(check);
                        break;
                    }else {
                        word.append(first);
                    }
                }
                if(source.isEmpty()) {
                    result.add(checkWord(word.toString()));
                }
            }
        }
        return result;

    }

    public Token checkWord(String word) {
        for(List<String> matcher : this.matchers.keySet()) {
            if(checkMatch(word, matcher)) {
                return new Token(word, this.matchers.get(matcher));
            }
        }
        if(keywords().contains(word)) {
            return new Token(word, "KEYWORD");
        }else if(checkMatch(word, values())) {
            return new Token(word, "VALUE");
        }else if(checkMatch(word, identifier())) {
            return new Token(word, "IDENTIFIER");
        }else{
            return new Token(word, "UNDEFINED");
        }
    }
    public boolean checkMatch(String word, List<String> regexes) {
        for(String regex : regexes) {
            if(word.matches(regex)) return true;
        }
        return false;
    }
    public boolean checkMatch(String word, String regex) {
         return word.matches(regex);
    }
    public Token checkCharacter(Character pre, Character c) {
        if(comments().contains(""+c)) {
            StringBuilder comment = new StringBuilder(c.toString());
            while(!source.isEmpty() && source.getFirst() != '\n') {
                comment.append(source.removeFirst());
            }
            return new Token(comment.toString(), "COMMENT");
        }
        else if (Character.isWhitespace(c) && c != '\n') {
            int ws = 1;
            while (!source.isEmpty() && (Character.isWhitespace(source.getFirst()) && source.getFirst() != '\n')) {
                ws++;
                source.removeFirst();
            }
            return new TokenIndent(ws);
        }
        else if(c == '\n') {
            int nl = 1;
            while (!source.isEmpty() && source.getFirst()=='\n') {
                nl++;
                source.removeFirst();
            }
            return new TokenNewline(nl);
        }
        else if(symbols().contains(""+c)) {
            return new Token(""+c, "SYMBOL");
        }
        else if(operators().contains(""+c)) {
            return new Token(""+c, "OPERATOR");
        }else if(strings().contains(""+c)) {
            StringBuilder builder = new StringBuilder(""+c);
            while(!source.isEmpty() && !strings().contains(source.getFirst().toString())) {
                if(source.getFirst() == '\\') {
                    if(source.get(1) == '\"') {
                        builder.append("\\\"");
                        source.removeFirst();
                        source.removeFirst();
                    }else{

                        builder.append(source.removeFirst());
                    }
                }
                builder.append(source.removeFirst());
            }
            builder.append(source.removeFirst());
            return new Token(builder.toString(), "STRING");
        }
        return null;
    }

    public HashMap<String, Color> getCustoms(String themeId) {
        return customColors.getOrDefault(themeId, null);
    }

}
