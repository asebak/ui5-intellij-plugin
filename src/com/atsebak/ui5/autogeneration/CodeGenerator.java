package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public final class CodeGenerator {

    /**
     * Controller.js code
     *
     * @param root
     * @return
     */
    public String createControllerCode(@NotNull final String root) {
        return createTemplate("templates/controller.js.ftl", new HashMap<String, Object>() {{
            put("root", root);
        }});
    }

    /**
     * Index.html code
     *
     * @param appType
     * @param rootModuleName
     * @param intialViewExt
     * @return
     */
    public String createIndexCode(@NotNull AppType appType, @NotNull final String rootModuleName, @NotNull final String intialViewExt) {
        return createTemplate("templates/" + appType.name().toLowerCase() + "/index.html.ftl", new HashMap<String, Object>() {{
            put("rootModuleName", rootModuleName);
            put("intialViewExt", intialViewExt.toUpperCase());
        }});
    }

    /**
     * Creates the view code
     * @param appType
     * @param controllerPath
     * @param ext
     * @return
     */
    public String createViewCode(@NotNull AppType appType, @NotNull String controllerPath, @NotNull String ext){
        return getGeneratedCodeForView(appType, controllerPath, ext);
    }

    /**
     * Generic method to autogenerate code based on file template extension
     *
     * @param appType
     * @param controllerPath
     * @param ext
     * @return
     */
    private String getGeneratedCodeForView(@NotNull AppType appType, @NotNull final String controllerPath,@NotNull String ext) {
        return createTemplate("templates/" + appType.name().toLowerCase() + "/view." + ext + ".ftl", new HashMap<String, Object>() {{
            put("controllerPath", controllerPath);
        }});
    }

    /**
     * Template Builder class
     * @param templateName
     * @param replacements
     * @return
     */
    private String createTemplate(String templateName, HashMap<String, Object> replacements) {
        return Template.builder().name(templateName).classContext(this.getClass())
                .data(replacements).build()
                .toString();
    }
}
