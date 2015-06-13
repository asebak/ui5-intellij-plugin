package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;
import org.junit.Test;


public class IndexTest {

    @Test
    public void testCreateIndexCodeForDesktop() throws Exception {
        Index index = new Index();
        String js = index.createIndexCode(AppType.DESKTOP, "com.atsebak", "js");

    }
}
