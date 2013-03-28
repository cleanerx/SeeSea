/**
 * 
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
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
package net.sf.seesea.rendering.chart.listeners;

import java.util.List;

import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.Route;
import net.sf.seesea.model.core.geo.Track;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.core.provider.EditingDomainProvider;
import net.sf.seesea.model.util.IModel;
import net.sf.seesea.rendering.chart.commands.AddPositionToAreaCommand;
import net.sf.seesea.rendering.chart.commands.CreateTrackCommand;
import net.sf.seesea.services.navigation.listener.IPositionListener;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class TrackRecorder implements IPositionListener {
	
	private IEditingDomainProvider domainProvider;

	private IModel _model;
	
	private Track _currentTrack;

	public void activate() {
		
	}

	public void deactivate() {
		
	}

	
	public synchronized void bindModel(IModel model) {
		this._model = model;
	}

	public synchronized void unbindModel(IModel model) {
		this._model = null;
	}

	public synchronized void bindEditingDomainProvider(IEditingDomainProvider domainProvider) {
		this.domainProvider = domainProvider;
	}

	public synchronized void unbindEditingDomainProvider(IEditingDomainProvider domainProvider) {
		this.domainProvider = null;
	}

	public void providerEnabled(String providerID) {
		// start new track
		try {
			World world = _model.loadModel();
			CreateTrackCommand createRouteCommand = new CreateTrackCommand((TransactionalEditingDomain) domainProvider.getEditingDomain(), "Create Route", world);
			IOperationHistory operationHistory = OperationHistoryFactory.getOperationHistory();
			IStatus status = operationHistory.execute(createRouteCommand, new NullProgressMonitor(), null);
			List<Track> routes = world.getTracksContainer().getTracks();
			_currentTrack = routes.get(routes.size() - 1);
			// _currentRoute
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	public void notify(MeasuredPosition3D sensorData, String source) {
		World world = _model.loadModel();
		List<Track> routes = world.getTracksContainer().getTracks();
		Track currentTrack = routes.get(routes.size() - 1);

//		AddPositionToAreaCommand createPositionCommand = new AddPositionToAreaCommand((TransactionalEditingDomain) domainProvider.getEditingDomain(), currentTrack, sensorData);
//		IOperationHistory operationHistory = OperationHistoryFactory.getOperationHistory();
//		try {
//			IStatus status = operationHistory.execute(createPositionCommand, new NullProgressMonitor(), null);
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//		currentRoute.getWaypoints().add(e)
	}

	public void providerDisabled(String providerID) {
		_currentTrack = null;
		
	}
	
	
}
