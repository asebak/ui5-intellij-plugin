package com.atsebak.ui5.actions;

/**
 * Created by asebak on 10/6/2014.
 */
public class SymbolExtractor {

    public String extract(CharSequence charSequence, int position) {
        if (position > charSequence.length()) {
            throw new IllegalStateException();
        }
        if (position < 0) {
            throw new IllegalStateException();
        }

        // the start is inclusive, walk backwards starting immediately before c
        int first = position;
        for (int c = position - 1; c >= 0; c--) {
            if (Character.isJavaIdentifierPart(charSequence.charAt(c))) {
                first = c;
            } else {
                break;
            }
        }

        // the end is exclusive, walk forwards starting at c
        int last = position;
        for (int c = last; c <= charSequence.length(); c++) {
            if (c == charSequence.length() || !Character
                    .isJavaIdentifierPart(charSequence.charAt(c))) {
                last = c;
                break;
            }
        }

        return charSequence.subSequence(first, last).toString();
    }
}
