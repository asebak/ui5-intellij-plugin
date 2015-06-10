package com.atsebak.ui5.autogeneration;

import org.jetbrains.annotations.NotNull;

public class Controller {

    @NotNull
    public static String getAutogenerateCode(@NotNull String modulePath, @NotNull String controllerName) {
        return new CodeGenerator().createControllerCode(modulePath, controllerName);
    }
}
