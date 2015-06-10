package com.atsebak.ui5.actions;

import com.intellij.openapi.editor.Editor;
import lombok.Builder;

@Builder
public class SearchTermFinder {
    private Editor editor;

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

        int caretOffset = editor.getCaretModel().getOffset();
        CharSequence charsSequence = editor.getDocument().getCharsSequence();
        return SymbolExtractor.extract(charsSequence, caretOffset);
    }
}
