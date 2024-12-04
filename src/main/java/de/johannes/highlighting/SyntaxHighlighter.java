package de.johannes.highlighting;

import de.johannes.processing.ColorPalette;
import de.johannes.processing.languagelexer.Token;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class SyntaxHighlighter {

    private final Language language;
    private final List<Token> tokens;

    public SyntaxHighlighter(Language language, String code) {
        this.language = language;
        tokens = language.lexer().lex(code);
    }

    public void print(ColorPalette palette) {
        for(Token token : tokens) {
            if(token.type.equals("NEWLINE") || token.type.equals("INDENTATION")) {
                System.out.print(token.value);
            }else {
                switch (token.type) {
                    case "KEYWORD" -> printColor(palette.keyword(), token.value);
                    case "VALUE" -> printColor(palette.value(), token.value);
                    case "STRING" -> printColor(palette.string(), token.value);
                    case "SYMBOL" -> printColor(palette.symbol(), token.value);
                    case "OPERATOR" -> printColor(palette.operator(), token.value);
                    case "IDENTIFIER" -> printColor(palette.indentifier(), token.value);
                    case "COMMENT" -> printColor(palette.comment(), token.value);
                    default -> {
                        HashMap<String, Color> customs = language.lexer().getCustoms(palette.id());
                        if(customs == null) {
                            System.out.print("\u001b[0m"+token.value);
                        }else {
                            printColor(customs.getOrDefault(token.type, Color.WHITE), token.value);
                        }
                    }
                }
            }
        }
        int i = 0;
    }

    public String toHtml(ColorPalette palette) {
        StringBuilder builder = new StringBuilder("<div style=\"font-family: 'JetBrains Mono';font-size: 22px; padding: 10px; background-color: "+String.format("#%02x%02x%02x", palette.background().getRed(), palette.background().getGreen(), palette.background().getBlue())+"\">\n");
        for(Token token : tokens) {
            if(token.type.equals("NEWLINE")) {
                builder.append("<br>\n".repeat(token.value.length()));
            } else if(token.type.equals("INDENTATION")) {
                builder.append("&nbsp".repeat(token.value.length()));
            }else {
                switch (token.type) {
                    case "KEYWORD" -> builder.append(htmlColor(palette.keyword(), token.value));
                    case "VALUE" -> builder.append(htmlColor(palette.value(), token.value));
                    case "STRING" -> builder.append(htmlColor(palette.string(), token.value));
                    case "SYMBOL" -> builder.append(htmlColor(palette.symbol(), token.value));
                    case "OPERATOR" -> builder.append(htmlColor(palette.operator(), token.value));
                    case "IDENTIFIER" -> builder.append(htmlColor(palette.indentifier(), token.value));
                    case "COMMENT" -> builder.append(htmlColor(palette.comment(), token.value));
                    default -> {
                        HashMap<String, Color> customs = language.lexer().getCustoms(palette.id());
                        if(customs == null) {
                            System.out.print("\u001b[0m"+token.value);
                        }else {
                            builder.append(htmlColor(customs.getOrDefault(token.type, Color.WHITE), token.value));
                        }
                    }
                }
            }
        }
        builder.append("\n</div>");
        return builder.toString();
    }

    private void printColor(Color c, String word) {
        System.out.print("\u001b[38;2;"+c.getRed()+";"+c.getGreen()+";"+c.getBlue()+"m"+word);
    }
    private String htmlColor(Color c, String word) {
        return "<span style=\"color: "+String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue())+"\">"+word+"</span>";
    }

}
