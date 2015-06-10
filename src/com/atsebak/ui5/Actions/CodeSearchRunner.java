package com.atsebak.ui5.Actions;

import com.atsebak.ui5.Actions.ui.UI5ApiSearchDialog;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

import java.io.File;

/**
 * Created by asebak on 10/6/2014.
 */
class CodeSearchRunner {


    public static void execute(File editorFile, Editor editor, Project project) {
        SearchTermFinder searchTermFinder = new SearchTermFinder(editor);
        String selectedTerm = searchTermFinder.getSearchTerm();
        UI5ApiSearchDialog ui5ApiSearchDialog = new UI5ApiSearchDialog(project);
        ui5ApiSearchDialog.show(selectedTerm);
    }
}