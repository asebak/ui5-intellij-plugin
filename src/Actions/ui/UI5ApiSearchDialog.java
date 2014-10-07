package Actions.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

/**
 * Created by asebak on 10/6/2014.
 */
public class UI5ApiSearchDialog {
    private Project project;

    public UI5ApiSearchDialog(Project project) {
        this.project = project;
    }

    public void show(final String searchTerm) {
        final DialogBuilder dialogBuilder = new DialogBuilder(project);
        dialogBuilder.setTitle("Expedia Confluence Search");
        JLabel myLabel = new JLabel("Search Term: ");
        JTextField searchTextField = new JTextField(searchTerm);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(myLabel, BorderLayout.WEST);
        panel.add(searchTextField, BorderLayout.CENTER);
        dialogBuilder.setCenterPanel(panel);
        AbstractAction queryAction = new AbstractAction("Search") {
            public void actionPerformed(ActionEvent e) {
                try {
                    //TODO: Fix url not going to correct path
                    Desktop.getDesktop().browse(new URL("https://sapui5.netweaver.ondemand.com/sdk/search.html?q=" + searchTerm).toURI());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                dialogBuilder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        };
        dialogBuilder.addAction(queryAction);
        dialogBuilder.addCancelAction();
        dialogBuilder.showModal(true);
    }
}
