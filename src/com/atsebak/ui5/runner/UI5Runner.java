package com.atsebak.ui5.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.ide.BuiltInServerManager;

public class UI5Runner extends DefaultProgramRunner {
    @Override
    protected RunContentDescriptor doExecute(@NotNull RunProfileState state, @NotNull ExecutionEnvironment env) throws ExecutionException {
        final RunProfile runProfileRaw = env.getRunProfile();
        if (runProfileRaw instanceof UI5RunConfiguration) {

            FileDocumentManager.getInstance().saveAllDocuments();
            final UI5RunConfiguration runProfile = (UI5RunConfiguration) runProfileRaw;
            final UI5RunnerParameters params = runProfile.getRunnerParameters();

            BuiltInServerManager serverManager = BuiltInServerManager.getInstance().waitForStart();
            BrowserLauncher.getInstance().browse("http://localhost:" + serverManager.getPort()
                    + getClientApplicationPath(env.getProject()), params.getWebBrowser(), env.getProject());
            return null;
        } else {
            return super.doExecute(state, env);
        }
    }

    @Override
    @NotNull
    public String getRunnerId() {
        return "OpenUI5Runner";
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        return DefaultRunExecutor.EXECUTOR_ID.equals(executorId) && (profile instanceof UI5RunConfiguration);
    }

    private String getClientApplicationPath(@NotNull Project project) {
        VirtualFile[] children = project.getBaseDir().getChildren();
        String browsePath = "/" + project.getName() + "/";
        for (int i = 0; i < children.length; i++) {
            if (children[i].getFileType() instanceof HtmlFileType) {
                browsePath += children[i].getName();
                break;
            }
        }
        return browsePath;
    }
}
