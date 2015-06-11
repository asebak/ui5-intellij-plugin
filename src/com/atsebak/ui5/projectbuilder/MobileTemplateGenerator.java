package com.atsebak.ui5.projectbuilder;


import com.atsebak.ui5.config.UI5Library;
import com.atsebak.ui5.locale.UI5Bundle;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

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
    public void generateProject(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull UI5ProjectSettings settings, @NotNull Module module) {
        settings.setLibrary(UI5Library.MOBILE);
        super.generateProject(project, virtualFile, settings, module);
    }


}
