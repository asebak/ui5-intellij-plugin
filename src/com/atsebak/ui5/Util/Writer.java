package com.atsebak.ui5.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by asebak on 10/13/2014.
 */
public class Writer {
    public static void writeToFile(File file, String content) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.close();
    }
}
