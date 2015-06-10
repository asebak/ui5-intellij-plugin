package com.atsebak.ui5.actions.ui;

import com.atsebak.ui5.locale.UI5Bundle;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;


@Builder
public class UI5ApiSearchDialog {
    private Project project;

    /**
     * Show the search dialog
     * @param searchTerm
     */
    public void show(final String searchTerm) {

        final JTextField searchTextField = new JTextField(searchTerm);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(UI5Bundle.getString("api.searchterm")), BorderLayout.WEST);
        panel.add(searchTextField, BorderLayout.CENTER);

        final DialogBuilder dialogBuilder = new DialogBuilder(project);
        dialogBuilder.setTitle(UI5Bundle.getString("api.search"));
        dialogBuilder.setCenterPanel(panel);
        dialogBuilder.addAction(getSearchAction(dialogBuilder, searchTextField.getText()));
        dialogBuilder.addCancelAction();
        dialogBuilder.showModal(true);
    }

    /**
     * Builds the action
     * @param dialogBuilder
     * @param term
     * @return
     */
    private AbstractAction getSearchAction(@NotNull final DialogBuilder dialogBuilder, @Nullable final String term) {
        return new AbstractAction(UI5Bundle.getString("search")) {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Desktop.getDesktop().browse(new URL("https://sapui5.netweaver.ondemand.com/sdk/search.html?q=" + term).toURI());
                } catch (Exception e) {
                    Messages.showErrorDialog(project, UI5Bundle.getString("search.error.msg"), UI5Bundle.getString("search.error.title"));
                }
                dialogBuilder.getDialogWrapper().close(DialogWrapper.OK_EXIT_CODE);
            }
        };
    }
}
