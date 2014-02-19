package net.sf.seesea.rendering.chart.handler;

import net.sf.seesea.rendering.chart.editor.ChartEditor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class DeleteHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);
		ISelection selection = activeEditor.getSite().getSelectionProvider().getSelection();
//		new
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			for (Object o : structuredSelection.toList()) {
				if(o instanceof EditPart) {
					EditPart editPart = (EditPart) o;
					Object model = editPart.getModel();
					EcoreUtil.delete((EObject) model);
					editPart.getParent().refresh();
				}
				
			}
		}
//		activeEditor.
//		ChartEditor chartEditor = (ChartEditor) activeEditor;
//		chartEditor.getGraphicalViewer().
		return null;
	}

}
