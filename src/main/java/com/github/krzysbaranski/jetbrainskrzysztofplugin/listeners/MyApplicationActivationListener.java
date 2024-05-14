package com.github.krzysbaranski.jetbrainskrzysztofplugin.listeners;

import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.wm.IdeFrame;

public class MyApplicationActivationListener implements ApplicationActivationListener {

    @Override
    public void applicationActivated(IdeFrame ideFrame) {
        Logger.getInstance(this.getClass()).warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }
}