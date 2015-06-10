package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;
import org.jetbrains.annotations.NotNull;

public class HTMLView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "html";
    }

    @NotNull
    @Override
    public String autogenerateCode(@NotNull UI5Library ui5Library, @NotNull String controllerPath) {
        return getCodeGenerator().createHtmlViewCode(ui5Library, controllerPath);
    }

}
