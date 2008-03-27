package org.eclipse.imp.pdb.browser;

import org.eclipse.imp.pdb.facts.db.FactBase;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class ClearFactBaseAction implements IViewActionDelegate {

    public void init(IViewPart view) {
    }

    public void run(IAction action) {
        FactBase.getInstance().clear();
    }

    public void selectionChanged(IAction action, ISelection selection) {
    }
}
