package ProjectBuilder;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.WebProjectGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by asebak on 9/27/2014.
 */
public class UI5ProjectPeer implements WebProjectGenerator.GeneratorPeer<UI5ProjectTemplateGenerator.UI5ProjectSettings> {
    @NotNull
    @Override
    public JComponent getComponent() {
        return null;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {

    }

    @NotNull
    @Override
    public UI5ProjectTemplateGenerator.UI5ProjectSettings getSettings() {
        return null;
    }

    @Nullable
    @Override
    public ValidationInfo validate() {
        return null;
    }

    @Override
    public boolean isBackgroundJobRunning() {
        return false;
    }

    @Override
    public void addSettingsStateListener(@NotNull WebProjectGenerator.SettingsStateListener settingsStateListener) {

    }
}
