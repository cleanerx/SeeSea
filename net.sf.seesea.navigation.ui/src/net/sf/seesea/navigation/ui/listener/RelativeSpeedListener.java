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

import java.text.DecimalFormat;

import org.eclipse.swt.widgets.Display;

import net.sf.seesea.model.core.physx.RelativeSpeed;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.navigation.ui.figures.DescriptiveInstrumentFigure;
import net.sf.seesea.services.navigation.listener.ISpeedListener;

/**
 * 
 */
public class RelativeSpeedListener extends InvalidatingFigureListener<RelativeSpeed> implements ISpeedListener {
	
	private final DescriptiveInstrumentFigure speedOverGround;

	private final DecimalFormat speedDecimalFormat;

	private final SpeedType speedType;

	/**
	 * @param courseOverGround 
	 * @param relativeSpeed 
	 * @param fdw 
	 * @param mgk 
	 */
	public RelativeSpeedListener(DescriptiveInstrumentFigure relativeSpeed, SpeedType speedType) {
		super(relativeSpeed);
		speedOverGround = relativeSpeed;
		this.speedType = speedType;
		speedDecimalFormat = new DecimalFormat("##0.0"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.services.navigation.listener.IDataListener#notify(java.lang.Object)
	 */
	@Override
	public void notify(final RelativeSpeed sensorData, String source) {
//		if(isSensorUpdateFast()) {
//			System.out.println("fast");
//			return;
//		}
//		System.out.println(speedDecimalFormat.format(sensorData.getValue().getSpeed()));
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				String speedUnit = ""; //$NON-NLS-1$
				if(speedType.equals(sensorData.getKey())) {
					if(sensorData.getValue() != null) {
						if(SpeedUnit.N.equals(sensorData.getValue().getSpeedUnit())) {
							speedUnit = "kn"; //$NON-NLS-1$
						} else if(SpeedUnit.K.equals(sensorData.getValue().getSpeedUnit())) {
							speedUnit = "km/h"; //$NON-NLS-1$
						} else if(SpeedUnit.M.equals(sensorData.getValue().getSpeedUnit())) {
							speedUnit = "m/h"; //$NON-NLS-1$
						} 
						speedOverGround.setVisible(true);
						speedOverGround.getParent().setVisible(true);
						speedOverGround.setValue(speedDecimalFormat.format(sensorData.getValue().getSpeed()) + speedUnit);
						speedOverGround.repaint();
					}
				}
			}
		});
	}

}
