package com.atsebak.ui5.projectbuilder;

import com.atsebak.ui5.autogeneration.Controller;
import com.atsebak.ui5.autogeneration.Index;
import com.atsebak.ui5.autogeneration.UI5Library;
import com.atsebak.ui5.autogeneration.UI5View;
import com.atsebak.ui5.util.UI5Icons;
import com.atsebak.ui5.util.Writer;
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
import java.io.File;
import java.io.IOException;

/**
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



                        com.atsebak.ui5.autogeneration.Index index = new Index();
                        String ext = settings.getUi5View().getExtension();
                        String rootName = virtualFile.getNameWithoutExtension().toLowerCase().replace(" ", "");
                        String indexHtml = index.createIndexCode(settings.getUi5Library(),
                                rootName,
                                ext);

                        String mainView = settings.getUi5View().autogenerateCode(settings.getUi5Library(),
                                rootName + ".Main");

                        File i18n = new File (tempProject,"i18n");
                        File css = new File (tempProject, "css");
                        File util = new File(tempProject, "util");
                        File rootFolder = new File (tempProject, rootName);
                        i18n.mkdir();
                        css.mkdir();
                        rootFolder.mkdir();
                        util.mkdir();
                        String mainController = Controller.getAutogenerateCode(rootName, "Main");
                        File createFile = new File(tempProject.getAbsolutePath() + File.separator + "Index.html");
                        Writer.writeToFile(createFile, indexHtml);
                        createFile = new File(tempProject.getAbsolutePath() + File.separator + rootName + File.separator  + "Main.view." + ext);
                        Writer.writeToFile(createFile, mainView);
                        createFile = new File(tempProject.getAbsolutePath() + File.separator + rootName + File.separator + "Main.controller.js");
                        Writer.writeToFile(createFile, mainController);
                        createFile = new File(tempProject.getAbsolutePath() + File.separator + "css" + File.separator + rootName + ".css");
                        Writer.writeToFile(createFile, "");
                        createFile = new File(tempProject.getAbsolutePath() + File.separator + "i18n" + File.separator + "i18n.properties");
                        Writer.writeToFile(createFile, "");


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

