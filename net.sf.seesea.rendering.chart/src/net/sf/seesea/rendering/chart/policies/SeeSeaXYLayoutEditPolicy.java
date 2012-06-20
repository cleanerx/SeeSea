/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.rendering.chart.policies;

import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.NamedPosition;
import net.sf.seesea.model.core.geo.osm.Area;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.commands.CreatePositionCommand;
import net.sf.seesea.rendering.chart.editpart.ITransactionalEditPart;
import net.sf.seesea.tileservice.projections.IMapProjection;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.swt.graphics.Point;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class SeeSeaXYLayoutEditPolicy extends XYLayoutEditPolicy {

	public SeeSeaXYLayoutEditPolicy(XYLayout layout) {
		super();
		setXyLayout(layout);
	}
	
	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		return null;
	}

	@Override
	protected Command getCreateCommand(final CreateRequest request) {
//		BundleContext bundleContext = SeeSeaActivator.getDefault().getBundle().getBundleContext();
//		ServiceReference serviceReference = bundleContext.getServiceReference(EditingDomain.class.getName());
//		EditingDomain editingDomain =  (EditingDomain) bundleContext.getService(serviceReference);
//		editingDomain.getCommandStack().execute(command)
		
		TransactionalEditingDomain editingDomain = ((ITransactionalEditPart)getHost()).getEditingDomain();
		
		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference serviceReference = bundleContext.getServiceReference(IMapProjection.class.getName());
		IMapProjection mapProjection = (IMapProjection) bundleContext.getService(serviceReference);

		if(getHost().getModel() instanceof Area) {
			Area area = (Area) getHost().getModel();
			NamedPosition namedPosition = (NamedPosition) request.getNewObject();
			org.eclipse.draw2d.geometry.Point location = request.getLocation().getCopy();
			getHostFigure().translateToRelative(location);
			GeoPosition position = mapProjection.backproject(new Point(location.x, location.y), (1<<area.getZoomLevel()) * 256);
			return new ICommandProxy(new CreatePositionCommand(editingDomain, area, namedPosition, position));
		}
		
		return UnexecutableCommand.INSTANCE;
		
//		CompositeTransactionalCommand cc = new CompositeTransactionalCommand(editingDomain, DiagramUIMessages.AddCommand_Label);

		// this should be the view model, not the model itself
		
	}

		
		
//		return new ICommandProxy(new AbstractCommand("Create Position") {
//
//			@Override
//			public boolean canExecute() {
//				return true;
//			}
//
//			@Override
//			protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor,
//					IAdaptable info) throws ExecutionException {
//				BundleContext bundleContext = SeeSeaActivator.getDefault().getBundle().getBundleContext();
//				ServiceReference serviceReference = bundleContext.getServiceReference(IMapProjection.class.getName());
//				IMapProjection mapProjection = (IMapProjection) bundleContext.getService(serviceReference);
//				if(getHost().getModel() instanceof POIContainer) {
//					Area area = (Area) getHost().getModel();
//					NamedPosition namedPosition = (NamedPosition) request.getNewObject();
//					org.eclipse.draw2d.geometry.Point location = request.getLocation().getCopy();
//					getHostFigure().translateToRelative(location);
//					GeoPosition position = mapProjection.backproject(new Point(location.x, location.y), (1<<area.getZoomLevel()) * 256);
//					namedPosition.setLatitude(position.getLatitude());
//					namedPosition.setLongitude(position.getLongitude());
//					area.getPoiContainer().getPois().add(namedPosition);
//					return CommandResult.newOKCommandResult(namedPosition);
//				}
//				return CommandResult.newCancelledCommandResult();
//			}
//
//			@Override
//			protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
//					IAdaptable info) throws ExecutionException {
//				return null;
//			}
//
//			@Override
//			protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
//					IAdaptable info) throws ExecutionException {
//				return null;
//			}
//		});
//		
//		
//	}

}
