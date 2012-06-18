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

package net.sf.seesea.navigation.ui.figures;

import net.sf.seesea.navigation.ui.NavigationColorConstants;
import net.sf.seesea.navigation.ui.NavigationUIActivator;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class CompassFigure extends Figure {

	private Image compassImage;
	private Color lightRed;
	private Color darkRed;

	public CompassFigure(ResourceManager resourceManager) {
		ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(NavigationUIActivator.getPluginID(), "/res/compass240x240.png"); //$NON-NLS-1$
		compassImage = resourceManager.createImage(imageDescriptor);
		lightRed = resourceManager.createColor(NavigationColorConstants.LIGHT_RED);
		darkRed = resourceManager.createColor(NavigationColorConstants.DARK_RED);
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		Rectangle copy = getBounds().getCopy();

		super.paintFigure(g);
		g.drawImage(compassImage, copy.getTopLeft());
		
		setForegroundColor(ColorConstants.red);
		int xcenter = copy.x + copy.width / 2;
		int margin = 70;
		
//		 Image image = new Image(Display.getDefault(), copy.width, copy.height);
//   	    GC gc = new GC(image);
//		ImageData imageData = image.getImageData();
//		int whitePixel = imageData.palette.getPixel(new RGB(255,255,255));
//		imageData.transparentPixel = whitePixel;
//		gc.setBackground(ColorConstants.white);
//		gc.fillRectangle(copy.x,copy.y, copy.width, copy.height);
//		gc.setBackground(ColorConstants.green);
//		gc.fillRectangle(copy.x,copy.y, 10, 10);
//		g.drawImage(image, copy.x,copy.y);
//		gc.dispose();
		
		int arrowLength = 10;
		setForegroundColor(lightRed);
		for(int i = -3; i < 5 ; i++) {
			g.drawLine(xcenter, copy.y + margin + i, xcenter - arrowLength, copy.y + margin + arrowLength + i);
		}
		setForegroundColor(darkRed);
		for(int i = -3; i < 5 ; i++) {
			g.drawLine(xcenter, copy.y + margin + i, xcenter + arrowLength, copy.y + margin + arrowLength + i);
		}
//		g.drawLine(xcenter, copy.y + margin + 1, xcenter + arrowLength , copy.y + margin + arrowLength + 1);
//		g.drawLine(xcenter, copy.y + margin + 2, xcenter + arrowLength , copy.y + margin + arrowLength + 2);

		
		setForegroundColor(lightRed);
		setBackgroundColor(darkRed);
		g.fillGradient(xcenter - 3, copy.y + margin, 5, copy.height - margin * 2, false);

//		g.drawLine(xcenter, copy.y + margin , xcenter, copy.y + copy.height - margin);
	}

	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		return new Dimension(compassImage.getBounds().width, compassImage.getBounds().height);
	}
}
