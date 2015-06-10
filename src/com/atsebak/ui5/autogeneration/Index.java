package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;
import org.jetbrains.annotations.NotNull;

public class Index extends View {
    @NotNull
    public String createIndexCode(@NotNull UI5Library ui5Library, @NotNull String rootModuleName, @NotNull String initialViewExt) {
        return getCodeGenerator().createIndexCode(ui5Library, rootModuleName, initialViewExt);
    }
}
