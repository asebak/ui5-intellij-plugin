package com.atsebak.ui5.autogeneration;

/**
 * Created by asebak on 9/28/2014.
 */
public class HTMLView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "html";
    }

    @Override
    public String autogenerateCode(UI5Library ui5Library, String controllerPath) {
        return codeGenerator.createHtmlViewCode(ui5Library, controllerPath);
    }

}
