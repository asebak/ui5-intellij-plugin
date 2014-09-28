package ProjectBuilder;

import Util.UI5Icons;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;

/**
 * Created by asebak on 9/27/2014.
 */
public class UI5ProjectTemplateGenerator extends WebProjectTemplate<UI5ProjectTemplateGenerator.UI5ProjectSettings> {

    @Nls
    @NotNull
    @Override
    public String getName() {
        return "OpenUI5 App";
    }

    @Override
    public String getDescription() {
        return "<html>OpenUI5 Project Template for Mobile or Desktop Apps</html>";
    }


    @Override
    public void generateProject(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull UI5ProjectSettings ui5ProjectSettings, @NotNull Module module) {
        try {

            ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
                @Override
                public void run() {

                }
            }, "Creating OpenUI5 project", false, project);

            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
        catch (Exception e) {
        }
    }

    @Override
    public Icon getIcon() {
        return UI5Icons.getIcon("/Icons/ui5.png");
    }

    @NotNull
    @Override
    public UI5ProjectPeer createPeer() {
        return new UI5ProjectPeer();
    }

    final static class UI5ProjectSettings {
        private String name = "example";
        private String executable;

        public void setExecutable(String executable) {
            this.executable = executable;
        }

        public String getExecutable() {
            return executable;
        }

        public String name() {
            return name;
        }
    }
}

