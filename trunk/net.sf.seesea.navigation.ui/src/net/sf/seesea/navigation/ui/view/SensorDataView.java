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
package net.sf.seesea.navigation.ui.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.DistanceType;
import net.sf.seesea.model.core.physx.HeadingType;
import net.sf.seesea.model.core.physx.SpeedType;
import net.sf.seesea.navigation.ui.NavigationUIActivator;
import net.sf.seesea.navigation.ui.figures.BarFigure;
import net.sf.seesea.navigation.ui.figures.ChildFigure;
import net.sf.seesea.navigation.ui.figures.DescriptiveInstrumentFigure;
import net.sf.seesea.navigation.ui.figures.DoubleRowedDescriptiveInstrumentFigure;
import net.sf.seesea.navigation.ui.figures.GraphFigure;
import net.sf.seesea.navigation.ui.figures.InstrumentContainerFigure;
import net.sf.seesea.navigation.ui.figures.SingleLinedFigure;
import net.sf.seesea.navigation.ui.figures.TextDoubleLinedInstrumentFigure;
import net.sf.seesea.navigation.ui.listener.DepthFigureListener;
import net.sf.seesea.navigation.ui.listener.HeadingListener;
import net.sf.seesea.navigation.ui.listener.ITideListener;
import net.sf.seesea.navigation.ui.listener.InvalidatingFigureListener;
import net.sf.seesea.navigation.ui.listener.LogFigureListener;
import net.sf.seesea.navigation.ui.listener.PositionFigureListener;
import net.sf.seesea.navigation.ui.listener.RelativeSpeedListener;
import net.sf.seesea.navigation.ui.listener.SateliteQualityFigureListener;
import net.sf.seesea.navigation.ui.listener.TimeFigureListener;
import net.sf.seesea.navigation.ui.listener.WaterTempertureFigureListener;
import net.sf.seesea.navigation.ui.listener.WindSpeedFigureListener;
import net.sf.seesea.services.navigation.listener.IDepthListener;
import net.sf.seesea.services.navigation.listener.IHeadingListener;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.services.navigation.listener.ISatelliteInfoListener;
import net.sf.seesea.services.navigation.listener.ISpeedListener;
import net.sf.seesea.services.navigation.listener.ITimeListener;
import net.sf.seesea.services.navigation.listener.ITotalLogListener;
import net.sf.seesea.services.navigation.listener.ITripLogListener;
import net.sf.seesea.services.navigation.listener.IWaterTemperatureListener;
import net.sf.seesea.services.navigation.listener.IWindListener;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * 
 */
public class SensorDataView extends ViewPart {


	private ServiceRegistration<?> positionRegistration;
	private ServiceRegistration<?> shipMovementRegistration;
	private Thread timeUpdateThread;
	private ServiceRegistration<?> depthRegistration;
	private ServiceRegistration<?> windRegistration;
	private ServiceRegistration<?> sateliteRegistration;
	private LocalResourceManager resourceManager;
	private ServiceRegistration<?> timeRegistration;
	private ServiceRegistration<?> tripRegistration;
	private ServiceRegistration<?> totalTripRegistration;
	private ServiceRegistration<?> speedListenerRegistration;
	private ServiceRegistration<?> mgkRegistration;
	private ServiceRegistration<?> fdwSpeedListenerRegistration;
	private ServiceRegistration<?> temperatureRegistration;
	private ServiceTracker<ITideListener, ITideListener> tideServiceTracker;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		resourceManager = new LocalResourceManager(JFaceResources.getResources());
		
		FigureCanvas figureCanvas = new FigureCanvas(parent);
		LightweightSystem lws = new LightweightSystem(figureCanvas);
		
		ScrollPane scrollPane = new ScrollPane();

		InstrumentContainerFigure instrumentContainerFigure = new InstrumentContainerFigure();
		int fontSize = 20;
		TextDoubleLinedInstrumentFigure positionInstrumentFigure = new TextDoubleLinedInstrumentFigure();
		positionInstrumentFigure.setUpperLine("--\u00B0 --.-' N"); //$NON-NLS-1$
		positionInstrumentFigure.setLowerLine("---\u00B0 --.-' E"); //$NON-NLS-1$
		positionInstrumentFigure.setFontSize(fontSize);

		Date time = Calendar.getInstance().getTime();
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm:ss z"); //$NON-NLS-1$
		
		SingleLinedFigure timeFigure = new SingleLinedFigure();
		timeFigure.setTime(dateFormatGmt.format(time));
		timeFigure.setFontSize(fontSize);

		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC")); //$NON-NLS-1$
		SingleLinedFigure timeFigureUTC = new SingleLinedFigure();
		timeFigureUTC.setTime(dateFormatGmt.format(time));
		timeFigureUTC.setFontSize(fontSize);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd"); //$NON-NLS-1$
		SingleLinedFigure dateFigure = new SingleLinedFigure();
		dateFigure.setTime(dateFormat.format(time));
		dateFigure.setFontSize(fontSize);
		
		DescriptiveInstrumentFigure cog = new DescriptiveInstrumentFigure();
		cog.setDescription(Messages.getString("SensorDataView.4")); //$NON-NLS-1$
		cog.setValue("---\u00B0"); //$NON-NLS-1$
		cog.setFontSize(fontSize);
		cog.setVisible(false);

		DescriptiveInstrumentFigure sog = new DescriptiveInstrumentFigure();
		sog.setDescription(Messages.getString("SensorDataView.6")); //$NON-NLS-1$
		sog.setValue("-.- kn"); //$NON-NLS-1$
		sog.setFontSize(fontSize);
		sog.setVisible(false);

		DescriptiveInstrumentFigure mgk = new DescriptiveInstrumentFigure();
		mgk.setDescription(Messages.getString("SensorDataView.8")); //$NON-NLS-1$
		mgk.setValue("---\u00B0"); //$NON-NLS-1$
		mgk.setFontSize(fontSize);
		mgk.setVisible(false);

		DescriptiveInstrumentFigure fdw = new DescriptiveInstrumentFigure();
		fdw.setDescription(Messages.getString("SensorDataView.10")); //$NON-NLS-1$
		fdw.setValue("-.- kn"); //$NON-NLS-1$
		fdw.setFontSize(fontSize);
		fdw.setVisible(false);

//		DoubleLinedInstrumentFigure windFigure = new DoubleLinedInstrumentFigure();
//		positionInstrumentFigure.setUpperLine("---\u00B0"); //$NON-NLS-1$
//		positionInstrumentFigure.setLowerLine("--.-kn"); //$NON-NLS-1$
//		positionInstrumentFigure.setFontSize(fontSize);

		DescriptiveInstrumentFigure windSpeed = new DescriptiveInstrumentFigure();
		windSpeed.setDescription("Wind SPD"); //$NON-NLS-1$
		windSpeed.setValue("-.- kn"); //$NON-NLS-1$
		windSpeed.setFontSize(fontSize);
		windSpeed.setVisible(false);

		DescriptiveInstrumentFigure windDirection = new DescriptiveInstrumentFigure();
		windDirection.setDescription("Wind DIR"); //$NON-NLS-1$
		windDirection.setValue("---\u00B0"); //$NON-NLS-1$
		windDirection.setFontSize(fontSize);
		windDirection.setVisible(false);

		final DescriptiveInstrumentFigure latoffset = new DescriptiveInstrumentFigure();
		latoffset.setDescription(Messages.getString("SensorDataView.tideOffset"));  //$NON-NLS-1$
		latoffset.setFontSize(fontSize);
		latoffset.setVisible(false);
		
		GraphFigure graphFigure = new GraphFigure(resourceManager);
		graphFigure.setFontSize(fontSize);
		graphFigure.setDescription(Messages.getString("SensorDataView.depthDescription")); //$NON-NLS-1$
		graphFigure.setVisible(false);
		
		BarFigure barFigure = new BarFigure(resourceManager);
		barFigure.setFontSize(fontSize);

		DoubleRowedDescriptiveInstrumentFigure tripFigure = new DoubleRowedDescriptiveInstrumentFigure();
		tripFigure.setDescription(Messages.getString("SensorDataView.0")); //$NON-NLS-1$
		tripFigure.setValue1("---.- nm"); //$NON-NLS-1$
		tripFigure.setValue2("---.- nm"); //$NON-NLS-1$
		tripFigure.setValueDescription1(Messages.getString("SensorDataView.GPS")); //$NON-NLS-1$
		tripFigure.setValueDescription2(Messages.getString("SensorDataView.LOG")); //$NON-NLS-1$
		tripFigure.setFontSize(fontSize);

		DoubleRowedDescriptiveInstrumentFigure totalTripFigure = new DoubleRowedDescriptiveInstrumentFigure();
		totalTripFigure.setDescription(Messages.getString("SensorDataView.2")); //$NON-NLS-1$
		totalTripFigure.setValue2("---.- nm"); //$NON-NLS-1$
		totalTripFigure.setValueDescription1(Messages.getString("SensorDataView.GPS")); //$NON-NLS-1$
		totalTripFigure.setValueDescription2(Messages.getString("SensorDataView.LOG")); //$NON-NLS-1$
		totalTripFigure.setFontSize(fontSize);

		DescriptiveInstrumentFigure waterTemperatureFigure = new DescriptiveInstrumentFigure();
		waterTemperatureFigure.setDescription(Messages.getString("SensorDataView.WaterTemperature"));  //$NON-NLS-1$
		waterTemperatureFigure.setValue("---.- \u00B0"); //$NON-NLS-1$
		waterTemperatureFigure.setFontSize(fontSize);
		waterTemperatureFigure.setVisible(false);

//		CompassFigure compassFigure = new CompassFigure();
		
		instrumentContainerFigure.getChildArea().add(positionInstrumentFigure);
		instrumentContainerFigure.getChildArea().add(timeFigure);
		instrumentContainerFigure.getChildArea().add(timeFigureUTC);
		instrumentContainerFigure.getChildArea().add(dateFigure);
		
		IFigure childArea = instrumentContainerFigure.getChildArea();
		ChildFigure childFigure = new ChildFigure();
		childFigure.add(cog);
		childFigure.add(sog);
		childFigure.setVisible(false);
		childArea.add(childFigure);
		GridLayout gridLayout = new GridLayout(2, true);
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 5;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		childFigure.setLayoutManager(gridLayout);

		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.grabExcessHorizontalSpace = true;
		gridLayout.setConstraint(cog, gridData);

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.grabExcessHorizontalSpace = true;
		gridLayout.setConstraint(sog, gridData);

		childFigure = new ChildFigure();
		childFigure.add(mgk);
		childFigure.add(fdw);
		childFigure.setVisible(false);
		childArea.add(childFigure);
		gridLayout = new GridLayout(2, true);
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 5;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		childFigure.setLayoutManager(gridLayout);

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.grabExcessHorizontalSpace = true;
		gridLayout.setConstraint(mgk, gridData);

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.grabExcessHorizontalSpace = true;
		gridLayout.setConstraint(fdw, gridData);

		instrumentContainerFigure.getChildArea().add(graphFigure);

		childFigure = new ChildFigure();
		childFigure.add(windSpeed);
		childFigure.add(windDirection);
		childFigure.setVisible(false);
		childArea.add(childFigure);
		gridLayout = new GridLayout(2, true);
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 5;
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		childFigure.setLayoutManager(gridLayout);

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.grabExcessHorizontalSpace = true;
		gridLayout.setConstraint(windSpeed, gridData);

		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.grabExcessHorizontalSpace = true;
		gridLayout.setConstraint(windDirection, gridData);
		
		instrumentContainerFigure.getChildArea().add(latoffset);
		
		instrumentContainerFigure.getChildArea().add(barFigure);
		instrumentContainerFigure.getChildArea().add(tripFigure);
		instrumentContainerFigure.getChildArea().add(totalTripFigure);
		instrumentContainerFigure.getChildArea().add(waterTemperatureFigure);
		
		scrollPane.setContents( instrumentContainerFigure );
		lws.setContents(scrollPane);
		
		InvalidatingFigureListener<MeasuredPosition3D> positionFigureListener = new PositionFigureListener(positionInstrumentFigure);
		positionRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(IPositionListener.class.getName(), positionFigureListener, null);

		tideServiceTracker = new ServiceTracker<>(NavigationUIActivator.getDefault().getBundle().getBundleContext(), ITideListener.class, new ServiceTrackerCustomizer<ITideListener, ITideListener>() {

			@Override
			public ITideListener addingService(ServiceReference<ITideListener> reference) {
				ITideListener tideListener = NavigationUIActivator.getDefault().getBundle().getBundleContext().getService(reference);
				tideListener.setFigure(latoffset);
				return tideListener;
			}

			@Override
			public void modifiedService(ServiceReference<ITideListener> reference, ITideListener service) {
				
			}

			@Override
			public void removedService(ServiceReference<ITideListener> reference, ITideListener service) {
				
			}
		});
		tideServiceTracker.open();

		
		HeadingListener shipMovementListener = new HeadingListener(cog, HeadingType.COG);
		shipMovementRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(IHeadingListener.class.getName(), shipMovementListener, null);

		HeadingListener mgkListener = new HeadingListener(mgk, HeadingType.MAGNETIC);
		mgkRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(IHeadingListener.class.getName(), mgkListener, null);

		RelativeSpeedListener speedListener = new RelativeSpeedListener(sog, SpeedType.COG);
		speedListenerRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(ISpeedListener.class.getName(), speedListener, null);

		RelativeSpeedListener fdwSpeedListener = new RelativeSpeedListener(fdw, SpeedType.SPEEDTHOUGHWATER);
		fdwSpeedListenerRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(ISpeedListener.class.getName(), fdwSpeedListener, null);

		DepthFigureListener depthFigureListener = new DepthFigureListener(graphFigure);
		depthRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(IDepthListener.class.getName(), depthFigureListener, null);
		
		WindSpeedFigureListener windSpeedListener = new WindSpeedFigureListener(windSpeed, windDirection);
		windRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(IWindListener.class.getName(), windSpeedListener, null);
		
		SateliteQualityFigureListener sateliteQualityFigureListener = new SateliteQualityFigureListener(barFigure); 
		sateliteRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(ISatelliteInfoListener.class.getName(), sateliteQualityFigureListener, null);
		
		TimeFigureListener timeListener = new TimeFigureListener(timeFigure, timeFigureUTC, dateFigure);
		timeRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(ITimeListener.class.getName(), timeListener, null);
		
		LogFigureListener logFigureListener = new LogFigureListener(totalTripFigure, DistanceType.TOTAL);
		tripRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(ITotalLogListener.class.getName(), logFigureListener, null);

		LogFigureListener tripFigureListener = new LogFigureListener(tripFigure, DistanceType.TRIP);
		totalTripRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(ITripLogListener.class.getName(), tripFigureListener, null);

		WaterTempertureFigureListener tempertureFigureListener = new WaterTempertureFigureListener(waterTemperatureFigure);
		temperatureRegistration = NavigationUIActivator.getDefault().getBundle().getBundleContext().registerService(IWaterTemperatureListener.class.getName(), tempertureFigureListener, null);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// nothing to do
	}

	@Override
	public void dispose() {
		positionRegistration.unregister();
		shipMovementRegistration.unregister();
		mgkRegistration.unregister();
		speedListenerRegistration.unregister();
		fdwSpeedListenerRegistration.unregister();
		depthRegistration.unregister();
		windRegistration.unregister();
		sateliteRegistration.unregister();
		timeRegistration.unregister();
//		latOffsetRegistration.unregister();
		if(timeUpdateThread != null) {
			timeUpdateThread.interrupt();
		}
		tripRegistration.unregister();
		totalTripRegistration.unregister();
		temperatureRegistration.unregister();
		resourceManager.dispose();
		tideServiceTracker.close();
		super.dispose();
	}
	
	

}
