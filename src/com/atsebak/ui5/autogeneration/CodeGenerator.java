package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;
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
        return Template.builder().name("templates/controller.js.ftl").classContext(this.getClass())
                .data(new HashMap<String, Object>() {{
                    put("root", root);
                }})
                .build()
                .toString();
    }

    /**
     * Index.html code
     *
     * @param ui5Library
     * @param rootModuleName
     * @param intialViewExt
     * @return
     */
    public String createIndexCode(@NotNull UI5Library ui5Library, @NotNull final String rootModuleName, @NotNull final String intialViewExt) {
        String templateLocation = "";
        switch (ui5Library) {
            case DESKTOP:
                templateLocation = "templates/desktop/index.html.ftl";
                break;
            case MOBILE:
                templateLocation = "templates/mobile/index.html.ftl";
                break;
        }

        return Template.builder().name(templateLocation).classContext(this.getClass())
                .data(new HashMap<String, Object>() {{
                    put("rootModuleName", rootModuleName);
                    put("intialViewExt", intialViewExt.toUpperCase());
                }})
                .build()
                .toString();
    }

    /**
     * HTML Code
     *
     * @param ui5Library
     * @param controllerPath
     * @return
     */
    public String createHtmlViewCode(@NotNull UI5Library ui5Library, @NotNull String controllerPath) {
        return getGeneratedCodeForView(ui5Library, controllerPath, "html");
    }

    /**
     * JS Code
     *
     * @param ui5Library
     * @param controllerPath
     * @return
     */
    public String createJavascriptViewCode(@NotNull UI5Library ui5Library, @NotNull String controllerPath){
        return getGeneratedCodeForView(ui5Library, controllerPath, "js");
    }

    /**
     * XML Code
     *
     * @param ui5Library
     * @param controllerPath
     * @return
     */
    public String createXmlViewCode(@NotNull UI5Library ui5Library, @NotNull String controllerPath) {
        return getGeneratedCodeForView(ui5Library, controllerPath, "xml");
    }

    /**
     * JSON Code
     *
     * @param ui5Library
     * @param controllerPath
     * @return
     */
    public String createJsonViewCode(@NotNull UI5Library ui5Library,@NotNull String controllerPath) {
        return getGeneratedCodeForView(ui5Library, controllerPath, "json");
    }

    /**
     * Generic method to autogenerate code based on file template extension
     *
     * @param ui5Library
     * @param controllerPath
     * @param ext
     * @return
     */
    private String getGeneratedCodeForView(@NotNull UI5Library ui5Library, @NotNull final String controllerPath,@NotNull String ext) {
        String templateLocation = "";
        switch (ui5Library) {
            case DESKTOP:
                templateLocation = "templates/desktop/view." + ext + ".ftl";
                break;
            case MOBILE:
                templateLocation = "templates/mobile/view." + ext + ".ftl";
                break;
        }

        return Template.builder().name(templateLocation).classContext(this.getClass())
                .data(new HashMap<String, Object>() {{
                    put("controllerPath", controllerPath);
                }})
                .build()
                .toString();
    }
}
