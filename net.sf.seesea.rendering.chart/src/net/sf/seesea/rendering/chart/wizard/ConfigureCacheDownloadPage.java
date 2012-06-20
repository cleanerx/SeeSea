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
package net.sf.seesea.rendering.chart.wizard;

import java.text.NumberFormat;
import java.text.ParseException;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Text;

public class ConfigureCacheDownloadPage extends WizardPage implements
		IWizardPage {
	private Text latutideStartText;
	private Text latutideEndText;
	private Text longitudeStartText;
	private Text longitudeEndText;
	private NumberFormat numberInstance;
	private double latitudeStart;
	private double longitudeStart;
	private double latitudeEnd;
	private double longitudeEnd;

	public ConfigureCacheDownloadPage() {
		super("FIMXE");
		numberInstance = NumberFormat.getNumberInstance();
		numberInstance.setMinimumFractionDigits(1);
	}

	public void createControl(Composite parent) {
		setTitle("Cache Tiles");
		setDescription("Please select the range for which to cache tiles");
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Latitude Start");
		
		latutideStartText = new Text(container, SWT.BORDER);
		latutideStartText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		latutideStartText.setText(numberInstance.format(latitudeStart));
		
		Label lblLatitudeEnd = new Label(container, SWT.NONE);
		lblLatitudeEnd.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLatitudeEnd.setText("Latitude End");
		
		latutideEndText = new Text(container, SWT.BORDER);
		latutideEndText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		latutideEndText.setText(numberInstance.format(latitudeEnd));

		Label lblLongitudeStar = new Label(container, SWT.NONE);
		lblLongitudeStar.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLongitudeStar.setText("Longitude Start");
		
		longitudeStartText = new Text(container, SWT.BORDER);
		longitudeStartText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		longitudeStartText.setText(numberInstance.format(longitudeStart));

		Label lblLongitudeEnd = new Label(container, SWT.NONE);
		lblLongitudeEnd.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLongitudeEnd.setText("Longitude End");
		
		longitudeEndText = new Text(container, SWT.BORDER);
		longitudeEndText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		longitudeEndText.setText(numberInstance.format(longitudeEnd));
		
		Label zoomLevelsLabel = new Label(container, SWT.NONE);
		zoomLevelsLabel.setText("Zoom Levels");
		
		Scale zoomScale = new Scale(container, SWT.NONE);
		zoomScale.setMaximum(18);
		// FIXME: Tile provider dependent
		
		GridData gd_zoomScale = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_zoomScale.widthHint = 477;
		zoomScale.setLayoutData(gd_zoomScale);
		
		Label sizeDescriptionLabel = new Label(container, SWT.NONE);
		sizeDescriptionLabel.setText("Estimated Size");
		
		Label sizeLabel = new Label(container, SWT.NONE);
	}

	public void setLatitudeStart(double latitudeStart) {
		this.latitudeStart = latitudeStart;
	}

	public void setLongitudeStart(double longitudeStart) {
		this.longitudeStart = longitudeStart;
	}

	public void setLatitudeEnd(double latitudeEnd) {
		this.latitudeEnd = latitudeEnd;
	}

	public void setLongitudeEnd(double longitudeEnd) {
		this.longitudeEnd = longitudeEnd;
	}

	public Double getLatutideStartText() {
		return latitudeStart;
	}

	public Double getLatutideEndText() {
		return latitudeEnd;
	}

	public Double getLongitudeStartText() {
		return longitudeStart;
	}

	public Double getLongitudeEndText() {
		return longitudeEnd;
	}

	public int getMinZoom() {
		return 0;
	}

	public int getMaxZoom() {
		return 18;
	}
	
	@Override
	public boolean isPageComplete() {
		try {
			latitudeStart = (Double) numberInstance.parse(latutideStartText.getText());
			latitudeEnd = (Double) numberInstance.parse(latutideEndText.getText());
			longitudeStart = (Double) numberInstance.parse(longitudeStartText.getText());
			longitudeEnd = (Double) numberInstance.parse(longitudeEndText.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return super.isPageComplete();
	}
	
	

}
