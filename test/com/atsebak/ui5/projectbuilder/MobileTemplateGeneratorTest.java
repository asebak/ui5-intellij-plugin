package com.atsebak.ui5.projectbuilder;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertTrue;

public class MobileTemplateGeneratorTest {

    @Test
    public void testGetName() {
        MobileTemplateGenerator templateGenerator = Whitebox.newInstance(MobileTemplateGenerator.class);
        assertTrue(templateGenerator.getName().contains("Mobile"));
    }
}