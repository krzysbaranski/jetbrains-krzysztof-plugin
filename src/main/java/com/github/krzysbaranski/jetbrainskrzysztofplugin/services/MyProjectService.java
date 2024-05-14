package com.github.krzysbaranski.jetbrainskrzysztofplugin.services;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.github.krzysbaranski.jetbrainskrzysztofplugin.MyBundle;

/**
 * com.intellij.diagnostic.PluginException: Light service class class com.github.krzysbaranski.jetbrainskrzysztofplugin.services.MyProjectService must be final [Plugin: com.github.krzysbaranski.jetbrainskrzysztofplugin]
 * 	at com.intellij.diagnostic.PluginProblemReporterImpl.createPluginExceptionByClass(PluginProblemReporterImpl.java:23)
 * 	at com.intellij.diagnostic.PluginException.createByClass(PluginException.java:83)
 * 	at com.intellij.serviceContainer.ComponentManagerImpl.doGetService(ComponentManagerImpl.kt:708)
 * 	at com.intellij.serviceContainer.ComponentManagerImpl.doGetService(ComponentManagerImpl.kt:699)
 * 	at com.intellij.serviceContainer.ComponentManagerImpl.getService(ComponentManagerImpl.kt:629)
 * 	at com.github.krzysbaranski.jetbrainskrzysztofplugin.toolwindow.MyToolWindowFactory$MyToolWindow.<init>(MyToolWindowFactory.java:43)
 * 	at com.github.krzysbaranski.jetbrainskrzysztofplugin.toolwindow.MyToolWindowFactory.createToolWindowContent(MyToolWindowFactory.java:28)
 * 	at com.intellij.openapi.wm.impl.ToolWindowImpl.createContentIfNeeded$intellij_platform_ide_impl(ToolWindowImpl.kt:543)
 * 	at com.intellij.openapi.wm.impl.ToolWindowImpl.scheduleContentInitializationIfNeeded$intellij_platform_ide_impl(ToolWindowImpl.kt:522)
 * 	at com.intellij.openapi.wm.impl.ToolWindowManagerImpl.doShowWindow(ToolWindowManagerImpl.kt:1009)
 * 	at com.intellij.openapi.wm.impl.ToolWindowManagerImpl.showToolWindowImpl(ToolWindowManagerImpl.kt:946)
 * 	at com.intellij.openapi.wm.impl.ToolWindowManagerImpl.activateToolWindow$intellij_platform_ide_impl(ToolWindowManagerImpl.kt:662)
 * 	at com.intellij.openapi.wm.impl.ToolWindowManagerImpl.activateToolWindow(ToolWindowManagerImpl.kt:622)
 * 	at com.jetbrains.rdserver.toolWindow.BackendServerToolWindowManager.activateToolWindow(BackendServerToolWindowManager.kt:188)
 * 	at com.intellij.ide.actions.ActivateToolWindowAction.actionPerformed(ActivateToolWindowAction.java:142)
 * 	at com.intellij.openapi.actionSystem.ex.ActionUtil.doPerformActionOrShowPopup(ActionUtil.java:327)
 * 	at com.intellij.ide.actions.GotoActionAction.lambda$performActionImpl$4(GotoActionAction.java:91)
 * 	at com.intellij.openapi.actionSystem.ex.ActionUtil.performDumbAwareWithCallbacks(ActionUtil.java:350)
 * 	at com.intellij.ide.actions.GotoActionAction.performActionImpl(GotoActionAction.java:90)
 * 	at com.intellij.ide.actions.GotoActionAction.lambda$performAction$2(GotoActionAction.java:70)
 * 	at com.intellij.openapi.application.TransactionGuardImpl.runWithWritingAllowed(TransactionGuardImpl.java:209)
 * 	at com.intellij.openapi.application.TransactionGuardImpl.access$100(TransactionGuardImpl.java:21)
 * 	at com.intellij.openapi.application.TransactionGuardImpl$1.run(TransactionGuardImpl.java:191)
 * 	at com.intellij.openapi.application.impl.ApplicationImpl.runIntendedWriteActionOnCurrentThread(ApplicationImpl.java:838)
 * 	at com.intellij.openapi.application.impl.ApplicationImpl$3.run(ApplicationImpl.java:454)
 * 	at com.intellij.openapi.application.impl.FlushQueue.doRun(FlushQueue.java:74)
 * 	at com.intellij.openapi.application.impl.FlushQueue.runNextEvent(FlushQueue.java:114)
 * 	at com.intellij.openapi.application.impl.FlushQueue.flushNow(FlushQueue.java:36)
 * 	at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:318)
 * 	at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:779)
 * 	at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:730)
 * 	at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:724)
 * 	at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
 * 	at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:86)
 * 	at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:749)
 * 	at com.intellij.ide.IdeEventQueue.defaultDispatchEvent(IdeEventQueue.java:909)
 * 	at com.intellij.ide.IdeEventQueue._dispatchEvent(IdeEventQueue.java:756)
 * 	at com.intellij.ide.IdeEventQueue.lambda$dispatchEvent$5(IdeEventQueue.java:437)
 * 	at com.intellij.openapi.progress.impl.CoreProgressManager.computePrioritized(CoreProgressManager.java:787)
 * 	at com.intellij.ide.IdeEventQueue.lambda$dispatchEvent$6(IdeEventQueue.java:436)
 * 	at com.intellij.openapi.application.TransactionGuardImpl.performActivity(TransactionGuardImpl.java:105)
 * 	at com.intellij.ide.IdeEventQueue.performActivity(IdeEventQueue.java:615)
 * 	at com.intellij.ide.IdeEventQueue.lambda$dispatchEvent$7(IdeEventQueue.java:434)
 * 	at com.intellij.openapi.application.impl.ApplicationImpl.runIntendedWriteActionOnCurrentThread(ApplicationImpl.java:838)
 * 	at com.intellij.ide.IdeEventQueue.dispatchEvent(IdeEventQueue.java:480)
 * 	at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:207)
 * 	at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:128)
 * 	at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:117)
 * 	at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:113)
 * 	at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:105)
 * 	at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:92)
 *
 *
 */
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
}
