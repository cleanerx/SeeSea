package net.sf.seesea.navigation.ui.listener;

import java.text.DecimalFormat;

import org.eclipse.swt.widgets.Display;

import net.sf.seesea.model.core.physx.Distance;
import net.sf.seesea.model.core.physx.DistanceType;
import net.sf.seesea.navigation.ui.figures.DoubleRowedDescriptiveInstrumentFigure;
import net.sf.seesea.services.navigation.listener.ITotalLogListener;
import net.sf.seesea.services.navigation.listener.ITripLogListener;

public class LogFigureListener extends InvalidatingFigureListener<Distance> implements ITotalLogListener, ITripLogListener {

	private final DoubleRowedDescriptiveInstrumentFigure figure;
	private DecimalFormat logDecimalFormat;
	private final DistanceType type;

	
	public LogFigureListener(DoubleRowedDescriptiveInstrumentFigure figure, DistanceType type) {
		super(figure);
		this.figure = figure;
		this.type = type;
		logDecimalFormat = new DecimalFormat("##0.0"); //$NON-NLS-1$
	}
	
	@Override
	public void notify(final Distance sensorData, String source) {
		if(isSensorUpdateFast()) {
			return;
		}
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				if(type.equals(sensorData.getDistanceType())) {
					String nm = logDecimalFormat.format(sensorData.getValue()) + Messages.getString("LogFigureListener.nauticalMile");
					if(sensorData.getSensorID().equals("GPS")) {
						figure.setValue1(nm); //$NON-NLS-1$
					} else {
						figure.setValue2(nm); //$NON-NLS-1$
					}
					figure.repaint();
				}
			}
		});
	}

}
