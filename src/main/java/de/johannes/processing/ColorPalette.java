package de.johannes.processing;

import java.awt.*;

public interface ColorPalette {

    public String id();

    public Color keyword();
    public Color string();
    public Color operator();
    public Color symbol();
    public Color value();
    public Color indentifier();
    public Color comment();

    public Color background();

    public default Color get(String hex) {return Color.decode(hex);}

}
