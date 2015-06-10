package com.atsebak.ui5.autogeneration;

/**
 * Created by asebak on 9/28/2014.
 */
public class XMLView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "xml";
    }

    @Override
    public String autogenerateCode(UI5Library ui5Library, String controllerPath) {
        return codeGenerator.createXmlViewCode(ui5Library, controllerPath);
    }

}
