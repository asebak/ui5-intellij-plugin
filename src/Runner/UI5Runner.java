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
package Runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class UI5Runner extends DefaultProgramRunner {
    @Override
    protected RunContentDescriptor doExecute(@NotNull Project project, @NotNull RunProfileState state, RunContentDescriptor contentToReuse,
                                             @NotNull ExecutionEnvironment env) throws ExecutionException {
        final RunProfile runProfileRaw = env.getRunProfile();
        if (runProfileRaw instanceof UI5RunConfiguration) {
            FileDocumentManager.getInstance().saveAllDocuments();
            final UI5RunConfiguration runProfile = (UI5RunConfiguration) runProfileRaw;
            final UI5RunnerParameters params = runProfile.getRunnerParameters();
            BrowserLauncher.getInstance().browse(params.getUrl(), params.getNonDefaultBrowser(), project);
            return null;
        } else {
            return super.doExecute(project, state, contentToReuse, env);
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
}