package net.sf.seesea.provider.navigation.nmea.v183;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;

import net.sf.seesea.data.io.IDataWriter;
import net.sf.seesea.model.core.geo.Depth;
import net.sf.seesea.model.core.geo.MeasuredPosition3D;
import net.sf.seesea.model.core.geo.RelativeDepthMeasurementPosition;
import net.sf.seesea.model.core.physx.Measurement;

public class NMEA0183Writer implements IDataWriter {
	
	private PrintWriter printWriter;
	private DecimalFormat singleDigitFormat;
	private SimpleDateFormat simpleTimeFormat;
	private DecimalFormat twoZero;
	private DecimalFormat minuteFormat;
	private DecimalFormat threeZero;


	public NMEA0183Writer(OutputStream outputStream) {
		printWriter = new PrintWriter(outputStream);
		singleDigitFormat = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
		simpleTimeFormat = new SimpleDateFormat("hhmmss"); //$NON-NLS-1$
		twoZero = new DecimalFormat("00", new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
		threeZero = new DecimalFormat("000", new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
		minuteFormat = new DecimalFormat("00.00000", new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$

	}

	@Override
	public void write(Collection<Measurement> data) {
		
		for (Measurement measurement : data) {
			if(measurement instanceof MeasuredPosition3D) {
				StringBuffer message = new StringBuffer();
				// $--RMC,hhmmss.ss,A,llll.ll,a,yyyyy.yy,a,x.x,x.x,xxxx,x.x,a*hh<CR><LF>
				MeasuredPosition3D position3d = (MeasuredPosition3D) measurement;
				message.append('$');
				message.append(measurement.getSensorID());
				message.append("GLL,"); //$NON-NLS-1$
				double decDegree = position3d.getLatitude().getDecimalDegree();
				boolean south = decDegree < 0;
				decDegree = Math.signum(decDegree);
				message.append(twoZero.format((int)decDegree));
				
				decDegree = (decDegree - ((int)decDegree)) * 60;
				decDegree = Math.abs(decDegree);
				message.append(minuteFormat.format(decDegree));
				if(south) {
					message.append(",S,"); //$NON-NLS-1$
				} else {
					message.append(",N,"); //$NON-NLS-1$
				}

				decDegree = position3d.getLatitude().getDecimalDegree();
				boolean west = decDegree < 0;
				decDegree = Math.signum(decDegree);
				message.append(threeZero.format((int)decDegree));
				
				decDegree = (decDegree - ((int)decDegree)) * 60;
				decDegree = Math.abs(decDegree);
				message.append(minuteFormat.format(decDegree));
				if(west) {
					message.append(",W,"); //$NON-NLS-1$
				} else {
					message.append(",E,"); //$NON-NLS-1$
				}
				message.append(simpleTimeFormat.format(position3d.getTime()));
				if(measurement.isValid()) {
					message.append(",A,"); //$NON-NLS-1$
				} else {
					message.append(",V,"); //$NON-NLS-1$
				}
				message.append('*');
				printWriter.append(getCheckSum(message.toString()));
				printWriter.append("\r\n"); //$NON-NLS-1$
//			} else if(measurement instanceof ShipMovementVector) {
//				
			} else if(measurement instanceof Depth) {
				StringBuffer message = new StringBuffer();
				// $--DBT,x.x,f,x.x,M,x.x,F*hh<CR><LF>
				// $--DBS,x.x,f,x.x,M,x.x,F*hh<CR><LF>
				// $--DBK,x.x,f,x.x,M,x.x,F*hh<CR><LF>
				Depth depth = (Depth) measurement;
				// FIXME: sensor id would be nice
				message.append('$');
				message.append(measurement.getSensorID());
				RelativeDepthMeasurementPosition depthMeasurementPosition = depth.getMeasurementPosition();
				switch (depthMeasurementPosition) {
				case KEEL:
					message.append("DBK,"); //$NON-NLS-1$
					break;
				case SURFACE:
					message.append("DBS,"); //$NON-NLS-1$
					break;
				case TRANSDUCER:
					message.append("DBS,"); //$NON-NLS-1$
					break;
				}
				double depthValue = depth.getDepth();
				message.append(singleDigitFormat.format(depthValue * 3.2808399));
				message.append(",f");
				message.append(singleDigitFormat.format(depthValue));
				message.append(",M");
				message.append(singleDigitFormat.format(depthValue * 0.546806649));
				message.append(",F");
				message.append('*');
				printWriter.append(getCheckSum(message.toString()));
				printWriter.append("\r\n");
			}
		}
		printWriter.flush();
	}

	
	  /**
     * Trims the checksum off an NMEA message, then
     * recalculates the checksum
     * to compare it with the one passed along with the message later
     * 
     * @param msg String containing the full NMEA message (including checksum)
     * @return String containing the checksum
     */
    private static String getCheckSum(String msg) {
        // perform NMEA checksum calculation
        int chk = 0;
        //run through each character of the message length
        //and XOR the value of chk with the byte value
        //of the character that is being evaluated
        for (int i = 1; i < msg.length(); i++) {
            chk ^= msg.charAt(i);
        }

        //convert the retreived integer to a HexString in uppercase
        String chk_s = Integer.toHexString(chk).toUpperCase();

        // checksum must be 2 characters!
        // if it falls short, add a zero before the checksum
        while (chk_s.length() < 2) {
            chk_s = "0" + chk_s;
        }

        //show the calculated checksum
        // System.out.println("    calculated checksum : " + chk_s);

        //return the calculated checksum
        return chk_s;
    }

}
