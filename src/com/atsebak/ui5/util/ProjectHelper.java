package com.atsebak.ui5.util;


import com.atsebak.ui5.runner.UI5RunConfiguration;
import com.atsebak.ui5.runner.UI5RunConfigurationType;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.ide.browsers.WebBrowserManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.RefreshQueue;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class ProjectHelper {

    private ProjectHelper() {

    }
    /**
     * Adds a run configuration to project
     * @param project
     */
    public static void addRunConfiguration(@NotNull final Project project) {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                final RunManager runManager = RunManager.getInstance(project);
                final RunnerAndConfigurationSettings settings = runManager.
                        createRunConfiguration(project.getName(), UI5RunConfigurationType.getInstance().getFactory());
                final UI5RunConfiguration configuration = (UI5RunConfiguration) settings.getConfiguration();

                configuration.setName("Run " + project.getName());
                configuration.getRunnerParameters().setWebBrowser(WebBrowserManager.getInstance().getFirstActiveBrowser());

                runManager.addConfiguration(settings, false);
                runManager.setSelectedConfiguration(settings);
            }
        };
        r.run();
    }

    /**
     * Refereshs path after adding files
     * @param files
     */
    public static void refreshAfterAdd(@NotNull Collection<File> files) {
        LocalFileSystem lfs = LocalFileSystem.getInstance();
        ArrayList filesToRefresh = new ArrayList();
        Iterator iterator = files.iterator();

        while (iterator.hasNext()) {
            File file = (File) iterator.next();
            VirtualFile virtualFile = lfs.refreshAndFindFileByIoFile(file);
            if (virtualFile != null) {
                filesToRefresh.add(virtualFile);
            }
        }

        if (!filesToRefresh.isEmpty()) {
            RefreshQueue.getInstance().refresh(false, true, null, filesToRefresh);
        }
    }
}
