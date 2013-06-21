package net.sf.seesea.nmea.rcp.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.ui.internal.quickaccess.CamelUtil;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class SBASWizardPage extends WizardPage {
	private Text text;
	private Text text_1;
	private Text text_2;
	private int x;
	private int y;
	private boolean pressed = false;

	public SBASWizardPage() {
		super("Vessels", "Vessels", null);
		setTitle("Satellite Position Sensor");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.pack();
		setControl(composite);
		
		final Canvas canvas = new Canvas(composite, SWT.BORDER);
		canvas.addMouseListener(new MouseAdapter() {

			public void mouseDown(MouseEvent e) {
				x = e.x;
				y = e.y;
				pressed = true;
				canvas.redraw();
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				pressed = false;
			}
			
		});
		canvas.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				if(pressed) {
					x = e.x;
					y = e.y;
					canvas.redraw();
				}
			}
		});
			
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				
				gc.drawLine(81, 240, 158, 240);
				gc.drawArc(60, 39, 100, 300, -20, 100);
				gc.drawArc(78, 39, 100, 300, 100, 100);
				
				RGB rgb = new RGB(255, 0, 0);
				Color red = new Color(gc.getDevice(), rgb);
				gc.setBackground(red);
				gc.setForeground(red);
				gc.fillRoundRectangle(x - 5, y - 5, 10, 10, 2, 2);
				red.dispose();
			}
		});
		
		Label lblDistance = new Label(composite, SWT.NONE);
		lblDistance.setText("Distance from Stern");
		
		Label lblDragThePosition = new Label(composite, SWT.NONE);
		lblDragThePosition.setText("Drag the position around");
		
		text = new Text(composite, SWT.BORDER);
		
		Label lblDistanceFrom = new Label(composite, SWT.NONE);
		lblDistanceFrom.setText("Distance from Centerline");
		
		text_1 = new Text(composite, SWT.BORDER);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Height above Waterline");
		
		text_2 = new Text(composite, SWT.BORDER);
		GroupLayout gl_composite = new GroupLayout(composite);
		gl_composite.setHorizontalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.addContainerGap()
					.add(gl_composite.createParallelGroup(GroupLayout.TRAILING)
						.add(gl_composite.createSequentialGroup()
							.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
								.add(gl_composite.createSequentialGroup()
									.add(lblDistance)
									.addPreferredGap(LayoutStyle.RELATED, 46, Short.MAX_VALUE)
									.add(text, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
								.add(gl_composite.createSequentialGroup()
									.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
										.add(lblDistanceFrom)
										.add(lblNewLabel))
									.add(18)
									.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
										.add(text_2, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
										.add(text_1, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))))
							.add(21)
							.add(18)
							.add(canvas, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
						.add(gl_composite.createSequentialGroup()
							.add(lblDragThePosition)
							.add(50))))
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(gl_composite.createSequentialGroup()
							.addContainerGap()
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(lblDistance)
								.add(text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(lblDistanceFrom)
								.add(text_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(lblNewLabel)
								.add(text_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.add(canvas, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblDragThePosition)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		composite.setLayout(gl_composite);
	}
}
