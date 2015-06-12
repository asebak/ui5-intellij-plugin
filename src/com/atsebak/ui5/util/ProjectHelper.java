package com.atsebak.ui5.util;


import com.atsebak.ui5.locale.UI5Bundle;
import com.atsebak.ui5.runner.UI5RunConfiguration;
import com.atsebak.ui5.runner.UI5RunConfigurationType;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.ide.browsers.WebBrowserManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public final class ProjectHelper {
    public static void addRunConfiguration(@NotNull final Project project) {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                final RunManager runManager = RunManager.getInstance(project);
                final RunnerAndConfigurationSettings settings = runManager.
                        createRunConfiguration(project.getName(), UI5RunConfigurationType.getInstance().getFactory());
                final UI5RunConfiguration configuration = (UI5RunConfiguration) settings.getConfiguration();

                configuration.setName(UI5Bundle.getString("app.runner.name"));
                configuration.getRunnerParameters().setWebBrowser(WebBrowserManager.getInstance().getFirstActiveBrowser());

                runManager.addConfiguration(settings, false);
                runManager.setSelectedConfiguration(settings);
            }
        };
        r.run();
    }
}
