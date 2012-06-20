/*******************************************************************************
 * Copyright (c) 2003, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.sf.seesea.rendering.chart.rulers;

import net.sf.seesea.rendering.chart.editpart.RulerEditPart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.GraphicalViewer;

public class RulerEditPartFactory implements EditPartFactory {

	protected GraphicalViewer diagramViewer;
	
	public RulerEditPartFactory(GraphicalViewer primaryViewer) {
		diagramViewer = primaryViewer;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart child = null;
		if(model instanceof RulerModel) {
			child = new RulerEditPart(model);
		}
	
		return child;
	}

}
