package com.atsebak.ui5.filebuilder;

import com.atsebak.ui5.autogeneration.Controller;
import com.atsebak.ui5.autogeneration.UI5View;
import com.atsebak.ui5.config.UI5Library;
import com.atsebak.ui5.config.UI5Type;
import com.atsebak.ui5.locale.UI5Bundle;
import com.atsebak.ui5.util.UI5FileBuilder;
import com.atsebak.ui5.util.Writer;
import com.intellij.compiler.impl.CompilerUtil;
import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.TemplateKindCombo;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.psi.PsiDirectory;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;

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
        final String ext = "." + type.name().toLowerCase();
        final String fileNameWithOutExt = fileName.replace(ext, "");
        final String contextPath = psiDirectory.getVirtualFile().getPath() + File.separator;
        ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                if (type.equals(UI5Type.PROPERTIES)) {
                    File file = new File(contextPath + fileNameWithOutExt + ext);
                    Writer.writeToFile(file, "");
                    CompilerUtil.refreshIODirectories(Arrays.asList(file));
                    return;
                }

                String newCreatedName = fileNameWithOutExt + ".view" + ext;
                String viewPath = contextPath + newCreatedName;
                String controllerPath = contextPath + fileNameWithOutExt + ".controller.js";
                File controllerFile = new File(controllerPath);
                File viewFile = new File(viewPath);

                UI5View ui5View = UI5FileBuilder.getViewImplementation(type);

                String modulePath = UI5FileBuilder.getModulePath(psiDirectory);

                String viewCode = ui5View.autogenerateCode(UI5Library.DESKTOP, modulePath + "." + fileNameWithOutExt);
                String controllerCode = Controller.getAutogenerateCode(modulePath, fileNameWithOutExt);

                Writer.writeToFile(controllerFile, controllerCode);
                Writer.writeToFile(viewFile, viewCode);

                CompilerUtil.refreshIODirectories(Arrays.asList(controllerFile, viewFile));

            }
        }, UI5Bundle.getString("file.adding"), false, project);
    }

    @Override
    protected void init() {
        kindCombo.addItem(UI5Bundle.getString("javascript.view"), AllIcons.FileTypes.JavaScript, UI5Type.JS.name());
        kindCombo.addItem(UI5Bundle.getString("html.view"), AllIcons.FileTypes.Html, UI5Type.HTML.name());
        kindCombo.addItem(UI5Bundle.getString("json.view"), AllIcons.FileTypes.Json, UI5Type.JSON.name());
        kindCombo.addItem(UI5Bundle.getString("xml.view"), AllIcons.FileTypes.Xml, UI5Type.XML.name());
        kindCombo.addItem(UI5Bundle.getString("i18n.properties"), AllIcons.FileTypes.Properties, UI5Type.PROPERTIES.name());
        super.init();
    }


    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        if (!StringUtils.isNotBlank(name.getText())) {
            return new ValidationInfo(UI5Bundle.getString("view.noname"), name);
        }
        return null;
    }


}
