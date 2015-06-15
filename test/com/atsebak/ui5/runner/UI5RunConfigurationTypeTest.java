package com.atsebak.ui5.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class UI5RunConfigurationTypeTest {

    @Test
    public void testGetConfigurationFactories() throws Exception {
        UI5RunConfigurationType ui5RunConfigurationType = new UI5RunConfigurationType();
        ConfigurationFactory[] configurationFactories = ui5RunConfigurationType.getConfigurationFactories();
        assertArrayEquals(configurationFactories, new ConfigurationFactory[] {ui5RunConfigurationType.getFactory()});
    }
}