package com.atsebak.ui5.Actions;

import com.atsebak.ui5.Actions.utils.Utils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

import java.io.File;

/**
 * Created by asebak on 10/6/2014.
 */
public class UI5ApiSearch extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = Utils.projectFor(anActionEvent);
        Editor editor = Utils.editorFor(anActionEvent);
        File file = Utils.fileFor(anActionEvent);
        if (project == null) {
            return;
        }
        CodeSearchRunner.execute(file, editor, project);
    }
}
