package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;
import org.jetbrains.annotations.NotNull;


public class XMLView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "xml";
    }

    @Override
    public String autogenerateCode(@NotNull UI5Library ui5Library, @NotNull String controllerPath) {
        return getCodeGenerator().createXmlViewCode(ui5Library, controllerPath);
    }

}
