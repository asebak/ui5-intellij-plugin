package com.atsebak.ui5.runner;

import com.intellij.execution.configurations.RunProfile;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;


public class UI5RunnerTest {

    @Test
    public void testGetRunnerId() throws Exception {
        UI5Runner runner = new UI5Runner();
        assertThat(runner.getRunnerId(), containsString("UI5"));
    }

    @Test
    public void testProfileCanRun() throws Exception {
        RunProfile profile = Mockito.mock(UI5RunConfiguration.class);
        UI5Runner runner = Whitebox.newInstance(UI5Runner.class);
        assertTrue(runner.canRun("Run", profile));
    }

    @Test
    public void testProfileCannotDebug() {
        RunProfile profile = Mockito.mock(UI5RunConfiguration.class);
        UI5Runner runner = Whitebox.newInstance(UI5Runner.class);
        assertFalse(runner.canRun("Debug", profile));
    }

    @Test
    public void testRunWrongProfile() {
        RunProfile wrongProfile = Mockito.mock(RunProfile.class);
        UI5Runner runner = Whitebox.newInstance(UI5Runner.class);
        assertFalse(runner.canRun("Run", wrongProfile));
    }
}