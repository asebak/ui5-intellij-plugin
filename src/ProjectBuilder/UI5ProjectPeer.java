package ProjectBuilder;

import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.WebProjectGenerator;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by asebak on 9/27/2014.
 */
public class UI5ProjectPeer implements WebProjectGenerator.GeneratorPeer<UI5ProjectTemplateGenerator.UI5ProjectSettings> {
    private final java.util.List<WebProjectGenerator.SettingsStateListener> stateListeners = ContainerUtil.createLockFreeCopyOnWriteList();
    private JPanel applicationTypeGroup =  new JPanel();
    private JPanel viewTypeGroup=  new JPanel();
    private ButtonGroup applicationTypeButtonGroup = new ButtonGroup();
    private ButtonGroup viewTypeButtonGroup = new ButtonGroup();
    private JRadioButton desktopTypeButton = new JRadioButton("Desktop");
    private JRadioButton mobileTypeButton = new JRadioButton("Mobile");
    private JRadioButton htmlViewButton = new JRadioButton("HTML");
    private JRadioButton jsonViewButton = new JRadioButton("JSON");
    private JRadioButton jsViewButton = new JRadioButton("Javascript");
    private JRadioButton xmlViewButton = new JRadioButton("XML");

    @NotNull
    @Override
    public JComponent getComponent() {
        return null;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {
        initializeViews();
        settingsStep.addSettingsField("Project Type:", applicationTypeGroup);
        settingsStep.addSettingsField("Initial View Type:", viewTypeGroup);
    }

    private void initializeViews(){
        applicationTypeGroup.setLayout(new FlowLayout());
        viewTypeGroup.setLayout(new FlowLayout());

        //Default Selection
        desktopTypeButton.setSelected(true);
        jsViewButton.setSelected(true);
        //Button Groups to Handle Logic Selection
        applicationTypeGroup.add(desktopTypeButton);
        applicationTypeGroup.add(mobileTypeButton);
        viewTypeButtonGroup.add(htmlViewButton);
        viewTypeButtonGroup.add(jsonViewButton);
        viewTypeButtonGroup.add(jsViewButton);
        viewTypeButtonGroup.add(xmlViewButton);

        //JPanel Add Views
        applicationTypeButtonGroup.add(desktopTypeButton);
        applicationTypeButtonGroup.add(mobileTypeButton);
        viewTypeGroup.add(jsViewButton);
        viewTypeGroup.add(xmlViewButton);
        viewTypeGroup.add(htmlViewButton);
        viewTypeGroup.add(jsonViewButton);

    }

    @NotNull
    @Override
    public UI5ProjectTemplateGenerator.UI5ProjectSettings getSettings() {
        UI5ProjectTemplateGenerator.UI5ProjectSettings settings = new UI5ProjectTemplateGenerator.UI5ProjectSettings();
        return settings;
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
        stateListeners.add(settingsStateListener);
    }
}
