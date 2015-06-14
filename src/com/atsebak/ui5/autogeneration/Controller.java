package com.atsebak.ui5.autogeneration;

import org.jetbrains.annotations.NotNull;

public class Controller extends UI5View {
    /**
     * generates code for the controller
     *
     * @param modulePath
     * @param controllerName
     * @return
     */
    @NotNull
    public String getAutogenerateCode(@NotNull String modulePath, @NotNull String controllerName) {
        return getCodeGenerator().createControllerCode(modulePath + "." + controllerName);
    }
}
