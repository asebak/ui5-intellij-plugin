package com.atsebak.ui5.actions.search;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

public class UI5ApiSearch extends AnAction {
    /**
     * Action performed from Intellij
     * @param anActionEvent
     */
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = (Project) anActionEvent.getDataContext().getData(DataConstants.PROJECT);
        Editor editor = (Editor) anActionEvent.getDataContext().getData(DataConstants.EDITOR);
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
