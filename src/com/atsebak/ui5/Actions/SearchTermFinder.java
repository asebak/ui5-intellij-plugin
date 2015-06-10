package com.atsebak.ui5.Actions;

import com.intellij.openapi.editor.Editor;

/**
 * Created by asebak on 10/6/2014.
 */
class SearchTermFinder {

    private final SymbolExtractor symbolExtractor;
    private final Editor editor;

    public SearchTermFinder(Editor editor) {
        this.editor = editor;
        this.symbolExtractor = new SymbolExtractor();
    }

    String getSearchTerm() {
        if (editor == null) {
            return null;
        }
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText != null && selectedText.length() != 0) {
            if (selectedText.contains("\n")) {
                return "";
            } else {
                return selectedText;
            }
        }
        //not selected text
        int caretOffset = editor.getCaretModel().getOffset();
        CharSequence charsSequence = editor.getDocument().getCharsSequence();
        return symbolExtractor.extract(charsSequence, caretOffset);
    }
}
