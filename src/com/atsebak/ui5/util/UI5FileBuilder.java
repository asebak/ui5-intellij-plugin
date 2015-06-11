package com.atsebak.ui5.util;


import com.atsebak.ui5.autogeneration.*;
import com.atsebak.ui5.config.UI5Type;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.regex.Pattern;

public final class UI5FileBuilder {

    /**
     * Creates a directory from a given name
     *
     * @param folder
     * @param name
     */
    public static void createDirectoryFromName(@NotNull File folder, @NotNull String name) {
        File file = new File(folder, name);
        file.mkdir();
    }

    /**
     * Gets the module path based on the psidirectory passed
     *
     * @param psiDirectory
     * @return
     */
    public static String getModulePath(@NotNull PsiDirectory psiDirectory) {
        Module module = ModuleUtilCore.findModuleForPsiElement(psiDirectory.getOriginalElement());
        if (module != null) {
            String[] projectDircs;
            String[] fileDircs;
            String projectPath = module.getProject().getBasePath();
            String filePath = new File(psiDirectory.getVirtualFile().getPath()).getPath();
            assert projectPath != null;
            projectDircs = projectPath.split(Pattern.quote(File.separator));
            fileDircs = filePath.split(Pattern.quote(File.separator));
            for (int i = 0; i < projectDircs.length; i++) {
                if (projectDircs[i].equals(fileDircs[i])) {
                    fileDircs[i] = "";
                }
            }
            String codePath = "";
            for (int i = 0; i < fileDircs.length; i++) {
                if (!fileDircs[i].isEmpty()) {
                    codePath += fileDircs[i];
                    if (i != fileDircs.length - 1) {
                        codePath += ".";
                    }
                }

            }
            return codePath;
        }
        return "";
    }

    /**
     * Get the interface implementation
     *
     * @param type
     * @return
     */
    public static UI5View getViewImplementation(@NotNull UI5Type type) {
        switch (type) {
            case JS:
                return new JSView();
            case XML:
                return new XMLView();
            case HTML:
                return new HTMLView();
            case JSON:
                return new JSONView();
        }
        return null;
    }
}
