package com.atsebak.ui5.autogeneration;


import com.atsebak.ui5.config.UI5Library;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class XMLViewTest {
    @Test
    public void testGetExtension() {
        UI5View view = new XMLView();
        assertEquals(view.getExtension(), "xml");
    }

    @Test
    public void testAutogenerateCodeForDesktop() {
        UI5View view = new XMLView();
        String s = view.autogenerateCode(UI5Library.DESKTOP, "com.atsebak");
        assertThat(s.contains("<core:View xmlns:core=\"sap.ui.core\" xmlns:mvc=\"sap.ui.core.mvc\" xmlns=\"sap.ui.commons\" controllerName='com.atsebak' xmlns:html=\"http://www.w3.org/1999/xhtml\">"), is(true));
    }

    @Test
    public void testAutogenerateCodeForMobile() {
        UI5View view = new XMLView();
        String s = view.autogenerateCode(UI5Library.MOBILE, "com.atsebak");
        assertThat(s.contains("<core:View xmlns:core=\"sap.ui.core\" xmlns:mvc=\"sap.ui.core.mvc\" xmlns=\"sap.m\" controllerName='com.atsebak' xmlns:html=\"http://www.w3.org/1999/xhtml\">"), is(true));
        assertThat(s.contains("<Page title=\"Title\">"), is(true));
    }
}
