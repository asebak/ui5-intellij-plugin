package com.atsebak.ui5.Autogeneration;

/**
 * Created by asebak on 9/28/2014.
 */
public class JSONView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "json";
    }

    @Override
    public String autogenerateCode(UI5Library ui5Library, String controllerPath) {
        return codeGenerator.createJsonViewCode(ui5Library, controllerPath);
    }

}
