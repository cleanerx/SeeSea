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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.sf.seesea.navigation.ui.figures.SingleLinedFigure;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

/**
 * This runnable updates the time figure every second
 */
public class TimeUpdater implements Runnable {

	private final SingleLinedFigure localTimeFigure;
	private final SingleLinedFigure utcFigure;
	private final SingleLinedFigure dateFigure;
	private final SimpleDateFormat dateFormat;
	private final SimpleDateFormat dateFormatGmt;

	/**
	 * @param localTimeFigure 
	 * @param utcFigure 
	 */
	public TimeUpdater(SingleLinedFigure localTimeFigure, SingleLinedFigure utcFigure, SingleLinedFigure dateFigure) {
		this.localTimeFigure = localTimeFigure;
		this.utcFigure = utcFigure;
		this.dateFigure = dateFigure;
		dateFormat = new SimpleDateFormat("yyyy-MMM-dd"); //$NON-NLS-1$
		dateFormatGmt = new SimpleDateFormat("HH:mm:ss z"); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true) {
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
					Date time = Calendar.getInstance().getTime();
					dateFormatGmt.setTimeZone(TimeZone.getDefault());
					localTimeFigure.setTime(dateFormatGmt.format(time));
					localTimeFigure.repaint();
					
					dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
					utcFigure.setTime(dateFormatGmt.format(time));
					utcFigure.repaint();
					
					String currentDate = dateFormat.format(time);
					if(!currentDate.equals(dateFigure.getTime())) {
						dateFigure.setTime(currentDate);
						dateFigure.repaint();
					}
					
					
				}
		});
		try {
			// sampling frequency to avoid antialiasing effects
			Thread.sleep(490);
		} catch (InterruptedException e) {
			Logger.getRootLogger().error(e);
		}
		}
	}

}
