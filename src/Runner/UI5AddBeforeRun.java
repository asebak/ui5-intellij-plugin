package Runner;

import Util.UI5Icons;
import com.intellij.execution.BeforeRunTask;
import com.intellij.execution.BeforeRunTaskProvider;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by asebak on 9/27/2014.
 */
public class UI5AddBeforeRun extends BeforeRunTaskProvider<UI5AddBeforeRun.UI5AddPlatformTask> {

    @Override
    public Key<UI5AddPlatformTask> getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription(UI5AddPlatformTask ui5AddPlatformTask) {
        return null;
    }

    @Override
    public Icon getIcon() {
        return UI5Icons.getIcon("/Icons/ui5.png");
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Nullable
    @Override
    public UI5AddPlatformTask createTask(RunConfiguration runConfiguration) {
        return null;
    }

    @Override
    public boolean configureTask(RunConfiguration runConfiguration, UI5AddPlatformTask ui5AddPlatformTask) {
        return false;
    }

    @Override
    public boolean canExecuteTask(RunConfiguration runConfiguration, UI5AddPlatformTask ui5AddPlatformTask) {
        return false;
    }

    @Override
    public boolean executeTask(DataContext dataContext, RunConfiguration runConfiguration, ExecutionEnvironment executionEnvironment, UI5AddPlatformTask ui5AddPlatformTask) {
        return false;
    }

    public static class UI5AddPlatformTask extends BeforeRunTask<UI5AddPlatformTask> {


        protected UI5AddPlatformTask(@NotNull Key<UI5AddPlatformTask> providerId) {
            super(providerId);
            this.setEnabled(true);
        }
    }
}
