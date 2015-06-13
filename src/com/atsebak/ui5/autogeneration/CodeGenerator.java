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
     * HTML Code
     *
     * @param appType
     * @param controllerPath
     * @return
     */
    public String createHtmlViewCode(@NotNull AppType appType, @NotNull String controllerPath) {
        return getGeneratedCodeForView(appType, controllerPath, "html");
    }

    /**
     * JS Code
     *
     * @param appType
     * @param controllerPath
     * @return
     */
    public String createJavascriptViewCode(@NotNull AppType appType, @NotNull String controllerPath){
        return getGeneratedCodeForView(appType, controllerPath, "js");
    }

    /**
     * XML Code
     *
     * @param appType
     * @param controllerPath
     * @return
     */
    public String createXmlViewCode(@NotNull AppType appType, @NotNull String controllerPath) {
        return getGeneratedCodeForView(appType, controllerPath, "xml");
    }

    /**
     * JSON Code
     *
     * @param appType
     * @param controllerPath
     * @return
     */
    public String createJsonViewCode(@NotNull AppType appType,@NotNull String controllerPath) {
        return getGeneratedCodeForView(appType, controllerPath, "json");
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
