package com.atsebak.ui5.projectbuilder;


import com.atsebak.ui5.AppType;
import com.atsebak.ui5.locale.UI5Bundle;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MobileTemplateGenerator extends UI5ProjectTemplateGenerator {
    @Nls
    @NotNull
    @Override
    public String getName() {
        return UI5Bundle.getString("mobile.app");
    }

    @Override
    public String getDescription() {
        return UI5Bundle.getString("mobile.description");
    }

    @Override
    public Icon getIcon() {
        return AllIcons.General.CreateNewProject;
    }

    @Override
    public void generateProject(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull UI5ProjectSettings settings, @NotNull Module module) {
        settings.setLibrary(AppType.MOBILE);
        super.generateProject(project, virtualFile, settings, module);
    }


}
