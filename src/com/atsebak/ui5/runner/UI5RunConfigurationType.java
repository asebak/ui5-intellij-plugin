package com.atsebak.ui5.runner;

import com.atsebak.ui5.locale.UI5Bundle;
import com.atsebak.ui5.util.UI5Icons;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class UI5RunConfigurationType implements ConfigurationType {
    private ConfigurationFactory configurationFactory;

    public UI5RunConfigurationType() {
        configurationFactory = new ConfigurationFactory(this) {
            public RunConfiguration createTemplateConfiguration(Project project) {
                return new UI5RunConfiguration(project, this, "OpenUI5");
            }
        };
    }

    public String getDisplayName() {
        return "Open UI5";
    }

    public String getConfigurationTypeDescription() {
        return UI5Bundle.getString("run.app");
    }

    public Icon getIcon() {
        return UI5Icons.getIcon();
    }

    @NotNull
    public String getId() {
        return getConfigurationTypeDescription();
    }

    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{configurationFactory};
    }
}
