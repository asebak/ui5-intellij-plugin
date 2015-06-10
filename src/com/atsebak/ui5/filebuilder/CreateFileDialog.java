package com.atsebak.ui5.filebuilder;

import com.atsebak.ui5.autogeneration.*;
import com.atsebak.ui5.config.UI5Library;
import com.atsebak.ui5.config.UI5Type;
import com.atsebak.ui5.util.Writer;
import com.intellij.compiler.impl.CompilerUtil;
import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.TemplateKindCombo;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CreateFileDialog extends DialogWrapper {
    @NotNull
    private final Project project;
    @NotNull
    private final PsiDirectory psiDirectory;
    private JPanel contentPane;
    private JTextField name;
    private JLabel myKindLabel;
    private TemplateKindCombo kindCombo;


    protected CreateFileDialog(@NotNull Project project, @NotNull PsiDirectory psiDirectory) {
        super(project);
        this.project = project;
        this.psiDirectory = psiDirectory;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    @Override
    protected void doOKAction() {
        final String fileName = name.getText();
        final String templateName = kindCombo.getSelectedName();
        addFile(fileName, templateName);
        super.doOKAction();
    }

    private void addFile(final String fileName, final String templateName) {
        final UI5Type type = UI5Type.valueOf(templateName);
        ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
            @Override
            public void run() {
                if (type.equals(UI5Type.PROPERTIES)) {
                    //enter manually properties file
                    try {
                        Writer.writeToFile(new File(psiDirectory.getVirtualFile().getPath() + File.separator + fileName.replace("." + UI5Type.PROPERTIES.name().toLowerCase(), "") + ".properties"), "");
//                        CompilerUtil.refreshIODirectories(Arrays.asList());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                String ext = type.name().toLowerCase();
                String newCreatedName = fileName.replace("." + ext, "") + ".view." + ext;
                String viewPath = psiDirectory.getVirtualFile().getPath() + File.separator + newCreatedName;
                String controllerPath = psiDirectory.getVirtualFile().getPath() + File.separator + fileName.replace("." + ext, "") + ".controller.js";
                File controllerFile = new File(controllerPath);
                File viewFile = new File(viewPath);
                UI5View ui5View = null;
                switch (type) {
                    case JS:
                        ui5View = new JSView();
                        break;
                    case XML:
                        ui5View = new XMLView();
                        break;
                    case HTML:
                        ui5View = new HTMLView();
                        break;
                    case JSON:
                        ui5View = new JSONView();
                        break;
                }

                //get the module path for autogeneration
                Module module = ModuleUtilCore.findModuleForPsiElement(psiDirectory.getOriginalElement());
                if (module != null) {
                    String[] projectDircs;
                    String[] fileDircs;
                    String projectPath = module.getProject().getBasePath();
                    String filePath = new File(psiDirectory.getVirtualFile().getPath()).getPath();
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
                    String viewCode = ui5View.autogenerateCode(UI5Library.Desktop, codePath + "." + fileName.replace("." + ext, ""));
                    String controllerCode = Controller.getAutogenerateCode(codePath, fileName.replace("." + ext, ""));
                    try {
                        Writer.writeToFile(controllerFile, controllerCode);
                        Writer.writeToFile(viewFile, viewCode);
                        CompilerUtil.refreshIODirectories(Arrays.asList(controllerFile, viewFile));
                    } catch (IOException e) {
                        Messages.showErrorDialog(psiDirectory.getProject(), e.getMessage(), "Create File from Template");
                    }
                }
            }
        }, "Adding Open UI5 File", false, project);
    }

    @Override
    protected void init() {
        kindCombo.addItem("Javascript View", AllIcons.FileTypes.JavaScript, UI5Type.JS.name());
        kindCombo.addItem("HTML View", AllIcons.FileTypes.Html, UI5Type.HTML.name());
        kindCombo.addItem("JSON View", AllIcons.FileTypes.Json, UI5Type.JSON.name());
        kindCombo.addItem("XML View", AllIcons.FileTypes.Xml, UI5Type.XML.name());
        kindCombo.addItem("i18n", AllIcons.FileTypes.Properties, UI5Type.PROPERTIES.name());
        super.init();
    }


}
