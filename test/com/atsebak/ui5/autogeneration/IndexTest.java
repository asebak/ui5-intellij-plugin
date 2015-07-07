package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class IndexTest {

    @Test
    public void testCreateIndexCodeForDesktop() {
        Index index = new Index();
        String js = index.createIndexCode(AppType.DESKTOP, "atsebak", "js");
        assertTrue(js.contains("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/atsebak.css\">"));
        assertTrue(js.contains("<script src=\"resources/sap-ui-core.js\" id=\"sap-ui-bootstrap\" data-sap-ui-language=\"en\" data-sap-ui-libs=\"sap.ui.commons,sap.ui.table,sap.ui.ux3\" data-sap-ui-theme=\"sap_goldreflection\">"));
        assertTrue(js.contains("sap.ui.localResources(\"atsebak\");"));
        assertFalse(js.contains("sap.m.App"));
    }

    @Test
    public void testIndexJSView() {
        Index index = new Index();
        String indexCode = index.createIndexCode(AppType.DESKTOP, "atsebak", "js");
        assertTrue(indexCode.contains("type: sap.ui.core.mvc.ViewType.JS"));

        indexCode = index.createIndexCode(AppType.MOBILE, "atsebak", "js");
        assertTrue(indexCode.contains("type: sap.ui.core.mvc.ViewType.JS"));
    }

    @Test
    public void testIndexXMLView() {
        Index index = new Index();
        String indexCode = index.createIndexCode(AppType.DESKTOP, "atsebak", "xml");
        assertTrue(indexCode.contains("type: sap.ui.core.mvc.ViewType.XML"));

        indexCode = index.createIndexCode(AppType.MOBILE, "atsebak", "xml");
        assertTrue(indexCode.contains("type: sap.ui.core.mvc.ViewType.XML"));
    }

    @Test
    public void testIndexJSONView() {
        Index index = new Index();
        String indexCode = index.createIndexCode(AppType.DESKTOP, "atsebak", "json");
        assertTrue(indexCode.contains("type: sap.ui.core.mvc.ViewType.JSON"));

        indexCode = index.createIndexCode(AppType.MOBILE, "atsebak", "json");
        assertTrue(indexCode.contains("type: sap.ui.core.mvc.ViewType.JSON"));
    }

    @Test
    public void testIndexHTMLView() {
        Index index = new Index();
        String indexCode = index.createIndexCode(AppType.DESKTOP, "atsebak", "html");
        assertTrue(indexCode.contains("type: sap.ui.core.mvc.ViewType.HTML"));

        indexCode = index.createIndexCode(AppType.MOBILE, "atsebak", "html");
        assertTrue(indexCode.contains("type: sap.ui.core.mvc.ViewType.HTML"));
    }

    @Test
    public void testCreateIndexCodeForMobile() {
        Index index = new Index();
        String js = index.createIndexCode(AppType.MOBILE, "atsebak", "js");
        assertTrue(js.contains("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/atsebak.css\">"));
        assertTrue(js.contains(" <script src=\"resources/sap-ui-core.js\" id=\"sap-ui-bootstrap\" data-sap-ui-language=\"en\" data-sap-ui-libs=\"sap.m\" data-sap-ui-theme=\"sap_bluecrystal\">"));
        assertTrue(js.contains("sap.ui.localResources(\"atsebak\");"));
        assertTrue(js.contains("sap.m.App"));
    }

}
