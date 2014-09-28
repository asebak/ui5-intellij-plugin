package ProjectBuilder;

import Autogeneration.UI5Library;
import Autogeneration.UI5View;
import Util.UI5Icons;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by asebak on 9/27/2014.
 */
public class UI5ProjectTemplateGenerator extends WebProjectTemplate<UI5ProjectTemplateGenerator.UI5ProjectSettings> {

    @Nls
    @NotNull
    @Override
    public String getName() {
        return "App";
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
        return UI5Icons.getIcon();
    }

    @NotNull
    @Override
    public UI5ProjectPeer createPeer() {
        return new UI5ProjectPeer();
    }

    final static class UI5ProjectSettings {
        private UI5View  ui5View;
        private UI5Library ui5Library;
        public UI5View getUi5View() {
            return ui5View;
        }

        public void setUi5View(UI5View ui5View) {
            this.ui5View = ui5View;
        }

        public UI5Library getUi5Library() {
            return ui5Library;
        }

        public void setUi5Library(UI5Library ui5Library) {
            this.ui5Library = ui5Library;
        }
    }
}

