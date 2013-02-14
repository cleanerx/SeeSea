package net.sf.seesea.rendering.chart;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.GestureListener;
import org.eclipse.swt.widgets.Canvas;

public class GestureLightweightSystem extends LightweightSystem {

		private Canvas canvas;

		@Override
		protected void addListeners() {
			super.addListeners();
			canvas.addGestureListener(new GEFGestureHandler());
		}

		@Override
		public void setControl(Canvas c) {
			this.canvas = c;
			super.setControl(c);
		}

		public class GEFGestureHandler implements GestureListener {
			public void gesture(GestureEvent e) {
				if (getEventDispatcher() instanceof GestureDomainEventDispatcher) {
					((GestureDomainEventDispatcher) getEventDispatcher()).dispatchGesture(e);
				}
			}
		}
}
