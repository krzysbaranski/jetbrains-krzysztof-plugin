package com.github.krzysbaranski.jetbrainskrzysztofplugin.toolwindow;

import com.github.krzysbaranski.jetbrainskrzysztofplugin.MyBundle;
import com.github.krzysbaranski.jetbrainskrzysztofplugin.services.MyProjectService;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.RoundedLineBorder;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyToolWindowFactory implements ToolWindowFactory {

    private static final Logger LOG = Logger.getInstance(MyToolWindowFactory.class);

    public MyToolWindowFactory() {
        LOG.warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        MyToolWindow myToolWindow = new MyToolWindow(project, toolWindow);
        Content content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public boolean shouldBeAvailable(Project project) {
        return true;
    }

    private static class MyToolWindow {

        private final MyProjectService service;

        private final Project project;
        private final JLabel fileNameLabel;
        private final JTextArea generatedCodeTextArea;
        private final JTextArea inputField;
        private Document document = null;


        public MyToolWindow(Project project, ToolWindow toolWindow) {
            this.service = toolWindow.getProject().getService(MyProjectService.class);
            this.project = project;

            this.fileNameLabel = new JLabel();
            this.fileNameLabel.setText("No file");
            this.generatedCodeTextArea = new JTextArea();
            this.generatedCodeTextArea.setEditable(false);
            this.generatedCodeTextArea.setText("None");

            this.generatedCodeTextArea.setBorder(RoundedLineBorder.createBlackLineBorder());
            this.inputField = new JTextArea();
            this.inputField.setColumns(20);
            this.inputField.setText("Fix bugs in this code if there are any");
            this.inputField.setEnabled(true);
            this.inputField.setEditable(true);
            this.inputField.setBorder(RoundedLineBorder.createBlackLineBorder());
            project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
                @Override
                public void selectionChanged(FileEditorManagerEvent event) {
                    updateContent();
                }
            });
            updateContent();
        }


        private void updateContent() {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            var f = fileEditorManager.getSelectedFiles();
            if (f.length == 0) {
                return;
            }

            var selectedFile = f[0];
            LOG.warn("updateContent " + selectedFile.getName());
            this.document = FileDocumentManager.getInstance().getDocument(selectedFile);
            fileNameLabel.setText(selectedFile.getName());
        }


        public JComponent getContent() {
            return getContent2();
        }


        private JBPanel<?> getContent2() {
            JBPanel<JBPanel<?>> panel = new JBPanel<>();
            JBLabel label = new JBLabel(MyBundle.message("descriptionLabel"));

            panel.add(label);

            var button = new JButton(MyBundle.message("generateCodeLabel"));

            var el2 = new JBScrollPane(this.inputField);
            panel.add(el2);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LOG.info("button");
                    if (inputField.getText().length() > 10 && document != null) {

                        var data = service.generateCode(inputField.getText(), document.getText());
                        LOG.info("button code");
                        generatedCodeTextArea.setText(MyBundle.message("generatedCodeLabel", data));
                    } else {
                        generatedCodeTextArea.setText("no code");
                        LOG.info("button no code");

                    }
                }
            });


            panel.add(button);
            var el1 = new JBScrollPane(this.generatedCodeTextArea);
            panel.add(el1);


            updateContent();
            return panel;
        }

    }
}