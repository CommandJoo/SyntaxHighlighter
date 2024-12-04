package de.johannes;

import de.johannes.highlighting.Language;
import de.johannes.highlighting.SyntaxHighlighter;
import de.johannes.highlighting.colors.OneDark;
import de.johannes.processing.FileUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        SyntaxHighlighter highlighter = new SyntaxHighlighter(Language.PYTHON, FileUtil.readFile("tests/test.py"));
        highlighter.print(new OneDark());
        FileUtil.writeFile(highlighter.toHtml(new OneDark()), "output/test.html");
        System.out.print("\u001b[0m");
    }
}