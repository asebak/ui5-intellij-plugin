package Runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by asebak on 9/27/2014.
 */
public class UI5ConfigurationType implements ConfigurationType {
    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getConfigurationTypeDescription() {
        return null;
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @NotNull
    @Override
    public String getId() {
        return null;
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[0];
    }
}
