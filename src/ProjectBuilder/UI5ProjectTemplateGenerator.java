package ProjectBuilder;

import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by asebak on 9/27/2014.
 */
public class UI5ProjectTemplateGenerator extends WebProjectTemplate<UI5ProjectTemplateGenerator.UI5ProjectSettings> {

    @Nls
    @NotNull
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }


    @Override
    public void generateProject(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull UI5ProjectSettings ui5ProjectSettings, @NotNull Module module) {

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

