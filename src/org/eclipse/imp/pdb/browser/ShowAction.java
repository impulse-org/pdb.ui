/*******************************************************************************
* Copyright (c) 2007 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

*******************************************************************************/

package org.eclipse.imp.pdb.browser;

import java.util.List;

import org.eclipse.imp.pdb.analysis.AnalysisException;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.db.FactBase;
import org.eclipse.imp.pdb.facts.db.IFactKey;
import org.eclipse.imp.pdb.ui.graph.Editor;
import org.eclipse.imp.pdb.ui.graph.GraphBuilder;
import org.eclipse.imp.pdb.ui.graph.ValueEditorInput;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ShowAction implements IViewActionDelegate {
    private FactBrowserView factBrowser;
	private final FactBase factBase = FactBase.getInstance();

    public void init(IViewPart view) {
        factBrowser = (FactBrowserView) view;
    }

    public void run(IAction action) {
        List<IFactKey> keys = factBrowser.getCurrentSelection();
        
        try {
        	for (IFactKey key : keys) {
        		IValue value = factBase.getFact(key);
        		openEditor(new ValueEditorInput(value));
        	}
        } catch (AnalysisException e) {
        	// TODO deal with this!
        } catch (PartInitException e) {
        	// TODO deal with this!
		}
    }

    private void openEditor(IEditorInput input) throws PartInitException {
    	IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();

		if (win != null) {
			IWorkbenchPage page = win.getActivePage();
			
			if (page != null) {
				page.openEditor(input, Editor.EditorId);
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
    	boolean canShow = true;
    	List<IFactKey> keys = factBrowser.getCurrentSelection();
        
    	for (IFactKey key : keys) {
    		canShow &= GraphBuilder.canShow(key.getType());
    	}
    	
    	action.setEnabled(canShow);
    }
}
