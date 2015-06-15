package com.atsebak.ui5.runner;

import com.intellij.execution.Executor;
import com.intellij.execution.configuration.EmptyRunProfileState;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class UI5RunConfigurationTest {

    @Test
    public void testGetState() throws Exception {
        UI5RunConfiguration ui5RunConfiguration = Whitebox.newInstance(UI5RunConfiguration.class);
        Executor executor = mock(Executor.class);
        ExecutionEnvironment executionEnvironment = mock(ExecutionEnvironment.class);
        RunProfileState state = ui5RunConfiguration.getState(executor, executionEnvironment);
        assertThat(state, instanceOf(EmptyRunProfileState.class));
    }
}