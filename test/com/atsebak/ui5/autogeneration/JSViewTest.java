package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JSViewTest {
    @Test
    public void testGetExtension() {
        UI5View view = new JSView();
        assertEquals(view.getExtension(), "js");
    }

    @Test
    public void testAutogenerateCodeForDesktop() {
        UI5View view = new JSView();
        String s = view.autogenerateCode(UI5Library.DESKTOP, "com.atsebak");
        assertThat(s.contains("sap.ui.jsview('com.atsebak'"), is(true));
        assertThat(s.contains("return 'com.atsebak'"), is(true));
    }

    @Test
    public void testAutogenerateCodeForMobile() {
        UI5View view = new JSView();
        String s = view.autogenerateCode(UI5Library.MOBILE, "com.atsebak");
        assertThat(s.contains("ap.ui.jsview('com.atsebak',"), is(true));
        assertThat(s.contains("return new sap.m.Page"), is(true));
    }
}
