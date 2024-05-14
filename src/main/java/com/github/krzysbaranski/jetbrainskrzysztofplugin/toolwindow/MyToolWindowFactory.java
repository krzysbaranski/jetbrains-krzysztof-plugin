package com.github.krzysbaranski.jetbrainskrzysztofplugin.toolwindow;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.github.krzysbaranski.jetbrainskrzysztofplugin.MyBundle;
import com.github.krzysbaranski.jetbrainskrzysztofplugin.services.MyProjectService;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyToolWindowFactory implements ToolWindowFactory {

    private static final Logger LOG = Logger.getInstance(MyToolWindowFactory.class);

    public MyToolWindowFactory() {
        LOG.warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        MyToolWindow myToolWindow = new MyToolWindow(toolWindow);
        Content content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public boolean shouldBeAvailable(Project project) {
        return true;
    }

    private static class MyToolWindow {

        private final MyProjectService service;

        public MyToolWindow(ToolWindow toolWindow) {
            this.service = toolWindow.getProject().getService(MyProjectService.class);
        }

        public JBPanel<?> getContent() {
            JBPanel<JBPanel<?>> panel = new JBPanel<>();
            JBLabel label = new JBLabel(MyBundle.message("randomLabel", "?"));

            panel.add(label);

            var button = new JButton(MyBundle.message("shuffle"));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    label.setText(MyBundle.message("randomLabel", service.getRandomNumber()));
                }
            });

            panel.add(button);

            return panel;
        }
    }
}
