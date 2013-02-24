package net.sf.seesea.rendering.chart.tools;

import java.rmi.activation.ActivateFailedException;

import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

public class Cache2ToolEntry extends ToolEntry {

	
	
	public Cache2ToolEntry(String label, String description) {
		super(label, description, null, null, CacheMarqueeSelectionTool2.class);
		if (label == null || label.length() == 0)
			setLabel(GEFMessages.MarqueeTool_Label);
		setUserModificationPermission(PERMISSION_NO_MODIFICATION);
	}
	
}
