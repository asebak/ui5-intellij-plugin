package com.atsebak.ui5.runner;

import com.intellij.ide.browsers.BrowserSelector;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class UI5RunConfigurationEditor extends SettingsEditor<UI5RunConfiguration> {
    private final BrowserSelector myBrowserSelector;
    private JPanel myMainPanel;
    private JPanel myBrowserSelectorPanel;

    public UI5RunConfigurationEditor() {
        myBrowserSelector = new BrowserSelector();
        myBrowserSelectorPanel.add(BorderLayout.CENTER, myBrowserSelector.getMainComponent());
    }

    @Override
    protected void resetEditorFrom(@NotNull UI5RunConfiguration runConfiguration) {
        UI5RunnerParameters params = runConfiguration.getRunnerParameters();
        myBrowserSelector.setSelected(params.getWebBrowser() != null ? params.getWebBrowser() : null);
    }

    @Override
    protected void applyEditorTo(@NotNull UI5RunConfiguration runConfiguration) throws ConfigurationException {
        UI5RunnerParameters params = runConfiguration.getRunnerParameters();
        params.setWebBrowser(myBrowserSelector.getSelected());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myMainPanel;
    }
}
