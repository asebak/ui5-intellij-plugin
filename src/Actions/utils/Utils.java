package Actions.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

public class Utils {
    public static File fileFor(AnActionEvent actionEvent) {
        VirtualFile virtualFile = (VirtualFile) actionEvent.getDataContext()
                .getData(DataConstants.VIRTUAL_FILE);
        return virtualFile == null ? null : new File(virtualFile.getPath());
    }

    /**
     * Returns the project associated with the given action event.
     */
    public static Project projectFor(AnActionEvent actionEvent) {
        return (Project) actionEvent.getDataContext().getData(DataConstants.PROJECT);
    }

    /**
     * Returns the editor associated with the given action event.
     */
    public static Editor editorFor(AnActionEvent actionEvent) {
        return (Editor) actionEvent.getDataContext().getData(DataConstants.EDITOR);
    }
}
