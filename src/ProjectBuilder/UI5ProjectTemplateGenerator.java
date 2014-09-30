package ProjectBuilder;

import Autogeneration.Controller;
import Autogeneration.Index;
import Autogeneration.UI5Library;
import Autogeneration.UI5View;
import Util.UI5Icons;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by asebak on 9/27/2014.
 * This is for the sub template of the project. \
 * Once the finished but is click generateProject method is called and runs a seperate process
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
    public void generateProject(@NotNull Project project, @NotNull final VirtualFile virtualFile, @NotNull final UI5ProjectSettings settings, @NotNull Module module) {
        try {

            ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
                @Override
                public void run() {
                    try {
                        ProgressIndicator indicator = ProgressManager.getInstance().getProgressIndicator();
                        indicator.setText("Creating OpenUI5 Project");
                        File tempProject = createTemp();



                        Autogeneration.Index index = new Index();
                        String ext = settings.getUi5View().getExtension();
                        String rootName = virtualFile.getNameWithoutExtension().toLowerCase().replace(" ", "");
                        String indexHtml = index.createIndexCode(settings.getUi5Library(),
                                rootName,
                                ext);

                        String mainView = settings.getUi5View().autogenerateCode(settings.getUi5Library(),
                                rootName + ".Main");

                        File i18n = new File (tempProject,"i18n");
                        File css = new File (tempProject, "css");
                        File rootFolder = new File (tempProject, rootName);
                        i18n.mkdir();
                        css.mkdir();
                        rootFolder.mkdir();
                        String mainController = Controller.getAutogenerateCode(rootName, "Main");
                        File createFile = new File(tempProject.getAbsolutePath() + "\\" + "Index.html");
                        writeToFile(createFile, indexHtml);
                        createFile = new File(tempProject.getAbsolutePath() + "\\"+ rootName + "\\"  + "Main.view." + ext);
                        writeToFile(createFile, mainView);
                        createFile = new File(tempProject.getAbsolutePath() + "\\" + rootName + "\\" + "Main.controller." + ext);
                        writeToFile(createFile, mainController);
                        createFile = new File(tempProject.getAbsolutePath() + "\\css\\" + rootName + ".css");
                        writeToFile(createFile, "");
                        createFile = new File(tempProject.getAbsolutePath() + "\\i18n\\" + "i18n.properties");
                        writeToFile(createFile, "");


                        File[] files = tempProject.listFiles();
                        assert files != null && files.length != 0;
                        FileUtil.copyDir(tempProject, new File(virtualFile.getPath()));
                        deleteTemp(tempProject);
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
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

    private void forceRename(File source, File target) throws IOException
    {
        if (target.exists()){
            target.delete();
        }
        source.renameTo(target);
    }

    private void writeToFile(File file, String content) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.close();
    }

    private File createTemp() throws IOException {
        return FileUtil.createTempDirectory("ui5-generated-project", null, false);
    }

    private void deleteTemp(File tempProject) {
        FileUtil.delete(tempProject);
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

