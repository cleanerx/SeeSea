package net.sf.seesea.provider.navigation.nmea.ui.view;

import java.util.HashMap;
import java.util.Map;

import net.sf.seesea.provider.navigation.nmea.NMEA0183MessageTypes;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class NMEAColumnLabelProvider extends ColumnLabelProvider {

	private Map<NMEA0183MessageTypes, Color> colors;
	
	public NMEAColumnLabelProvider() {
		colors = new HashMap<NMEA0183MessageTypes, Color>();
		colors.put(NMEA0183MessageTypes.RMC, new Color(Display.getDefault(), new RGB(255,200,100)));
		colors.put(NMEA0183MessageTypes.GGA, new Color(Display.getDefault(), new RGB(255,100,100)));
		colors.put(NMEA0183MessageTypes.GSA, new Color(Display.getDefault(), new RGB(255,50,50)));
		colors.put(NMEA0183MessageTypes.GSV, new Color(Display.getDefault(), new RGB(150,75,75)));
		colors.put(NMEA0183MessageTypes.GLL, new Color(Display.getDefault(), new RGB(150,125,75)));
		colors.put(NMEA0183MessageTypes.MTW, new Color(Display.getDefault(), new RGB(0,75,150)));
		colors.put(NMEA0183MessageTypes.MWV, new Color(Display.getDefault(), new RGB(100,197,193)));
		colors.put(NMEA0183MessageTypes.DBT, new Color(Display.getDefault(), new RGB(100,120,200)));
		colors.put(NMEA0183MessageTypes.DBK, new Color(Display.getDefault(), new RGB(120,100,200)));
		colors.put(NMEA0183MessageTypes.DBS, new Color(Display.getDefault(), new RGB(80,120,200)));
		colors.put(NMEA0183MessageTypes.RSA, new Color(Display.getDefault(), new RGB(189,135,197)));
		colors.put(NMEA0183MessageTypes.HDM, new Color(Display.getDefault(), new RGB(197,174,83)));
		colors.put(NMEA0183MessageTypes.VHW, new Color(Display.getDefault(), new RGB(145,197,126)));
	}
	
	@Override
	public Color getBackground(Object element) {
		String text = (String) element;
		if(text.length() > 6) {
			String substring = text.substring(3, 6);
			try {
				NMEA0183MessageTypes messageTypes = NMEA0183MessageTypes.valueOf(substring);
				return colors.get(messageTypes);
			} catch (Exception e) {
				// nothing to do
			}
		}
		return super.getBackground(element);
	}
	
	@Override
	public void dispose() {
		for(Color c : colors.values()) {
			c.dispose();
		}
		super.dispose();
	};
}
