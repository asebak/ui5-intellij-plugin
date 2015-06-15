package com.atsebak.ui5.runner;

import com.intellij.ide.browsers.WebBrowser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class UI5RunnerParametersTest {

    @Test
    public void testClone() throws Exception {
        WebBrowser webBrowser = mock(WebBrowser.class);
        UI5RunnerParameters ui5RunnerParameters = new UI5RunnerParameters();
        ui5RunnerParameters.setWebBrowser(webBrowser);
        UI5RunnerParameters clone = ui5RunnerParameters.clone();
        assertEquals(ui5RunnerParameters, clone);
    }
}