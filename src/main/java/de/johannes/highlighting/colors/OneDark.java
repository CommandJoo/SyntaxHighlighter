package de.johannes.highlighting.colors;

import de.johannes.processing.ColorPalette;

import java.awt.*;

public class OneDark implements ColorPalette {
    @Override
    public String id() {
        return "ONE_DARK";
    }

    @Override
    public Color keyword() {
        return get("#bb70d2");
    }

    @Override
    public Color string() {
        return get("#8fb573");
    }

    @Override
    public Color operator() {
        return get("#818387");
    }

    @Override
    public Color symbol() {
        return get("#818387");
    }

    @Override
    public Color value() {
        return get("#c49060");
    }

    @Override
    public Color indentifier() {
        return get("#de5d68");
    }

    @Override
    public Color comment() {
        return get("#818387");
    }

    @Override
    public Color background() {
        return get("#232326");
    }
}
