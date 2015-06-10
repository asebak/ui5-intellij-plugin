package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;
import org.jetbrains.annotations.NotNull;

public class JSView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "js";
    }

    @NotNull
    @Override
    public String autogenerateCode(@NotNull UI5Library ui5Library, @NotNull String controllerPath) {
        return getCodeGenerator().createJavascriptViewCode(ui5Library, controllerPath);
    }

}
