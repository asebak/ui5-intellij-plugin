package com.atsebak.ui5.filebuilder;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NotNull;

/**
 * Used for File Templates Creation
 */
public class UI5CreateFileAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final DataContext dataContext = anActionEvent.getDataContext();

        final IdeView view = LangDataKeys.IDE_VIEW.getData(dataContext);
        if (view == null) {
            return;
        }

        final Project project = CommonDataKeys.PROJECT.getData(dataContext);

        final PsiDirectory dir = view.getOrChooseDirectory();
        if (dir == null || project == null) {
            return;
        }
        CreateFileDialog createFileDialog = new CreateFileDialog(project, dir);
        createFileDialog.show();
    }

}
