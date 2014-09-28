package Config;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by asebak on 9/27/2014.
 */
public final class UI5Settings implements PersistentStateComponent<UI5Settings.State> {

    @Nullable
    @Override
    public State getState() {
        return null;
    }

    @Override
    public void loadState(State state) {

    }

    public static class State {

    }


    public static UI5Settings getInstance() {
        return ServiceManager.getService(UI5Settings.class);
    }

    private State myState = new State();
}
