package FileBuilder;

import Autogeneration.Controller;
import Util.UI5Icons;
import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NonNls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by asebak on 10/4/2014.
 */
public class UI5CreateFileAction extends CreateFileFromTemplateAction implements DumbAware {
    @NonNls
    private static final String DEFAULT_HTML_TEMPLATE_PROPERTY = "DefaultCfmlFileTemplate";

    public UI5CreateFileAction() {
        super("UI5 Component", "Creates new UI5 Component", UI5Icons.getIcon());
    }

    @Override
    protected void postProcess(PsiFile createdElement, String templateName, Map<String, String> customProperties) {
        String[] templatePattern = templateName.split(Pattern.quote("."));
        String ext = templatePattern[templatePattern.length - 1];
        String createdName = createdElement.getName();
        String newCreatedName = createdName.replace("." + ext, "") + ".view." + ext;
        createdElement.setName(newCreatedName);
        String controllerFilePath = createdElement.getVirtualFile().getParent().getPath() + "\\" + createdName.replace("." + ext, "") + ".controller.js";
        File controllerFile = new File(controllerFilePath);

        //TODO First parameter should be module path
        String controllerCode = Controller.getAutogenerateCode("", createdName.replace("." + ext, ""));

        try {
            writeToFile(controllerFile, controllerCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Module module = ModuleUtilCore.findModuleForPsiElement(createdElement);
//        customProperties.put("AHMAD", "THEVALUE");
        if (module != null) {
//            ConfigureKotlinInProjectUtils.showConfigureKotlinNotificationIfNeeded(module);
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
//                .setValidator(new InputValidatorEx() {
//                    @Nullable
//                    @Override
//                    public String getErrorText(String inputString) {
//                        final String error = " is not a valid Haskell module name.";
//                        return null;
////                        if (inputString.isEmpty()) {
////                            return null;
////                        }
////                        if (VALID_MODULE_NAME_REGEX.matcher(inputString).matches()) {
////                            return null;
////                        }
////                        return '\'' + inputString + '\'' + error;
//                    }
//
//                    @Override
//                    public boolean checkInput(String inputString) {
//                        return true;
//                    }
//
//                    @Override
//                    public boolean canClose(String inputString) {
//                        return getErrorText(inputString) == null;
//                    }
//                });
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "UI5 file";
    }

    private void writeToFile(File file, String content) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.close();
    }
}
