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

import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.ShipMovementVector;
import net.sf.seesea.model.core.physx.Speed;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.model.core.physx.SpeedUnit;
import net.sf.seesea.navigation.ui.figures.DescriptiveInstrumentFigure;
import net.sf.seesea.services.navigation.listener.ISpeedVectorListener;

import org.eclipse.swt.widgets.Display;

/**
 * 
 */
public class ShipMovementListener extends InvalidatingFigureListener<ShipMovementVector> implements ISpeedVectorListener {
	
	private final DescriptiveInstrumentFigure _courseOverGround;

	private final DecimalFormat degreeDecimalFormat;

	private final DescriptiveInstrumentFigure speedOverGround;

	private final DecimalFormat speedDecimalFormat;

	private final DescriptiveInstrumentFigure mgk;

	private final DescriptiveInstrumentFigure fdw;

	/**
	 * @param courseOverGround 
	 * @param sog 
	 * @param fdw 
	 * @param mgk 
	 */
	public ShipMovementListener(DescriptiveInstrumentFigure courseOverGround, DescriptiveInstrumentFigure sog, DescriptiveInstrumentFigure mgk, DescriptiveInstrumentFigure fdw) {
		super(courseOverGround, sog, fdw, mgk);
		_courseOverGround = courseOverGround;
		speedOverGround = sog;
		this.fdw = fdw;
		this.mgk = mgk;
		degreeDecimalFormat = new DecimalFormat("000"); //$NON-NLS-1$
		speedDecimalFormat = new DecimalFormat("##0.0"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.services.navigation.listener.IDataListener#notify(java.lang.Object)
	 */
	@Override
	public void notify(final ShipMovementVector sensorData) {
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				Double cog = sensorData.getHeadings().get(HeadingType.COG);
				if(cog != null) {
					_courseOverGround.setValue(degreeDecimalFormat.format(cog) + "\u00B0"); //$NON-NLS-1$
					_courseOverGround.repaint();
				}
				Double mgkValue = sensorData.getHeadings().get(HeadingType.MAGNETIC);
				if(mgk != null && mgkValue != null) {
					mgk.setValue(degreeDecimalFormat.format(mgkValue) + "\u00B0"); //$NON-NLS-1$
					mgk.repaint();
				}
				String speedUnit = ""; //$NON-NLS-1$
				if(!sensorData.getSpeeds().isEmpty()) {
					Speed speed = sensorData.getSpeeds().get(SpeedType.COG);
					if(speed != null) {
						if(SpeedUnit.N.equals(speed.getSpeedUnit())) {
							speedUnit = "kn"; //$NON-NLS-1$
						} else if(SpeedUnit.K.equals(speed.getSpeedUnit())) {
							speedUnit = "km/h"; //$NON-NLS-1$
						} else if(SpeedUnit.M.equals(speed.getSpeedUnit())) {
							speedUnit = "m/h"; //$NON-NLS-1$
						} 
						speedOverGround.setValue(speedDecimalFormat.format(speed.getSpeed()) + speedUnit);
						speedOverGround.repaint();
					}
					speed = sensorData.getSpeeds().get(SpeedType.SPEEDTHOUGHWATER);
					if(speed != null) {
						if(SpeedUnit.N.equals(speed.getSpeedUnit())) {
							speedUnit = "kn"; //$NON-NLS-1$
						} else if(SpeedUnit.K.equals(speed.getSpeedUnit())) {
							speedUnit = "km/h"; //$NON-NLS-1$
						} else if(SpeedUnit.M.equals(speed.getSpeedUnit())) {
							speedUnit = "m/h"; //$NON-NLS-1$
						} 
						fdw.setValue(speedDecimalFormat.format(speed.getSpeed()) + speedUnit);
						fdw.repaint();
					}
					
				}
			}
		});
	}

}
