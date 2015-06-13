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
    protected void resetEditorFrom(UI5RunConfiguration s) {
        UI5RunnerParameters params = s.getRunnerParameters();
        myBrowserSelector.setSelected(params.getWebBrowser() != null ? params.getWebBrowser() : null);
    }

    @Override
    protected void applyEditorTo(UI5RunConfiguration s) throws ConfigurationException {
        UI5RunnerParameters params = s.getRunnerParameters();
        params.setWebBrowser(myBrowserSelector.getSelected());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myMainPanel;
    }
}
