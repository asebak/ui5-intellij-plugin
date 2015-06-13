package com.atsebak.ui5.runner;

import com.atsebak.ui5.locale.UI5Bundle;
import com.atsebak.ui5.util.UI5Icons;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@Getter
public class UI5RunConfigurationType implements ConfigurationType {
    private ConfigurationFactory factory;

    public UI5RunConfigurationType() {
        factory = new ConfigurationFactory(this) {
            public RunConfiguration createTemplateConfiguration(Project project) {
                return new UI5RunConfiguration(project, this, "OpenUI5");
            }
        };
    }

    @NotNull
    public static UI5RunConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(UI5RunConfigurationType.class);
    }

    @Override
    public String getDisplayName() {
        return "Open UI5";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return UI5Bundle.getString("run.app");
    }

    @Override
    public Icon getIcon() {
        return UI5Icons.getIcon();
    }

    @NotNull
    public String getId() {
        return getConfigurationTypeDescription();
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{factory};
    }
}
