package net.sf.seesea.provider.navigation.nmea.csv;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;
import java.util.Locale;

import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.physx.CompositeMeasurement;
import net.sf.seesea.model.core.physx.Measurement;

public class CSVWriter implements IDataWriter {

	private DecimalFormat longitudeFormat;

	private DecimalFormat latitudeFormat;

	private PrintWriter printWriter;

	private DecimalFormat depthFormat;

	private final OutputStream outputStream;

	private DateFormat dateFormat;

	public CSVWriter(OutputStream outputStream) {
		this.outputStream = outputStream;
		latitudeFormat = new DecimalFormat("00.0#######", new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
		longitudeFormat = new DecimalFormat("000.0#######", new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
		depthFormat = new DecimalFormat("0.0#######", new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.ENGLISH);
	}
	
	
	@Override
	public void write(Measurement measurement, boolean valid) {
		if(measurement instanceof CompositeMeasurement) {
			CompositeMeasurement compositeMeasurement = (CompositeMeasurement) measurement;
			write(compositeMeasurement.getMeasurements(), valid);
		}
	}

	@Override
	public void write(Collection<Measurement> measurements, boolean valid) {
		MeasuredPosition3D geoPosition = null;
		Depth depth = null;
		if(printWriter == null) {
			printWriter = new PrintWriter(outputStream);
		}

		for (Measurement measurement2 : measurements) {
			if(measurement2 instanceof MeasuredPosition3D) {
				geoPosition = (MeasuredPosition3D) measurement2;
			} else if(measurement2 instanceof Depth) {
				depth = (Depth) measurement2;
			}
		}

		if(geoPosition != null && depth != null) {
			StringBuffer message = new StringBuffer();
			
			double decDegree = geoPosition.getLatitude().getDecimalDegree();
			message.append(dateFormat.format(geoPosition.getTime()));
			message.append(',');
			message.append(latitudeFormat.format(decDegree));
			message.append(',');
			decDegree = geoPosition.getLongitude().getDecimalDegree();
			message.append(longitudeFormat.format(decDegree));
			message.append(',');
			message.append(depthFormat.format(depth.getDepth()));
			message.append("\r\n"); //$NON-NLS-1$
			printWriter.write(message.toString());
		}
	}

	@Override
	public void closeOutput() {
		if(printWriter != null) {
			printWriter.close();
		}
	}

}
