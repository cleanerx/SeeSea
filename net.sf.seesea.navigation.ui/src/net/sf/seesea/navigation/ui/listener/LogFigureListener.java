package net.sf.seesea.navigation.ui.listener;

import java.text.DecimalFormat;

import net.sf.seesea.model.core.physx.Distance;
import net.sf.seesea.navigation.ui.figures.DescriptiveInstrumentFigure;
import net.sf.seesea.services.navigation.listener.ITotalLogListener;
import net.sf.seesea.services.navigation.listener.ITripLogListener;

import org.eclipse.swt.widgets.Display;

public class LogFigureListener extends InvalidatingFigureListener<Distance> implements ITotalLogListener, ITripLogListener {

	private final DescriptiveInstrumentFigure figure;
	private DecimalFormat logDecimalFormat;

	
	public LogFigureListener(DescriptiveInstrumentFigure figure) {
		super(figure);
		this.figure = figure;
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
				figure.setValue(logDecimalFormat.format(sensorData.getValue()) + Messages.getString("LogFigureListener.nauticalMile")); //$NON-NLS-1$
				figure.repaint();
			}
		});
	}

}
