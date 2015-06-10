
package com.atsebak.ui5.runner;

import com.atsebak.ui5.util.UI5Icons;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class UI5RunConfigurationType implements ConfigurationType {
    private ConfigurationFactory myConfigurationFactory;

    public UI5RunConfigurationType() {
        myConfigurationFactory = new ConfigurationFactory(this) {
            public RunConfiguration createTemplateConfiguration(Project project) {
                return new UI5RunConfiguration(project, this, "OpenUI5");
            }
        };
    }

    public String getDisplayName() {
        return "OpenUI5";
    }

    public String getConfigurationTypeDescription() {
        return "Run an OpenUI5 App";
    }

    public Icon getIcon() {
        return UI5Icons.getIcon();
    }

    @NotNull
    public String getId() {
        return getConfigurationTypeDescription();
    }

    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{myConfigurationFactory};
    }
}
