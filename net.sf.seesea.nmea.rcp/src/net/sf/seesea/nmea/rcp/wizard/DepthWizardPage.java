package net.sf.seesea.nmea.rcp.wizard;

import java.text.NumberFormat;
import java.text.ParseException;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class DepthWizardPage extends WizardPage {
	private Text distanceSternText;
	private Text distanceCenterText;
	private Text distanceWaterlineText;
	private int x = 119;
	private int y = 240;
	private int z = 80;
	private boolean pressed = false;
	
	private double distanceStern;
	private double distanceCenter;
	private double distanceWaterline;
	private NumberFormat format = NumberFormat.getNumberInstance();
	private Canvas boatSchemeCanvas;
	private Text offsetToKeelText;


	public DepthWizardPage() {
		super("Depth", "Vessels", null);
		setMessage("Improve the accuracy of your data by providing the exact sensor position");
		setTitle("Depth Sensor Position");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.pack();
		setControl(composite);
		
		boatSchemeCanvas = new Canvas(composite, SWT.BORDER);

		Label lblDistance = new Label(composite, SWT.NONE);
		lblDistance.setText("Distance from Stern");
		
		distanceSternText = new Text(composite, SWT.BORDER);
		distanceSternText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					Number parse = format.parse(distanceSternText.getText());
					if(parse instanceof Long) {
						if(((Long) parse) < 0) {
							parse = 0L;
						}
						setDistanceStern(((Long) parse).doubleValue());
					} else if(parse instanceof Double) {
						if(((Double)parse) < 0) {
							parse = 0.0;
						}
						setDistanceStern((Double) parse);
					}
					setErrorMessage(null);
				} catch (ParseException e1) {
					setErrorMessage("Distance Stern is not a number");
				}
			}
		});
		
		Label lblDistanceFrom = new Label(composite, SWT.NONE);
		lblDistanceFrom.setText("Distance from Centerline");
		
		distanceCenterText = new Text(composite, SWT.BORDER);
		distanceCenterText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					Number parse = format.parse(distanceCenterText.getText());
					if(parse instanceof Long) {
//						if(((Long) parse) < 0) {
//							parse = 0L;
//						}
						setDistanceCenter(((Long) parse).doubleValue());
					} else if(parse instanceof Double) {
//						if(((Double)parse) < 0) {
//							parse = 0.0;
//						}
						setDistanceCenter((Double) parse);
					}
					setErrorMessage(null);
				} catch (ParseException e1) {
					setErrorMessage("Distance from Centerline is not a number");
				}
			}
		});
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Position below Waterline");
		
		distanceWaterlineText = new Text(composite, SWT.BORDER);
		distanceWaterlineText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					Number parse = format.parse(distanceWaterlineText.getText());
					if(parse instanceof Long) {
						Long value = (Long) parse; 
						if(value < 0) {
							parse = Math.abs(value);
						}
						setDistanceWaterline(((Long) parse).doubleValue());
					} else if(parse instanceof Double) {
						Double value = (Double)parse; 
						if(value < 0) {
							parse = Math.abs(value);
						}
						setDistanceWaterline(value);
					}
					setErrorMessage(null);
				} catch (ParseException e1) {
					setErrorMessage("Distance from Centerline is not a number");
				}
			}
		});
		
		boatSchemeCanvas.addMouseListener(new MouseAdapter() {

			public void mouseDown(MouseEvent e) {
				x = e.x;
				y = e.y;
				pressed = true;
				if(pressed && e.y >= 45 && e.y <= 240 && e.x >= 79 && e.x <= 160) {
					x = e.x;
					y = e.y;
					
					VesselWizardPage vesselWizardPage = (VesselWizardPage) getPreviousPage();

					double length = vesselWizardPage.getLength();
					distanceSternText.setText(format.format(((240D - y) / 195D) * length));
					
					double beam = vesselWizardPage.getBeam();
					distanceCenterText.setText(format.format(((x - 119.5) / 40.5) * beam));
					
					boatSchemeCanvas.redraw();
				}
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				pressed = false;
			}
			
		});
		boatSchemeCanvas.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				if(pressed && e.y >= 45 && e.y <= 240 && e.x >= 79 && e.x <= 160) {
					x = e.x;
					y = e.y;
					
					VesselWizardPage vesselWizardPage = (VesselWizardPage) getPreviousPage();

					double length = vesselWizardPage.getLength();
					distanceSternText.setText(format.format(((240D - y) / 195D) * length));
					
					double beam = vesselWizardPage.getBeam();
					distanceCenterText.setText(format.format(((x - 119.5) / 40.5) * beam));
					
					boatSchemeCanvas.redraw();
				}
			}
		});
		boatSchemeCanvas.addTouchListener(new TouchListener() {
			
			@Override
			public void touch(TouchEvent e) {
				if(e.y >= 45 && e.y <= 240 && e.x >= 79 && e.x <= 160) {
					x = e.x;
					y = e.y;
					
					VesselWizardPage vesselWizardPage = (VesselWizardPage) getPreviousPage();

					double length = vesselWizardPage.getLength();
					distanceSternText.setText(format.format(((240D - y) / 195D) * length));
					
					double beam = vesselWizardPage.getBeam();
					distanceCenterText.setText(format.format(((x - 119.5) / 40.5) * beam));
					
					boatSchemeCanvas.redraw();
				}
			}
		});
			
		boatSchemeCanvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				
				// center x at 119,5
				// extent x left/right 40.5
				// bottom y at 240
				// top y at 45
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
		
		Label lblDepthMeasurement = new Label(composite, SWT.NONE);
		lblDepthMeasurement.setText("Depth Measurement");
		
		ComboViewer comboViewer = new ComboViewer(composite, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		
		final Label lblOffsetToKeel = new Label(composite, SWT.NONE);
		lblOffsetToKeel.setText("Sensor offset to Keel");
		
		offsetToKeelText = new Text(composite, SWT.BORDER);
		offsetToKeelText.setEnabled(false);
		
		comboViewer.setContentProvider(new ArrayContentProvider());
		comboViewer.setLabelProvider(new LabelProvider());
		final String[] measurmentDepth = new String[]{"Autodetect", "Depth below Transducer", "Depth below Keel", "Depth below Waterline"};
		comboViewer.setInput(measurmentDepth);
		comboViewer.setSelection(new StructuredSelection(measurmentDepth[0]));
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Object element = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if(element.equals(measurmentDepth[2])) {
					lblOffsetToKeel.setEnabled(true);
					offsetToKeelText.setEnabled(true);
				} else {
					lblOffsetToKeel.setEnabled(false);
					offsetToKeelText.setEnabled(false);
				}
				
			}
		});
		
		Label lblDragThePosition = new Label(composite, SWT.NONE);
		lblDragThePosition.setText("Drag to position the sensor");
		
		final Canvas canvas = new Canvas(composite, SWT.BORDER);
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				
				gc.drawLine(0, 80, 40, 80);
				gc.drawLine(213, 80, 253, 80);
				gc.drawPolygon(new int[] {35,70,218,70,208,95,45,95});
				
				RGB rgb = new RGB(255, 0, 0);
				Color red = new Color(gc.getDevice(), rgb);
				gc.setBackground(red);
				gc.setForeground(red);
				gc.fillRoundRectangle(121, z - 5, 10, 10, 2, 2);
				red.dispose();
			}
		});	
		canvas.addMouseListener(new MouseAdapter() {

			public void mouseDown(MouseEvent e) {
				pressed = true;
				if(pressed && e.y >= 80 && e.y <= 240) {
					z = e.y;
					
					VesselWizardPage vesselWizardPage = (VesselWizardPage) getPreviousPage();

					double draft = vesselWizardPage.getDraft();
					distanceWaterlineText.setText(format.format(((z - 80D) / 61D) * draft));
					
					canvas.redraw();
				}
			}
			
			@Override
			public void mouseUp(MouseEvent e) {
				pressed = false;
			}
			
		});
		canvas.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
				if(pressed && e.y >= 80 && e.y <= 141) {
					z = e.y;
					
					VesselWizardPage vesselWizardPage = (VesselWizardPage) getPreviousPage();

					double draft = vesselWizardPage.getDraft();
					distanceWaterlineText.setText(format.format(((z - 80D) / 61D) * draft));
					
					canvas.redraw();
				}
			}
		});
		canvas.addTouchListener(new TouchListener() {
			
			@Override
			public void touch(TouchEvent e) {
				if(e.y >= 80 && e.y <= 141) {
					z = e.y;
					
					VesselWizardPage vesselWizardPage = (VesselWizardPage) getPreviousPage();

					double draft = vesselWizardPage.getDraft();
					distanceWaterlineText.setText(format.format(((z - 80D) / 61D) * draft));
					
					canvas.redraw();
				}
			}
		});
		
		GroupLayout gl_composite = new GroupLayout(composite);
		gl_composite.setHorizontalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.addContainerGap()
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(lblDistanceFrom)
						.add(lblNewLabel)
						.add(lblDistance)
						.add(lblDepthMeasurement)
						.add(lblOffsetToKeel))
					.add(18)
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(canvas, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.add(offsetToKeelText, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.add(combo, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.add(distanceSternText, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.add(distanceWaterlineText, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
						.add(distanceCenterText, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING, false)
						.add(gl_composite.createSequentialGroup()
							.add(39)
							.add(boatSchemeCanvas, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
						.add(GroupLayout.TRAILING, gl_composite.createSequentialGroup()
							.addPreferredGap(LayoutStyle.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.add(lblDragThePosition)
							.add(49))))
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_composite.createSequentialGroup()
					.addContainerGap()
					.add(gl_composite.createParallelGroup(GroupLayout.TRAILING)
						.add(gl_composite.createSequentialGroup()
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(lblDistance)
								.add(distanceSternText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.add(lblDragThePosition))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(lblDistanceFrom)
								.add(distanceCenterText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(lblNewLabel)
								.add(distanceWaterlineText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(lblDepthMeasurement)
								.add(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_composite.createParallelGroup(GroupLayout.BASELINE)
								.add(lblOffsetToKeel)
								.add(offsetToKeelText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(canvas, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
						.add(boatSchemeCanvas, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
					.add(25))
		);
		
		composite.setLayout(gl_composite);
	}

	public double getDistanceStern() {
		return distanceStern;
	}

	public void setDistanceStern(double distanceStern) {
		this.distanceStern = distanceStern;
		VesselWizardPage vesselWizardPage = (VesselWizardPage) getPreviousPage();
		double length = vesselWizardPage.getLength();
		if(distanceStern >= 0 && distanceStern <= length) {
			y = (int) ((-1)*((distanceStern / length) * 195D) + 240);
			boatSchemeCanvas.redraw();
		}
	}

	public double getDistanceCenter() {
		return distanceCenter;
	}

	public void setDistanceCenter(double distanceCenter) {
		this.distanceCenter = distanceCenter;
		VesselWizardPage vesselWizardPage = (VesselWizardPage) getPreviousPage();
		double beam = vesselWizardPage.getBeam();
		x = (int) ((distanceCenter / beam) * 40.5 + 119.5);
		boatSchemeCanvas.redraw();
		
	}

	public double getDistanceWaterline() {
		return distanceWaterline;
	}

	public void setDistanceWaterline(double distanceWaterline) {
		this.distanceWaterline = distanceWaterline;
	}
}
