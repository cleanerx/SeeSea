package net.sf.seesea.rendering.chart;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.events.GestureEvent;

public interface IViewerGestureListener {

	public static final String KEY = IViewerGestureListener.class.getName();

	public void gesturePerformed(GestureEvent gestureEvent, EditPartViewer viewer);
}