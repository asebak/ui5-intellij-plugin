package com.atsebak.ui5.projectbuilder;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertTrue;

public class DesktopTemplateGeneratorTest {

    @Test
    public void testGetName() {
        DesktopTemplateGenerator templateGenerator = Whitebox.newInstance(DesktopTemplateGenerator.class);
        assertTrue(templateGenerator.getName().contains("Desktop"));
    }
}