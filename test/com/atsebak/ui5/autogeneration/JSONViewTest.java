package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JSONViewTest {

    @Test
    public void testGetExtension() {
        UI5View view = new JSONView();
        assertEquals(view.getExtension(), "json");
    }

    @Test
    public void testAutogenerateCodeForDesktop() {
        UI5View view = new JSONView();
        String s = view.generateCode(AppType.DESKTOP, "com.atsebak");
        assertThat(s.contains("\"Type\": \"sap.ui.core.mvc.JSONView\","), is(true));
        assertThat(s.contains("\"controllerName\": \"com.atsebak\","), is(true));
    }

    @Test
    public void testAutogenerateCodeForMobile() {
        UI5View view = new JSONView();
        String s = view.generateCode(AppType.MOBILE, "com.atsebak");
        assertThat(s.contains("\"Type\": \"sap.ui.core.mvc.JSONView\","), is(true));
        assertThat(s.contains("\"controllerName\": \"com.atsebak\","), is(true));
        assertThat(s.contains("\"Type\": \"sap.m.Page\","), is(true));
    }
}
