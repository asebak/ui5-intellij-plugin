package com.atsebak.ui5.projectbuilder;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;


public class UI5ProjectTemplateGeneratorTest {

    private static final String UI5_RESOURCE_PATH = "/ui5/";

    @Test
    public void resourcesZipExistsTest() {
        URL resource = getClass().getResource("/resources.zip");
        assertTrue(resource != null);
    }

    @Test
    public void resourcesZipDoesNotExistsTest() {
        URL resource = getClass().getResource("/ui5/resources.zip");
        assertTrue(resource == null);
    }

    @Test
    public void xsdFilesExistsTest() {
        assertTrue(getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.core.xsd") != null);
        assertTrue(getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.commons.xsd") != null);
        assertTrue(getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.layout.xsd") != null);
        assertTrue(getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.table.xsd") != null);
        assertTrue(getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.ui.table.xsd") != null);
        assertTrue(getClass().getResourceAsStream(UI5_RESOURCE_PATH + "sap.m.xsd") != null);
    }
}