package FileBuilder;

import Util.UI5Icons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NonNls;

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
    protected String getDefaultTemplateProperty() {
        return DEFAULT_HTML_TEMPLATE_PROPERTY;
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder
                .setTitle("UI5 Component")
                .addKind("Javascript View", UI5Icons.getIcon(), "ColdFusion File.cfm")
                .addKind("HTML View", UI5Icons.getIcon(), "ColdFusion Script Component.cfc")
                .addKind("JSON View", UI5Icons.getIcon(), "ColdFusion Script Interface.cfc")
                .addKind("XML View", UI5Icons.getIcon(), "ColdFusion Tag Component.cfc")
                .addKind("i18n", UI5Icons.getIcon(), "ColdFusion Tag Interface.cfc");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "UI5 file";
    }
}
