package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;
import org.jetbrains.annotations.NotNull;


public class XMLView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "xml";
    }

    @Override
    public String autogenerateCode(@NotNull AppType ui5Library, @NotNull String controllerPath) {
        return getCodeGenerator().createViewCode(ui5Library, controllerPath, getExtension());
    }

}
