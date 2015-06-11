package com.atsebak.ui5.actions.search;

import com.atsebak.ui5.util.SymbolExtractor;
import com.intellij.openapi.editor.Editor;
import lombok.Builder;

@Builder
public class SearchTermFinder {
    private Editor editor;

    public String getSearchTerm() {
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText != null && selectedText.length() != 0) {
            if (selectedText.contains("\n")) {
                return "";
            } else {
                return selectedText;
            }
        }

        int caretOffset = editor.getCaretModel().getOffset();
        CharSequence charsSequence = editor.getDocument().getCharsSequence();
        return SymbolExtractor.extract(charsSequence, caretOffset);
    }
}
