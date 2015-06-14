package com.atsebak.ui5.autogeneration;


import com.atsebak.ui5.AppType;
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
        String s = view.generateCode(AppType.DESKTOP, "com.atsebak");
        assertThat(s.contains("<core:View xmlns:core=\"sap.ui.core\" xmlns=\"sap.ui.commons\" controllerName='com.atsebak'>"), is(true));
    }

    @Test
    public void testAutogenerateCodeForMobile() {
        UI5View view = new XMLView();
        String s = view.generateCode(AppType.MOBILE, "com.atsebak");
        assertThat(s.contains("<core:View xmlns:core=\"sap.ui.core\" xmlns=\"sap.m\" controllerName='com.atsebak'>"), is(true));
        assertThat(s.contains("<Page title=\"Title\">"), is(true));
    }
}
