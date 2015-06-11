package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;
import org.junit.Test;


public class IndexTest {

    @Test
    public void testCreateIndexCodeForDesktop() throws Exception {
        Index index = new Index();
        String js = index.createIndexCode(UI5Library.DESKTOP, "com.atsebak", "js");

    }
}
