package Runner;

import Util.UI5Icons;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by asebak on 9/27/2014.
 */
public class UI5ConfigurationType implements ConfigurationType {
    public UI5ConfigurationFactory ui5ConfigurationFactory;

    public UI5ConfigurationType() {
        ui5ConfigurationFactory = new UI5ConfigurationFactory(this);
    }

    @Override
    public String getDisplayName() {
        return "OpenUI5";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "OpenUI5 App";
    }

    @Override
    public Icon getIcon() {
        return UI5Icons.getIcon();
    }

    @NotNull
    @Override
    public String getId() {
        return "UI5";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        {
            return new UI5ConfigurationFactory[]{ui5ConfigurationFactory};
        }
    }

    public class UI5ConfigurationFactory extends ConfigurationFactory {

        public UI5ConfigurationFactory(ConfigurationType type) {
            super(type);
        }

        @Override
        public RunConfiguration createTemplateConfiguration(Project project) {
            return new UI5RunConfiguration(project, ui5ConfigurationFactory, "OpenUI5");
        }
    }
}
