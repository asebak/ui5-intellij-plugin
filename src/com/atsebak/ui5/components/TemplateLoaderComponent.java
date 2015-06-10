package com.atsebak.ui5.components;

import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;


public class TemplateLoaderComponent implements ApplicationComponent {
    @Override
    public void initComponent() {
//        FileTemplateManager.getInstance()
//        FileTemplate template = FileTemplateManager.getInstance().getTemplate(STORY_FILE_TYPE.getName());
//        if (template == null) {
//            template = FileTemplateManager.getInstance()
//                    .addTemplate(STORY_FILE_TYPE.getName(), STORY_FILE_TYPE.getDefaultExtension());
//            try {
//                template.setText(
//                        loadTextAndClose(new InputStreamReader(getClass().getResourceAsStream("/fileTemplates/JBehave Story.story.ft"))));
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void disposeComponent() {
        // do nothing
    }

    @NotNull
    @Override
    public String getComponentName() {
        return this.getClass().getName();
    }
}