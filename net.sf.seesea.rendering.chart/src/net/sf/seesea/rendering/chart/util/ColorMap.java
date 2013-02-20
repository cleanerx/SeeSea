/**
 * 
 Copyright (c) 2010-2012, Jens K�bler All rights reserved.
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
package net.sf.seesea.rendering.chart.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.seesea.model.int1.lights.Color;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.RGB;

/**
 * static map to map model colors to SWT colors  
 */
public class ColorMap {
	
	private static final RGB violet = new RGB(116,63,175);
	private static Map<net.sf.seesea.model.int1.lights.Color, org.eclipse.swt.graphics.Color> colorMap = new HashMap<net.sf.seesea.model.int1.lights.Color, org.eclipse.swt.graphics.Color>();

	public static org.eclipse.swt.graphics.Color getColor(net.sf.seesea.model.int1.lights.Color color) {
		if(colorMap.isEmpty()) {
			colorMap.put(Color.RED, ColorConstants.red);
			colorMap.put(Color.GREEN, ColorConstants.darkGreen);
			colorMap.put(Color.ORANGE, ColorConstants.orange);
			colorMap.put(Color.BLUE, ColorConstants.blue);
			colorMap.put(Color.BLACK, ColorConstants.black);
			colorMap.put(Color.VIOLET, new org.eclipse.swt.graphics.Color(null, violet));
			colorMap.put(Color.WHITE, ColorConstants.white);
			colorMap.put(Color.YELLOW, ColorConstants.yellow);
		}
		return colorMap.get(color);
	}
	
}
