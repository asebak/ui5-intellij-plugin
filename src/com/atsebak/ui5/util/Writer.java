package com.atsebak.ui5.util;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Writer {

    /**
     * Private constructor
     */
    private Writer() {

    }
    /**
     * Writes content to a specific file
     * @param file
     * @param content
     * @throws IOException
     */
    public static void writeToFile(@NotNull File file,@NotNull String content) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.close();
    }
}
