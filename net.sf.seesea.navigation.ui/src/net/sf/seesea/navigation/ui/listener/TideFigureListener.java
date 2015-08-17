package net.sf.seesea.navigation.ui.listener;

import java.text.DecimalFormat;

import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.navigation.ui.NavigationUIActivator;
import net.sf.seesea.navigation.ui.figures.DescriptiveInstrumentFigure;
import net.sf.seesea.services.navigation.listener.IPositionListener;
import net.sf.seesea.waterlevel.ocean.IOceanTideProvider;
import net.sf.seesea.waterlevel.ocean.LengthUnit;
import net.sf.seesea.waterlevel.ocean.TideLevel;

import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class TideFigureListener extends InvalidatingFigureListener<MeasuredPosition3D> implements IPositionListener, ITideListener {

	private DescriptiveInstrumentFigure figure;
	private DecimalFormat tideFormat;

	public TideFigureListener() {
//		setFastTimeoutMilliseconds(250);
		tideFormat = new DecimalFormat("##.##");
	}

	@Override
	public void notify(final MeasuredPosition3D sensorData, String source) {
		if (isSensorUpdateFast()) {
			return;
		}
		BundleContext bundleContext = NavigationUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference<IOceanTideProvider> reference = bundleContext.getServiceReference(IOceanTideProvider.class);
		if (reference != null) {
			IOceanTideProvider oceanTideProvider = bundleContext.getService(reference);
			final double tideHeight = oceanTideProvider.getTideHeight(TideLevel.LOWESTATRONOMICALTIDE, LengthUnit.METERS, sensorData.getLatitude()
					.getDecimalDegree(), sensorData.getLongitude().getDecimalDegree(), sensorData.getTime());

			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					figure.setVisible(true);
					figure.setValue(tideFormat.format(tideHeight) + "m");
				}
			});
			bundleContext.ungetService(reference);
		}
	}

	public DescriptiveInstrumentFigure getFigure() {
		return figure;
	}

	public void setFigure(DescriptiveInstrumentFigure figure) {
		addFigure(figure);
		this.figure = figure;
	}

}
