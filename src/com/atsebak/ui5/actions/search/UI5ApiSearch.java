package com.atsebak.ui5.actions.search;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

public class UI5ApiSearch extends AnAction {
    /**
     * Action performed from Intellij
     * @param anActionEvent
     */
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        final DataContext dataContext = anActionEvent.getDataContext();
        final Project project = CommonDataKeys.PROJECT.getData(dataContext);
        Editor editor = CommonDataKeys.EDITOR.getData(dataContext);
        if (project == null) {
            return;
        }
        execute(editor, project);
    }

    /**
     * Executes the action
     * @param editor
     * @param project
     */
    private void execute(Editor editor, Project project) {
        String selectedTerm = SearchTermFinder.builder()
                .editor(editor)
                .build()
                .getSearchTerm();
        UI5ApiSearchDialog.builder().project(project).build().show(selectedTerm);
    }
}
