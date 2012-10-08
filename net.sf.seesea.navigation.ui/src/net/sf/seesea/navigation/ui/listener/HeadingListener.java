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

import net.sf.seesea.model.core.physx.Heading;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.navigation.ui.figures.DescriptiveInstrumentFigure;
import net.sf.seesea.services.navigation.listener.IHeadingListener;

import org.eclipse.swt.widgets.Display;

/**
 * 
 */
public class HeadingListener extends InvalidatingFigureListener<Heading> implements IHeadingListener {
	
	private final DescriptiveInstrumentFigure _courseOverGround;

	private final DecimalFormat degreeDecimalFormat;

	private final DescriptiveInstrumentFigure mgk;

	/**
	 * @param courseOverGround 
	 * @param sog 
	 * @param fdw 
	 * @param mgk 
	 */
	public HeadingListener(DescriptiveInstrumentFigure courseOverGround, DescriptiveInstrumentFigure mgk) {
		super(courseOverGround, mgk);
		_courseOverGround = courseOverGround;
		this.mgk = mgk;
		degreeDecimalFormat = new DecimalFormat("000"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see net.sf.seesea.services.navigation.listener.IDataListener#notify(java.lang.Object)
	 */
	@Override
	public void notify(final Heading sensorData, String source) {
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				if(HeadingType.COG.equals(sensorData.getHeadingType())) {
					Double cog = sensorData.getDegrees();
					if(cog != null) {
						_courseOverGround.setValue(degreeDecimalFormat.format(cog) + "\u00B0"); //$NON-NLS-1$
						_courseOverGround.repaint();
					}
				} else if(HeadingType.MAGNETIC.equals(sensorData.getHeadingType())) {
					Double mgkValue = sensorData.getDegrees();
					if(mgk != null && mgkValue != null) {
						mgk.setValue(degreeDecimalFormat.format(mgkValue) + "\u00B0"); //$NON-NLS-1$
						mgk.repaint();
					}
				}
//				String speedUnit = ""; //$NON-NLS-1$
//				if(!sensorData.getSpeeds().isEmpty()) {
//					Speed speed = sensorData.getSpeeds().get(SpeedType.COG);
//					if(speed != null) {
//						if(SpeedUnit.N.equals(speed.getSpeedUnit())) {
//							speedUnit = "kn"; //$NON-NLS-1$
//						} else if(SpeedUnit.K.equals(speed.getSpeedUnit())) {
//							speedUnit = "km/h"; //$NON-NLS-1$
//						} else if(SpeedUnit.M.equals(speed.getSpeedUnit())) {
//							speedUnit = "m/h"; //$NON-NLS-1$
//						} 
//						speedOverGround.setValue(speedDecimalFormat.format(speed.getSpeed()) + speedUnit);
//						speedOverGround.repaint();
//					}
//					speed = sensorData.getSpeeds().get(SpeedType.SPEEDTHOUGHWATER);
//					if(speed != null) {
//						if(SpeedUnit.N.equals(speed.getSpeedUnit())) {
//							speedUnit = "kn"; //$NON-NLS-1$
//						} else if(SpeedUnit.K.equals(speed.getSpeedUnit())) {
//							speedUnit = "km/h"; //$NON-NLS-1$
//						} else if(SpeedUnit.M.equals(speed.getSpeedUnit())) {
//							speedUnit = "m/h"; //$NON-NLS-1$
//						} 
//						fdw.setValue(speedDecimalFormat.format(speed.getSpeed()) + speedUnit);
//						fdw.repaint();
//					}
//					
//				}
			}
		});
	}

}
