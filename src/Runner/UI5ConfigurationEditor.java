package Runner;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by asebak on 10/4/2014.
 */
public class UI5ConfigurationEditor extends SettingsEditor<UI5RunConfiguration> {
    private final Project project;

    public UI5ConfigurationEditor(Project project) {
        this.project = project;
    }

    @Override
    protected void resetEditorFrom(UI5RunConfiguration ui5RunConfiguration) {

    }

    @Override
    protected void applyEditorTo(UI5RunConfiguration ui5RunConfiguration) throws ConfigurationException {

    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return null;
    }
}
