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

import java.text.NumberFormat;

import org.eclipse.swt.widgets.Display;

import net.sf.seesea.model.core.weather.WindMeasurement;
import net.sf.seesea.navigation.ui.figures.DescriptiveInstrumentFigure;
import net.sf.seesea.services.navigation.listener.IWindListener;

public class WindSpeedFigureListener extends InvalidatingFigureListener<WindMeasurement> implements IWindListener {


	private final DescriptiveInstrumentFigure windSpeed;
	private final DescriptiveInstrumentFigure windDirection;
	private NumberFormat speedFormat;
	private NumberFormat directionFormat;

	public WindSpeedFigureListener(DescriptiveInstrumentFigure windSpeed,
			DescriptiveInstrumentFigure windDirection) {
		super(windSpeed, windDirection);
				this.windSpeed = windSpeed;
				this.windDirection = windDirection;
				speedFormat = NumberFormat.getNumberInstance();
				speedFormat.setMinimumIntegerDigits(1);
				speedFormat.setMaximumFractionDigits(1);
				
				directionFormat = NumberFormat.getNumberInstance();
				directionFormat.setMinimumIntegerDigits(3);
				directionFormat.setMaximumFractionDigits(0);
	}

	@Override
	public void notify(final WindMeasurement sensorData, String source) {
		if(isSensorUpdateFast()) {
			return;
		}
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				windSpeed.setVisible(true);
				windDirection.setVisible(true);
				windSpeed.getParent().setVisible(true);
				windSpeed.setValue(speedFormat.format(sensorData.getSpeed()) + "kn");
				windSpeed.repaint();
				windDirection.setValue(directionFormat.format(sensorData.getAngle()) + "\u00B0");
				windDirection.repaint();
			}
		});
	}


}
