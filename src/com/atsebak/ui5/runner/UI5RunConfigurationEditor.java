/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.atsebak.ui5.runner;

import com.intellij.ide.browsers.BrowserSelector;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class UI5RunConfigurationEditor extends SettingsEditor<UI5RunConfiguration> {
    private JPanel myMainPanel;
    private JTextField myWebPathField;
    private JPanel myBrowserSelectorPanel;
    private final BrowserSelector myBrowserSelector;

    public UI5RunConfigurationEditor() {
        myBrowserSelector = new BrowserSelector();
        myBrowserSelectorPanel.add(BorderLayout.CENTER, myBrowserSelector.getMainComponent());
    }

    @Override
    protected void resetEditorFrom(UI5RunConfiguration s) {
        UI5RunnerParameters params = s.getRunnerParameters();
        myWebPathField.setText(params.getUrl());
        myBrowserSelector.setSelected(params.getWebBrowser() != null ? params.getWebBrowser() : null);
    }

    @Override
    protected void applyEditorTo(UI5RunConfiguration s) throws ConfigurationException {
        UI5RunnerParameters params = s.getRunnerParameters();
        params.setUrl(myWebPathField.getText());
        params.setWebBrowser(myBrowserSelector.getSelected());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myMainPanel;
    }
}
