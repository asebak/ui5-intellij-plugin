package com.atsebak.ui5.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configuration.EmptyRunProfileState;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class UI5RunConfiguration extends RunConfigurationBase {
    private UI5RunnerParameters runnerParameters = new UI5RunnerParameters();

    protected UI5RunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory, @NotNull String name) {
        super(project, factory, name);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new UI5RunConfigurationEditor();
    }

    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
        return EmptyRunProfileState.INSTANCE;
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {

    }

}
