package com.github.krzysbaranski.jetbrainskrzysztofplugin.services;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.github.krzysbaranski.jetbrainskrzysztofplugin.MyBundle;

@Service(Service.Level.PROJECT)
public final class MyProjectService {
    private final Project project;

    public MyProjectService(Project project) {
        this.project = project;
        Logger.getInstance(this.getClass()).info(MyBundle.message("projectService", project.getName()));
        Logger.getInstance(this.getClass()).warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }

    public int getRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    public String generateCode(String input, String context) {

        return "input:" + input + " context: " + context;
    }
}
