package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;
import org.jetbrains.annotations.NotNull;

public class HTMLView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "html";
    }

    @NotNull
    @Override
    public String autogenerateCode(@NotNull AppType ui5Library, @NotNull String controllerPath) {
        return getCodeGenerator().createViewCode(ui5Library, controllerPath, getExtension());
    }

}
