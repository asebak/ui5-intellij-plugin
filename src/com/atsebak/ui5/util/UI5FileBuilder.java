package com.atsebak.ui5.util;


import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class UI5FileBuilder {

    public static void createDirectoryFromName(@NotNull File folder, @NotNull String name) {
        File file = new File(folder, name);
        file.mkdir();
    }
}
