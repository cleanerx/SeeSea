/**
 * 
 Copyright (c) 2010, Jens Kübler All rights reserved.
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
package net.sf.seesea.navigation.ui.listener;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.seesea.model.core.physx.SatelliteInfo;
import net.sf.seesea.model.core.physx.SatellitesVisible;
import net.sf.seesea.navigation.ui.figures.BarFigure;
import net.sf.seesea.services.navigation.listener.ISatelliteInfoListener;

import org.eclipse.swt.widgets.Display;

public class SateliteQualityFigureListener extends InvalidatingFigureListener<SatellitesVisible> implements ISatelliteInfoListener {

	private final BarFigure _barFigure;

	/**
	 * @param doubleLinedInstrumentFigure 
	 * 
	 */
	public SateliteQualityFigureListener(BarFigure barFigure) {
		super(barFigure);
		_barFigure = barFigure; 
	}
	
	/* (non-Javadoc)
	 * @see net.sf.seesea.services.navigation.listener.IDataListener#notify(java.lang.Object)
	 */
	@Override
	public void notify(final SatellitesVisible sensorData, String source) {
		if(isSensorUpdateFast()) {
			return;
		}

		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				Map<Integer,Double> signalStrength = new TreeMap<Integer,Double>();
				List<SatelliteInfo> satelliteInfo = sensorData.getSatelliteInfo();
				for (SatelliteInfo satelliteInfo2 : satelliteInfo) {
					int id = satelliteInfo2.getId();
					signalStrength.put(id, (double) satelliteInfo2.getSignalStrength());
				}
//				for(int i = 0; i < 14 ; i++) {
//					signalStrength.put(i, (double) 99.0);
//				}
				_barFigure.setBarHeights(signalStrength);
				_barFigure.repaint();
			}
		});
	}

	
}
