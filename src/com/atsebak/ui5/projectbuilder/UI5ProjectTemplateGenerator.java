package com.atsebak.ui5.projectbuilder;

import com.atsebak.ui5.AppType;
import com.atsebak.ui5.FileType;
import com.atsebak.ui5.autogeneration.Controller;
import com.atsebak.ui5.autogeneration.Index;
import com.atsebak.ui5.autogeneration.UI5View;
import com.atsebak.ui5.locale.UI5Bundle;
import com.atsebak.ui5.util.*;
import com.atsebak.ui5.util.Writer;
import com.intellij.ide.util.projectWizard.WebProjectTemplate;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.*;

/**
 * This is for the sub template of the project.
 * Once the finished but is click generateProject method is called and runs a separate process
 */
public class UI5ProjectTemplateGenerator extends WebProjectTemplate<UI5ProjectTemplateGenerator.UI5ProjectSettings> {
    private static final String UI5_RESOURCE_PATH = "/ui5/";
    private static final String RESOURCE_PATH = UI5_RESOURCE_PATH + "resources.zip";
    @Nls
    @NotNull
    @Override
    public String getName() {
        return UI5Bundle.getString("app");
    }

    @Override
    public String getDescription() {
        return UI5Bundle.getString("app.description");
    }

    @Override
    public void generateProject(@NotNull final Project project, @NotNull final VirtualFile virtualFile, @NotNull final UI5ProjectSettings settings, @NotNull Module module) {
        if (settings.getLibrary() == null) {
            settings.setLibrary(AppType.DESKTOP);
        }
        final Index index = new Index();
        final UI5View view = settings.getView();
        final AppType type = settings.getLibrary();
        final String ext = view.getExtension();


        ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
            @SneakyThrows(Exception.class)
            @Override
            public void run() {
                final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
                progressIndicator.setText(UI5Bundle.getString("app.creating.long"));

                /*Code generation*/

                String rootName = virtualFile.getNameWithoutExtension().toLowerCase().replace(" ", "");
                String indexHtml = index.createIndexCode(type, rootName, ext);

                String mainView = view.generateCode(type, rootName + ".Main");
                String mainController = new Controller().getAutogenerateCode(rootName, "Main");

                /*File Creation*/

                File tempProject = createTemp();


                createPaths(tempProject, new String[]{"i18n", "css", "util", rootName, "resources"});

                if (ext.toUpperCase().equals(FileType.XML.name())) {
                    UI5FileBuilder.createDirectoryFromName(tempProject, "xsd");
                }

                writeToFile(tempProject, "", "index.html", indexHtml);
                writeToFile(tempProject, rootName, "Main.view." + ext, mainView);
                writeToFile(tempProject, rootName, "Main.controller.js", mainController);
                writeToFile(tempProject, "css", rootName + ".css", "");
                writeToFile(tempProject, "i18n", "i18n.properties", "");

                /*Unzip UI5 Resources to temp project*/

                Zip.unzip(getClass().getResourceAsStream(RESOURCE_PATH), tempProject.getAbsolutePath() + File.separator + "resources");

                /*Add XSD Schemas if XML based project*/

                addXsdSchemas(tempProject);

                transferTempFilesToProject(tempProject, virtualFile);

                ProjectHelper.addRunConfiguration(project);
            }

            private void addXsdSchemas(@NotNull File tempProject) throws IOException {
                if (ext.toUpperCase().equals(FileType.XML.name())) {
                    writeToFile(tempProject, "xsd", "sap.ui.core.xsd", getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.core.xsd"));
                    writeToFile(tempProject, "xsd", "sap.ui.commons.xsd", getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.commons.xsd"));
                    writeToFile(tempProject, "xsd", "sap.ui.layout.xsd", getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.layout.xsd"));
                    writeToFile(tempProject, "xsd", "sap.ui.table.xsd", getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.table.xsd"));
                    writeToFile(tempProject, "xsd", "sap.ui.ux3.xsd", getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.table.xsd"));
                    writeToFile(tempProject, "xsd", "sap.m.xsd", getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.m.xsd"));
                }
            }

            private void createPaths(@NotNull File tempDir, @NotNull String[] paths) {
                for (int i = 0; i < paths.length; i++) {
                    UI5FileBuilder.createDirectoryFromName(tempDir, paths[i]);
                }
            }

            private void writeToFile(@NotNull File tempProject, @Nullable String path, @NotNull String fileName, @Nullable String content) throws IOException {
                File file = new File(tempProject.getAbsolutePath() + File.separator + path + File.separator + fileName);
                Writer.writeToFile(file, content);
            }

            private void writeToFile(@NotNull File tempProject, @Nullable String path, @NotNull String fileName, @Nullable InputStream inputStream) throws IOException {
                BufferedReader bufRead = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = bufRead.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                writeToFile(tempProject, path, fileName, builder.toString());
            }

            private void transferTempFilesToProject(@NotNull File tempProject, @NotNull VirtualFile virtualFile) throws IOException {
                File[] files = tempProject.listFiles();
                assert files != null && files.length != 0;
                FileUtil.copyDir(tempProject, new File(virtualFile.getPath()));
                deleteTemp(tempProject);
            }

            private File createTemp() throws IOException {
                return FileUtil.createTempDirectory("ui5-generated-project", null, false);
            }

            private void deleteTemp(@NotNull File tempProject) {
                FileUtil.delete(tempProject);
            }

        }, UI5Bundle.getString("project.creating"), false, project);
    }


    @Override
    public Icon getIcon() {
        return UI5Icons.getIcon();
    }

    @NotNull
    @Override
    public ProjectPeer createPeer() {
        return new ProjectPeer();
    }


    @Data
    final static class UI5ProjectSettings {
        private UI5View view;
        private AppType library;
    }

}

