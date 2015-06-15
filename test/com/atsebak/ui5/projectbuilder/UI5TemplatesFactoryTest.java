package com.atsebak.ui5.projectbuilder;

import com.intellij.platform.ProjectTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertArrayEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UI5TemplatesFactory.class, MobileTemplateGenerator.class, DesktopTemplateGenerator.class})
public class UI5TemplatesFactoryTest {

    @Test
    public void testCreateTemplates() throws Exception {
        DesktopTemplateGenerator desktopTemplateGenerator = mock(DesktopTemplateGenerator.class);
        whenNew(DesktopTemplateGenerator.class).withNoArguments().thenReturn(desktopTemplateGenerator);

        MobileTemplateGenerator mobileTemplateGenerator = mock(MobileTemplateGenerator.class);
        whenNew(MobileTemplateGenerator.class).withNoArguments().thenReturn(mobileTemplateGenerator);

        UI5TemplatesFactory templatesFactory = Whitebox.newInstance(UI5TemplatesFactory.class);

        ProjectTemplate[] templates = templatesFactory.createTemplates(null, null);
        assertArrayEquals(templates, new ProjectTemplate[]{desktopTemplateGenerator, mobileTemplateGenerator});
    }
}