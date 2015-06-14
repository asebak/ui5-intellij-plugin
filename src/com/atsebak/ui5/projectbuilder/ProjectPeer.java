package com.atsebak.ui5.projectbuilder;

import com.atsebak.ui5.FileType;
import com.atsebak.ui5.autogeneration.UI5View;
import com.atsebak.ui5.locale.UI5Bundle;
import com.atsebak.ui5.util.UI5FileBuilder;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.platform.WebProjectGenerator;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Enumeration;

public class ProjectPeer implements WebProjectGenerator.GeneratorPeer<UI5ProjectTemplateGenerator.UI5ProjectSettings> {
    private final java.util.List<WebProjectGenerator.SettingsStateListener> stateListeners = ContainerUtil.createLockFreeCopyOnWriteList();
    private UI5ProjectTemplateGenerator.UI5ProjectSettings settings = new UI5ProjectTemplateGenerator.UI5ProjectSettings();
    private ButtonGroup viewTypeButtonGroup = new ButtonGroup();
    private JRadioButton javascriptRadioButton;
    private JRadioButton XMLRadioButton;
    private JRadioButton HTMLRadioButton;
    private JRadioButton JSONRadioButton;
    private JPanel panel;
    private JPanel viewPanel;

    public ProjectPeer() {
        viewTypeButtonGroup.add(javascriptRadioButton);
        viewTypeButtonGroup.add(XMLRadioButton);
        viewTypeButtonGroup.add(HTMLRadioButton);
        viewTypeButtonGroup.add(JSONRadioButton);
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return null;
    }

    @Override
    public void buildUI(@NotNull SettingsStep settingsStep) {
        settingsStep.addSettingsField(UI5Bundle.getString("view.type"), viewPanel);
    }

    @NotNull
    @Override
    public UI5ProjectTemplateGenerator.UI5ProjectSettings getSettings() {

        String viewType = getSelectedButton(viewTypeButtonGroup);
        UI5View view = UI5FileBuilder.getViewImplementation(FileType.valueOf(viewType));

        settings.setView(view);

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

    /**
     * Gets the selected button on the UI
     *
     * @param buttonGroup
     * @return
     */
    private String getSelectedButton(@NotNull ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
