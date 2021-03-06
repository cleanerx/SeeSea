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

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.swt.widgets.Display;

import net.sf.seesea.navigation.ui.figures.IInvalidatableFigure;
import net.sf.seesea.services.navigation.listener.IDataProviderNotification;

public abstract class InvalidatingFigureListener<SensorDataType> implements
		IDataProviderNotification {

	private Set<IInvalidatableFigure> invalidatableFigures;
	protected long lastTimeMillis;
	private long fastTimeoutMilliseconds;

	public InvalidatingFigureListener(IInvalidatableFigure... figures) {
		invalidatableFigures = new LinkedHashSet<IInvalidatableFigure>();
		for (IInvalidatableFigure invalidatableFigure : figures) {
			invalidatableFigures.add(invalidatableFigure);
		}
		lastTimeMillis = System.currentTimeMillis();
		fastTimeoutMilliseconds = 250L;
	}
	
	public InvalidatingFigureListener() {
		invalidatableFigures = new LinkedHashSet<IInvalidatableFigure>();
	}
	
	@Override
	public void providerEnabled(String providerID) {
		Display.getDefault().asyncExec(new Runnable() {
			
			public void run() {
				for (IInvalidatableFigure invalidatableFigure : getInvalidatableFigure()) {
					invalidatableFigure.setValidData(true);
					invalidatableFigure.repaint();
				}
			}
		});
	}

	@Override
	public void providerDisabled(String providerID) {
		Display.getDefault().asyncExec(new Runnable() {
			
			public void run() {
				for (IInvalidatableFigure invalidatableFigure : getInvalidatableFigure()) {
					invalidatableFigure.setValidData(false);
					invalidatableFigure.repaint();
				}
			}
		});
	}
	
	protected Set<IInvalidatableFigure> getInvalidatableFigure() {
		return invalidatableFigures;
	}

	protected boolean isSensorUpdateFast() {
		long currentTimeMillis = System.currentTimeMillis();
		if(currentTimeMillis - lastTimeMillis < fastTimeoutMilliseconds) {
			return true;
		}
		lastTimeMillis = currentTimeMillis;
		return false;
	}
	
	public long getFastTimeoutMilliseconds() {
		return fastTimeoutMilliseconds;
	}

	public void setFastTimeoutMilliseconds(long fastTimeoutMilliseconds) {
		this.fastTimeoutMilliseconds = fastTimeoutMilliseconds;
	}

	public void addFigure(IInvalidatableFigure figure) {
		invalidatableFigures.add(figure);
	}
	
	
}
