package com.atsebak.ui5.util;

import com.atsebak.ui5.FileType;
import com.atsebak.ui5.autogeneration.*;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class UI5FileBuilderTest {
    @Test
    public void testDepedencyImplementation() {
        UI5View viewImplementation = UI5FileBuilder.getViewImplementation(FileType.JS);
        assertThat(viewImplementation, instanceOf(JSView.class));

        viewImplementation = UI5FileBuilder.getViewImplementation(FileType.HTML);
        assertThat(viewImplementation, instanceOf(HTMLView.class));

        viewImplementation = UI5FileBuilder.getViewImplementation(FileType.XML);
        assertThat(viewImplementation, instanceOf(XMLView.class));

        viewImplementation = UI5FileBuilder.getViewImplementation(FileType.JSON);
        assertThat(viewImplementation, instanceOf(JSONView.class));

        viewImplementation = UI5FileBuilder.getViewImplementation(FileType.PROPERTIES);
        assertEquals(viewImplementation, null);
    }
}
