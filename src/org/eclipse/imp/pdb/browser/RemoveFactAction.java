package org.eclipse.imp.pdb.browser;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class RemoveFactAction implements IViewActionDelegate {
    private FactBrowserView factBrowser;

    public void init(IViewPart view) {
        factBrowser = (FactBrowserView) view;
    }

    public void run(IAction action) {
        factBrowser.removeCurrentSelection();
    }

    public void selectionChanged(IAction action, ISelection selection) {
    }
}
