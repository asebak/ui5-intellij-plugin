package ProjectBuilder;

import Autogeneration.*;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.WebProjectGenerator;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created by asebak on 9/27/2014.
 * This class is responsible for the UI of the project creator
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

        //JPanel Add Views
        applicationTypeGroup.add(desktopTypeButton);
        applicationTypeGroup.add(mobileTypeButton);
        viewTypeButtonGroup.add(htmlViewButton);
        viewTypeButtonGroup.add(jsonViewButton);
        viewTypeButtonGroup.add(jsViewButton);
        viewTypeButtonGroup.add(xmlViewButton);

        //Button Groups to Handle Logic Selection
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
        UI5Library ui5Library = null;
        UI5View ui5View = null;
        String appType = getSelectedButton(applicationTypeButtonGroup);
        String viewType = getSelectedButton(viewTypeButtonGroup);
        if(appType == "Desktop"){
            ui5Library = UI5Library.Desktop;
        }
        else if(appType == "Mobile"){
            ui5Library = UI5Library.Mobile;
        }
        if(viewType == "Javascript"){
            ui5View = new JSView();
        }
        else if(viewType == "HTML"){
            ui5View = new HTMLView();
        }
        else if(viewType == "XML"){
            ui5View = new XMLView();
        }
        else if(viewType == "JSON"){
            ui5View = new JSONView();
        }
        settings.setUi5Library(ui5Library);
        settings.setUi5View(ui5View);
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

    public String getSelectedButton(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
