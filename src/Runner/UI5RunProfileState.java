package Runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Created by asebak on 10/4/2014.
 */
public class UI5RunProfileState extends CommandLineState {
    public UI5RunProfileState(Project project, ExecutionEnvironment executionEnvironment, UI5RunConfiguration ui5RunConfiguration) {
        super(executionEnvironment);

    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        return null;
    }
}
