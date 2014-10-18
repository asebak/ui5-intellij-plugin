package FileBuilder;

import Autogeneration.*;
import Util.UI5Icons;
import Util.Writer;
import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by asebak on 10/4/2014.
 * Used for File Templates Creation
 */
public class UI5CreateFileAction extends CreateFileFromTemplateAction implements DumbAware {
    @NonNls
    private static final String DEFAULT_HTML_TEMPLATE_PROPERTY = "";

    public UI5CreateFileAction() {
        super("UI5 Component", "Creates new UI5 Component", UI5Icons.getIcon());
    }

    @Override
    protected void postProcess(PsiFile createdElement, String templateName, Map<String, String> customProperties) {
        //TODO implement it as a background task
        String[] templatePattern = templateName.split(Pattern.quote("."));
        String ext = templatePattern[templatePattern.length - 1];
        String createdName = createdElement.getName();
        String newCreatedName = createdName.replace("." + ext, "") + ".view." + ext;
        createdElement.setName(newCreatedName);
        String viewFilePath = createdElement.getVirtualFile().getParent().getPath() + "\\" + newCreatedName;
        String controllerFilePath = createdElement.getVirtualFile().getParent().getPath() + "\\" + createdName.replace("." + ext, "") + ".controller.js";
        File controllerFile = new File(controllerFilePath);
        File viewFile = new File(viewFilePath);
        UI5View ui5View = null;
        if (ext.equals("js")) {
            ui5View = new JSView();
        } else if (ext.equals("xml")) {
            ui5View = new XMLView();
        } else if (ext.equals("json")) {
            ui5View = new JSONView();
        } else if (ext.equals("html")) {
            ui5View = new HTMLView();
        } else {

        }

        Module module = ModuleUtilCore.findModuleForPsiElement(createdElement);
//        createdElement.getOriginalFile().delete();
        if (module != null) {
            String[] projectDircs;
            String[] fileDircs;
            String projectPath = module.getProject().getBasePath();
            String filePath = new File(createdElement.getVirtualFile().getParent().getPath()).getPath();
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
            String viewCode = ui5View.autogenerateCode(UI5Library.Desktop, codePath + "." + createdName.replace("." + ext, ""));
            String controllerCode = Controller.getAutogenerateCode(codePath, createdName.replace("." + ext, ""));
            try {
                Writer.writeToFile(controllerFile, controllerCode);
                Writer.writeToFile(viewFile, viewCode);
            } catch (IOException e) {
                Messages.showErrorDialog(createdElement.getProject(), e.getMessage(), "Create File from Template");
                e.printStackTrace();
            }
        }


        super.postProcess(createdElement, templateName, customProperties);
    }

    @Override
    protected String getDefaultTemplateProperty() {
        return DEFAULT_HTML_TEMPLATE_PROPERTY;
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("UI5 Component")
                .addKind("Javascript View", AllIcons.FileTypes.JavaScript, "ui5.view.js")
                .addKind("HTML View", AllIcons.FileTypes.Html, "ui5.view.html")
                .addKind("JSON View", AllIcons.FileTypes.Json, "ui5.view.json")
                .addKind("XML View", AllIcons.FileTypes.Xml, "ui5.view.xml")
                .addKind("i18n", AllIcons.FileTypes.Properties, "ui5.properties");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "UI5 file";
    }


}
