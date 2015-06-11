package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;

import java.util.HashMap;

public final class CodeGenerator {

    public String createControllerCode(final String root) {
        return Template.builder().name("templates/controller.js.ftl").classContext(this.getClass())
                .data(new HashMap<String, Object>() {{
                    put("root", root);
                }})
                .build()
                .toString();
    }

    public String createIndexCode(UI5Library ui5Library, final String rootModuleName, final String intialViewExt) {
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
                    put("intialViewExt", intialViewExt);
                }})
                .build()
                .toString();
    }

    public String createHtmlViewCode(UI5Library ui5Library, String controllerPath) {
        return getGeneratedCodeForView(ui5Library, controllerPath, "html");
    }

    public String createJavascriptViewCode(UI5Library ui5Library, String controllerPath){
        return getGeneratedCodeForView(ui5Library, controllerPath, "js");
    }

    public String createXmlViewCode(UI5Library ui5Library, String controllerPath) {
        return getGeneratedCodeForView(ui5Library, controllerPath, "xml");
    }

    public String createJsonViewCode(UI5Library ui5Library, String controllerPath) {
        return getGeneratedCodeForView(ui5Library, controllerPath, "json");
    }

    private String getGeneratedCodeForView(UI5Library ui5Library, final String controllerPath, String ext) {
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
