package com.atsebak.ui5.runner;

import com.atsebak.ui5.locale.UI5Bundle;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configuration.EmptyRunProfileState;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializer;
import lombok.Getter;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
public class UI5RunConfiguration extends RunConfigurationBase {
    private UI5RunnerParameters runnerParameters = new UI5RunnerParameters();

    protected UI5RunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory, @NotNull String name) {
        super(project, factory, name);
    }

    public static void checkURL(String url) throws RuntimeConfigurationException {
        try {
            if (url == null) {
                throw new MalformedURLException(UI5Bundle.getString("url.invalid"));
            }
            new URL(url);
        } catch (MalformedURLException ignored) {
            throw new RuntimeConfigurationError(UI5Bundle.getString("url.exception"));
        }
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new UI5RunConfigurationEditor();
    }

    protected UI5RunnerParameters createRunnerParametersInstance() {
        return new UI5RunnerParameters();
    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {
        super.readExternal(element);
        runnerParameters = createRunnerParametersInstance();
        XmlSerializer.deserializeInto(runnerParameters, element);
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
        super.writeExternal(element);
        if (runnerParameters != null) {
            XmlSerializer.serializeInto(runnerParameters, element);
        }
    }

    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
        return EmptyRunProfileState.INSTANCE;
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        checkURL(runnerParameters.getUrl());
    }

}
