package com.atsebak.ui5.actions.search;

import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SearchTermFinderTest {

    private Editor editor = mock(Editor.class);
    final SearchTermFinder searchTermFinder = SearchTermFinder.builder().editor(editor).build();
    private SelectionModel selectionModel = mock(SelectionModel.class);
    private CaretModel caretModel = mock(CaretModel.class);
    private Document document = mock(Document.class);

    @Before
    public void setUp() throws Exception {
        when(editor.getSelectionModel()).thenReturn(selectionModel);
        when(editor.getCaretModel()).thenReturn(caretModel);
        when(editor.getDocument()).thenReturn(document);
    }

    @Test
    public void testBasicSearchTerm() {
        final String term = "sapui5";
        when(selectionModel.getSelectedText()).thenReturn(term);
        assertThat(searchTermFinder.getSearchTerm(), is(term));
    }

    @Test
    public void testEmptySearchTerm() {
        final String term = "\n";
        when(selectionModel.getSelectedText()).thenReturn(term);
        assertThat(searchTermFinder.getSearchTerm(), is(""));
    }

    @Test
    public void testSymbolSearchingFirstTerm() {
        final String term = "Open UI5";
        when(caretModel.getOffset()).thenReturn(1);
        when(document.getCharsSequence()).thenReturn(term);
        assertThat(searchTermFinder.getSearchTerm(), is("Open"));
    }

    @Test
    public void testSymbolSearchingSecondTerm() {
        final String term = "Open UI5";
        when(caretModel.getOffset()).thenReturn(5);
        when(document.getCharsSequence()).thenReturn(term);
        assertThat(searchTermFinder.getSearchTerm(), is("UI5"));
    }
}
