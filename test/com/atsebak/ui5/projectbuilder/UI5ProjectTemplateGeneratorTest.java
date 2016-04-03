package com.atsebak.ui5.projectbuilder;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;


public class UI5ProjectTemplateGeneratorTest {

    private static final String UI5_RESOURCE_PATH = "/ui5/";

    @Test
    public void resourcesZipExistsTest() {
        assertTrue(getClass().getResource("/resources.zip") == null);
    }

    @Test
    public void resourcesZipDoesNotExistsTest() {
        assertTrue(getClass().getResource(UI5_RESOURCE_PATH + "resources.zip") != null);
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