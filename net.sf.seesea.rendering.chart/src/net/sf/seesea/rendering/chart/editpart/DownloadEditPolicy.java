package net.sf.seesea.rendering.chart.editpart;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

public class DownloadEditPolicy extends AbstractEditPolicy {

	public Command getCommand(Request request) {
		if (REQ_SELECTION.equals(request.getType())) {
			return getHost().getCommand(request);
		}
		return null;
	}
	
}
