package com.atsebak.ui5.actions.search;

import com.atsebak.ui5.locale.UI5Bundle;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

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
        if (project == null || editor == null) {
            return;
        }
        execute(editor, project);
    }

    /**
     * Executes the action
     * @param editor
     * @param project
     */
    private void execute(@NotNull Editor editor, @NotNull Project project) {
        String selectedTerm = SearchTermFinder.builder()
                .editor(editor)
                .build()
                .getSearchTerm();
        if (StringUtils.isNotBlank(selectedTerm)) {
            UI5ApiSearchDialog.builder().project(project).build().show(selectedTerm);
        } else {
            Messages.showErrorDialog(project, UI5Bundle.getString("api.search.errormsg"), UI5Bundle.getString("error"));
        }
    }
}
