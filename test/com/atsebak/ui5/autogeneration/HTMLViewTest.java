package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class HTMLViewTest {

    @Test
    public void testGetExtension() {
        UI5View htmlView = new HTMLView();
        assertEquals(htmlView.getExtension(), "html");
    }

    @Test
    public void testAutogenerateCodeForDesktop() {
        UI5View htmlView = new HTMLView();
        String s = htmlView.generateCode(AppType.DESKTOP, "com.atsebak");
        assertThat(s.contains("<template data-controller-name='com.atsebak'"), is(true));
    }

    @Test
    public void testAutogenerateCodeForMobile() {
        UI5View htmlView = new HTMLView();
        String s = htmlView.generateCode(AppType.MOBILE, "com.atsebak");
        assertThat(s.contains("<template data-controller-name='com.atsebak'"), is(true));
        assertThat(s.contains("<div data-sap-ui-type='sap.m.Page' data-title='Title'>"), is(true));
    }
}
