/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
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
package net.sf.seesea.rendering.chart.editpolicies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.seesea.rendering.chart.editpart.WorldEditPart;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Tool;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.SimpleRootEditPart;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

/**
 * @version $Id: AbstractPopupBarEditPolicy.java 101196 2010-05-28 08:56:35Z gebauer $
 */
public abstract class AbstractPopupBarEditPolicy extends GraphicalEditPolicy implements MouseMotionListener {

  public enum PopupBarPosition {
    MOUSE, CENTER, TOP, LEFT, RIGHT, BOTTOM, HORIZONTAL_SIDES, VERTICAL_SIDES, ALL_SIDES
  }

  /**
   * Default tool placed on the popup bar
   * 
   * @author affrantz@us.ibm.com
   */

  private class PopupBarEntryLabel extends Label implements MouseListener, MouseMotionListener {
    private PaletteEntry _paletteEntry = null;

    private Tool _tool = null;

    private Color HOVER_COLOR = ColorConstants.green;

    private Color HOVER_COLOR_HICONTRAST = ColorConstants.darkGreen;

    /**
     * @param paletteEntry
     */
    public PopupBarEntryLabel(PaletteEntry paletteEntry) {
      super();
      _paletteEntry = paletteEntry;
      if (_paletteEntry != null && _paletteEntry.getSmallIcon() != null) {
//        Image image = ResourceManager.getImage(paletteEntry.getSmallIcon());
//        super.setIcon(image);
      }
      if (_paletteEntry instanceof ToolEntry) {
        _tool = ((ToolEntry) _paletteEntry).createTool();
      }
      this.setOpaque(true);
      this.setBackgroundColor(ColorConstants.buttonLightest);
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
    }

    /**
     * @see org.eclipse.draw2d.MouseListener#mouseDoubleClicked(org.eclipse.draw2d.MouseEvent)
     */
    public void mouseDoubleClicked(MouseEvent me) {
      //nothing to do

    }

    /**
     * If there is an valid PopupBarTool it is activated
     * 
     * @see org.eclipse.draw2d.MouseListener#mousePressed(org.eclipse.draw2d.MouseEvent)
     */
    public void mousePressed(MouseEvent me) {
      if (me.button == 1) {
//        if (_tool instanceof IPopupBarCreationTool) {
//          ((IPopupBarCreationTool) _tool).setActivatedFromPopupBar(true);
//          ((IPopupBarCreationTool) _tool).setPopupBarHost((GraphicalEditPart) getHost());
//          ((IPopupBarCreationTool) _tool).setOriginMouseLocation(getOriginMouseLocation());
//          getHost().getViewer().getEditDomain().setActiveTool(_tool);
//          hidePopupBar();
//          reorderPopupBarEntries(_paletteEntry);
//        }
      }
    }

    /**
     * @see org.eclipse.draw2d.MouseListener#mouseReleased(org.eclipse.draw2d.MouseEvent)
     */
    public void mouseReleased(MouseEvent me) {
      //nothing to do
    }

    /**
     * @see org.eclipse.draw2d.MouseMotionListener#mouseDragged(org.eclipse.draw2d.MouseEvent)
     */
    public void mouseDragged(MouseEvent me) {
      //nothing to do

    }

    /**
     * The BackgroundColor is changed for user feedback
     * 
     * @see org.eclipse.draw2d.MouseMotionListener#mouseEntered(org.eclipse.draw2d.MouseEvent)
     */
    public void mouseEntered(MouseEvent me) {
      this.setBackgroundColor(getHoverColor());
    }

    /**
     * The BackgroundGolor is reseted
     * 
     * @see org.eclipse.draw2d.MouseMotionListener#mouseExited(org.eclipse.draw2d.MouseEvent)
     */
    public void mouseExited(MouseEvent me) {
      this.setBackgroundColor(ColorConstants.buttonLightest);
    }

    /**
     * @see org.eclipse.draw2d.MouseMotionListener#mouseHover(org.eclipse.draw2d.MouseEvent)
     */
    public void mouseHover(MouseEvent me) {
      //nothing to do

    }

    /**
     * @see org.eclipse.draw2d.MouseMotionListener#mouseMoved(org.eclipse.draw2d.MouseEvent)
     */
    public void mouseMoved(MouseEvent me) {
      //nothing to do
    }

    private Color getHoverColor() {
      Display display = Display.getCurrent();
      if (display == null) {
        display = Display.getDefault();
      }
      if (display.getHighContrast()) {
        return HOVER_COLOR_HICONTRAST;
      }
      return HOVER_COLOR;
    }

  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#isDiagramAssistant(java.lang.Object)
   */
  protected boolean isDiagramAssistant(Object object) {
    return object instanceof PopupBarEntryLabel;
  }

  /**
   * Adds the popup bar after a delay
   */
  public void mouseHover(MouseEvent me) {
    if (!isDiagramAssistant(me.getSource())) {
      setAvoidHidingDiagramAssistant(false);
    }

    setMouseLocation(me.getLocation());
    if (shouldShowPopupBar()) {
      showDiagramAssistantAfterDelay(getAppearanceDelay());
    }
  }

  /**
   * @see org.eclipse.draw2d.MouseMotionListener#mouseMoved(org.eclipse.draw2d.MouseEvent)
   */
  public void mouseMoved(MouseEvent me) {
    setAvoidHidingDiagramAssistant(true);
    setMouseLocation(me.getLocation());
    if (!isDiagramAssistant(me.getSource())) {
      setAvoidHidingDiagramAssistant(false);
    }

    showDiagramAssistantAfterDelay(getAppearanceDelay());
  }

  /**
   * Listens to the owner figure being moved so the handles can be removed when this occurs.
   * 
   * @author affrantz@us.ibm.com
   */
  private class OwnerMovedListener implements FigureListener {

    public void figureMoved(IFigure source) {
      hidePopupBar();
    }
  }

  /**
   * Listens for mouse key presses so the popup bar can be dismissed if the context menu is displayed
   * 
   * @author affrantz@us.ibm.com
   */
  private class PopupBarMouseListener extends MouseListener.Stub {

    /**
     * @see org.eclipse.draw2d.MouseListener#mousePressed(org.eclipse.draw2d.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent me) {
      if (3 == me.button) // context menu, hide the popup bar
      {
        hidePopupBar();
      }
      super.mousePressed(me);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
      super.mouseReleased(me);
    }
  }

  private class PopupBarKeyListener implements KeyListener {
    /**
     * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
     */
    public void keyPressed(KeyEvent e) {
      if (e.keyCode == SWT.ESC) {
        if (isPopupBarShowing()) {
          hidePopupBar();
        }
      }

    }

    /**
     * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
     */
    public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
      // nothing to do
    }
  }

  private class ShowPopupBarJob extends UIJob {

    /** the mouse location when the job was created */
    private Point originalMouseLocation;

    /**
     * Creates a new instance.
     */
    protected ShowPopupBarJob() {
      super("Show Pop-up Bar"); //$NON-NLS-1$
      setSystem(true);
    }

    /**
     * Sets mouse location
     * 
     * @param originalMouseLocationParam the current mouse location
     */
    public void setOriginalMouseLocation(Point originalMouseLocationParam) {
      this.originalMouseLocation = originalMouseLocationParam;
    }

    /**
     * The diagram assistant added when this task is run if the mouse is still at the same spot where it was when the timer was started (i.e. only add
     * the diagram assistant when the user stops moving the mouse).
     */
    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
      if (originalMouseLocation != null && originalMouseLocation.equals(getMouseLocation())) {
        if (isPopupBarShowing() && !shouldAvoidHidingPopupBar()) {
          hidePopupBar();
        }
        if (shouldShowPopupBar()) {
          // Cancel the hide diagram assistant job for this host if it
          // is waiting to run.
          hidePopupBarJob.cancel();

          // Schedule any hide diagram assistant jobs on other
          // editparts to run immediately to avoid duplicate diagram
          // assistants showing.
          if (getPopupBarID() != null) {
            Job.getJobManager().wakeUp(getPopupBarID());
          }

          showPopupBar(originalMouseLocation);
        }
      }
      return Status.OK_STATUS;
    }
  }

  /**
   * The <code>Job</code> used to hide the diagram assistant after a certain amount of time has passed.
   */
  private class HidePopupBarJob extends UIJob {

    protected HidePopupBarJob() {
      super("Hide Diagram Assistant"); //$NON-NLS-1$
      setSystem(true);
    }

    /**
     * The diagram assistant is removed when this task is run if the mouse is still outside the shape.
     */
    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
      if (getMouseLocation() == null || !shouldAvoidHidingPopupBar()) {
        hidePopupBar();
      }
      return Status.OK_STATUS;
    }

    @Override
    public boolean belongsTo(Object family) {
      return family == getPopupBarID();
    }
  }

  /* End nested classes */

  /** X postion offset from shape where the balloon top begin. */
  static private int ACTION_WIDTH_AND_HEIGHT = 20;

  static private int ACTION_BUTTON_OFFSET = 4;

  static private Map<String, String> _priorityPaletteEntyMap = new HashMap<String, String>();

  /** the figure used to surround the action buttons */
  private Map<PopupBarPosition, IFigure> _balloons = new HashMap<PopupBarPosition, IFigure>();

  /** The popup bar descriptors for the popup bar buttons */
  private List<PaletteEntry> _popupBarToolEntries = new ArrayList<PaletteEntry>();

  private Map<String, PaletteEntry> _nameToEntryMap = new HashMap<String, PaletteEntry>();

  private PaletteEntry _priorityPopupBarToolEntry;

  /** Images created that must be deleted when popup bar is removed */
  protected List<Image> imagesToBeDisposed = null;

  /** mouse keys listener for the owner shape */
  private PopupBarMouseListener myMouseKeyListener = new PopupBarMouseListener();

  /** listener for owner shape movement */
  private OwnerMovedListener myOwnerMovedListener = new OwnerMovedListener();

  /** listener for key events */
  private PopupBarKeyListener popupBarKeyListener = new PopupBarKeyListener();

  /**
   * The current mouse location within the host used to determine where the diagram assistant should be displayed. This will be null if the mouse is
   * outside the host and diagram assistant figure.
   */
  private Point mouseLocation;

  /**
   * The location of the mouse to be used for the operations started from the popup bar.
   */
  private Point _originMouseLocation = null;

  /** Flag to indicate that the diagram assistant should not be hidden. */
  private boolean avoidHidingDiagramAssistant = true;

  private ShowPopupBarJob showPopupBarJob = new ShowPopupBarJob();

  private HidePopupBarJob hidePopupBarJob = new HidePopupBarJob();

  private final String _toolKey;

  /**
   * Puts the given PaletteEntry to the first Position of the PaletteEntry list to provied it on first position when the popupbar is opened again
   * 
   * @param paletteEntry The PaletteEntry to put on first position
   */
  private void reorderPopupBarEntries(PaletteEntry paletteEntry) {
    _priorityPopupBarToolEntry = paletteEntry;
    _priorityPaletteEntyMap.put(_toolKey, paletteEntry.toString());
  }

  /**
   * 
   */
  public AbstractPopupBarEditPolicy(String toolKey, List<PaletteEntry> popupBarPaletteEntries) {
    super();
    _toolKey = toolKey;
    _popupBarToolEntries.addAll(popupBarPaletteEntries);
    for (PaletteEntry paletteEntry : popupBarPaletteEntries) {
      _nameToEntryMap.put(paletteEntry.toString(), paletteEntry);
    }

  }

  private boolean isSelectionToolActive() {
    // getViewer calls getParent so check for null
    if ((getHost().getParent() != null || getHost() instanceof SimpleRootEditPart) && getHost().isActive()) {
      Tool theTool = getHost().getViewer().getEditDomain().getActiveTool();
      if ((theTool != null) && theTool instanceof SelectionTool) {
        return true;
      }
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#shouldShowDiagramAssistant()
   */
  protected boolean shouldShowPopupBar() {
    EditPart parent = getHost().getParent();
    if (!(getHost().isActive() && isHostEditable() && isPreferenceOn() && isDiagramPartActive())) {
      return false;
    }
    if (!(getHost().getViewer().getSelectedEditParts().isEmpty() && (getHost() instanceof WorldEditPart || getHost() instanceof SimpleRootEditPart))
        && getHost().getSelected() == org.eclipse.gef.EditPart.SELECTED_NONE) {
//      while (parent instanceof EditPart) {
//        parent = parent.getParent();
//      }
      if (parent != null && (parent.getSelected() != org.eclipse.gef.EditPart.SELECTED_NONE)) {
        return isSelectionToolActive();
      }
      return false;
    }
    return isSelectionToolActive();
  }

  /**
   * Returns true if the preference to show this diagram assistant is on or if there is no applicable preference; false otherwise.
   */
  protected boolean isPreferenceOn() {
	  return true;
  }

  protected void addPopupBarDescriptor(PaletteEntry paletteEntry) {
    _popupBarToolEntries.add(paletteEntry);
  }

  /**
   * gets the popup bar descriptors
   * 
   * @return list
   */
  protected List<PaletteEntry> getPopupBarDescriptors() {
    return _popupBarToolEntries;
  }

  /**
   * initialize the popup bars from the list of action descriptors.
   */
  private void initPopupBars() {

    List<PaletteEntry> theList = getPopupBarDescriptors();
    if (theList.isEmpty()) {
      return;
    }
    if (getPopupBarPosition() == PopupBarPosition.ALL_SIDES) {
      _balloons.put(PopupBarPosition.LEFT, createPopupBarFigure());
      _balloons.put(PopupBarPosition.RIGHT, createPopupBarFigure());
      _balloons.put(PopupBarPosition.TOP, createPopupBarFigure());
      _balloons.put(PopupBarPosition.BOTTOM, createPopupBarFigure());
    } else if (getPopupBarPosition() == PopupBarPosition.HORIZONTAL_SIDES) {
      _balloons.put(PopupBarPosition.TOP, createPopupBarFigure());
      _balloons.put(PopupBarPosition.BOTTOM, createPopupBarFigure());
    } else if (getPopupBarPosition() == PopupBarPosition.VERTICAL_SIDES) {
      _balloons.put(PopupBarPosition.LEFT, createPopupBarFigure());
      _balloons.put(PopupBarPosition.RIGHT, createPopupBarFigure());
    } else {
      _balloons.put(getPopupBarPosition(), createPopupBarFigure());
    }

    int priorityActionOffset = ACTION_WIDTH_AND_HEIGHT + ACTION_BUTTON_OFFSET / 2 - 1;
    int iTotal = ACTION_WIDTH_AND_HEIGHT * theList.size() + ACTION_BUTTON_OFFSET * 2;
    if (theList.size() > 2) {
      iTotal += priorityActionOffset;
    }
    for (PopupBarPosition popupBarPosition : _balloons.keySet()) {
      getBalloon().get(popupBarPosition).setSize(iTotal, ACTION_WIDTH_AND_HEIGHT + ACTION_BUTTON_OFFSET * 2);

      int xLoc = ACTION_BUTTON_OFFSET;
      int yLoc = ACTION_BUTTON_OFFSET;

      String entryName = _priorityPaletteEntyMap.get(_toolKey);
      _priorityPopupBarToolEntry = _nameToEntryMap.get(entryName);
      if (_priorityPopupBarToolEntry == null) {
        reorderPopupBarEntries(theList.get(0));
      }
      if (_priorityPopupBarToolEntry != null && theList.size() > 2) {
        PopupBarEntryLabel b = new PopupBarEntryLabel(_priorityPopupBarToolEntry);

        Rectangle r1 = new Rectangle();
        r1.setLocation(xLoc, yLoc);
        xLoc += priorityActionOffset;
        r1.setSize(ACTION_WIDTH_AND_HEIGHT, ACTION_WIDTH_AND_HEIGHT);

        Label l = new Label();
        l.setText(" " + _priorityPopupBarToolEntry.getLabel() + ": " + _priorityPopupBarToolEntry.getDescription() + " "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        b.setToolTip(l);
        b.setPreferredSize(ACTION_WIDTH_AND_HEIGHT, ACTION_WIDTH_AND_HEIGHT);
        b.setBounds(r1);

        getBalloon().get(popupBarPosition).add(b);

        b.addMouseMotionListener(this);
        b.addMouseListener(this.myMouseKeyListener);

        RoundedRectangle separator = new RoundedRectangle();
        separator.setLocation(new Point(xLoc, yLoc));
        separator.setSize(2, ACTION_WIDTH_AND_HEIGHT);
        separator.setCornerDimensions(new Dimension(1, 1));
        separator.setForegroundColor(ColorConstants.lightGray);
        separator.setBackgroundColor(ColorConstants.lightGray);

        getBalloon().get(popupBarPosition).add(separator);
        xLoc += ACTION_BUTTON_OFFSET / 2 + 1;
      }

      for (Iterator<?> iter = theList.iterator(); iter.hasNext();) {
        PaletteEntry theDesc = (PaletteEntry) iter.next();
        PopupBarEntryLabel b = new PopupBarEntryLabel(theDesc);

        Rectangle r1 = new Rectangle();
        r1.setLocation(xLoc, yLoc);
        xLoc += ACTION_WIDTH_AND_HEIGHT;
        r1.setSize(ACTION_WIDTH_AND_HEIGHT, ACTION_WIDTH_AND_HEIGHT);

        Label l = new Label();
        l.setText(" " + theDesc.getLabel() + ": " + theDesc.getDescription() + " "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        b.setToolTip(l);
        b.setPreferredSize(ACTION_WIDTH_AND_HEIGHT, ACTION_WIDTH_AND_HEIGHT);
        b.setBounds(r1);

        getBalloon().get(popupBarPosition).add(b);

        b.addMouseMotionListener(this);
        b.addMouseListener(this.myMouseKeyListener);

      }
    }
  }

  protected boolean isPopupBarShowing() {
    return !getBalloon().isEmpty();
  }

  private Map<PopupBarPosition, IFigure> getBalloon() {
    return _balloons;
  }

  protected IFigure createPopupBarFigure() {
    //    return new RoundedRectangleWithTail();
    RoundedRectangle roundedRectangle = new RoundedRectangle();
    roundedRectangle.setFill(true);
    roundedRectangle.setBackgroundColor(ColorConstants.buttonLightest);
    roundedRectangle.setForegroundColor(ColorConstants.lightGray);
    roundedRectangle.setVisible(true);
    roundedRectangle.setEnabled(true);
    roundedRectangle.setOpaque(true);
    roundedRectangle.setCornerDimensions(new Dimension(6, 6));
    return roundedRectangle;
  }

  protected void showPopupBar(Point referencePoint) {

    Point internalReferencePoint = referencePoint;
    // already have a one
    if (!getBalloon().isEmpty()/* && getBalloon().getParent() != null */) {
      return;
    }

    if (_popupBarToolEntries.isEmpty()) {
      return; // nothing to show
    }
    initPopupBars();

    for (PopupBarPosition popupBarPosition : _balloons.keySet()) {
      getBalloon().get(popupBarPosition).addMouseMotionListener(this);
      getBalloon().get(popupBarPosition).addMouseListener(myMouseKeyListener);

      // the feedback layer figures do not recieve mouse events so do not use
      // it for popup bars
      IFigure layer = getLayer(LayerConstants.HANDLE_LAYER);
      layer.add(getBalloon().get(popupBarPosition));

      if (internalReferencePoint == null) {
        internalReferencePoint = getHostFigure().getBounds().getCenter();
      }

      Point thePoint = getBalloonPosition(getBalloon().get(popupBarPosition), internalReferencePoint, popupBarPosition);

      getBalloon().get(popupBarPosition).setLocation(thePoint);
    }
    // dismiss the popup bar after a delay
    if (!shouldAvoidHidingPopupBar()) {
      hideDiagramAssistantAfterDelay(getDisappearanceDelay());
    }
  }

  /**
   * getter for the IsDisplayAtMouseHoverLocation flag
   * 
   * @return true or false
   */
  protected PopupBarPosition getPopupBarPosition() {
//    String positionString = getDefault().getPreferenceStore().getString(POPUPBAR_POSITION);
//    PopupBarPosition popupBarPosition = PopupBarPosition.valueOf(positionString);
//    return popupBarPosition;
	  return null;
  }

  /**
   * For editparts that consume the entire viewport, statechart, structure, communication, we want to display the popup bar at the mouse location.
   * 
   * @param referencePoint The reference point which may be used to determine where the diagram assistant should be located. This is most likely the
   *          current mouse location.
   * @param popupBarPosition
   * @return Point
   */
  private Point getBalloonPosition(IFigure balloonForPositioning, Point referencePoint, PopupBarPosition popupBarPosition) {
    Point thePoint = new Point();
    Dimension theoffset = new Dimension();
    Rectangle rcBounds = getHostFigure().getBounds().getCopy();
    switch (popupBarPosition) {
      case MOUSE: {
        thePoint.setLocation(referencePoint);
        getHostFigure().translateToAbsolute(thePoint);
        balloonForPositioning.translateToRelative(thePoint);

        // shift the ballon so it is above the cursor.
        thePoint.y -= getBallonEditPartYOffset() + ACTION_WIDTH_AND_HEIGHT + ACTION_BUTTON_OFFSET * 2;
        thePoint.x += getBallonEditPartXOffset();
        adjustToFitInViewport(balloonForPositioning, thePoint);
        return thePoint;
      }
      case CENTER: {

        getHostFigure().translateToAbsolute(rcBounds);
        balloonForPositioning.translateToRelative(rcBounds);

        theoffset.height = ((rcBounds.height - balloonForPositioning.getBounds().height) / 2);
        theoffset.width = ((rcBounds.width - balloonForPositioning.getBounds().width) / 2);

        break;
      }
      case TOP: {
        getHostFigure().translateToAbsolute(rcBounds);
        balloonForPositioning.translateToRelative(rcBounds);

        theoffset.height = -(Math.abs(getBallonEditPartXOffset()) + balloonForPositioning.getBounds().height);
        theoffset.width = ((rcBounds.width - balloonForPositioning.getBounds().width) / 2);

        break;
      }
      case BOTTOM: {
        getHostFigure().translateToAbsolute(rcBounds);
        balloonForPositioning.translateToRelative(rcBounds);

        theoffset.height = Math.abs(getBallonEditPartXOffset()) + rcBounds.height;
        theoffset.width = ((rcBounds.width - balloonForPositioning.getBounds().width) / 2);

        break;
      }

      case LEFT: {
        getHostFigure().translateToAbsolute(rcBounds);
        balloonForPositioning.translateToRelative(rcBounds);

        theoffset.height = ((rcBounds.height - balloonForPositioning.getBounds().height) / 2);
        theoffset.width = -(Math.abs(getBallonEditPartXOffset()) + balloonForPositioning.getBounds().width);

        break;
      }

      case RIGHT: {
        getHostFigure().translateToAbsolute(rcBounds);
        balloonForPositioning.translateToRelative(rcBounds);

        theoffset.height = ((rcBounds.height - balloonForPositioning.getBounds().height) / 2);
        theoffset.width = Math.abs(getBallonEditPartXOffset()) + rcBounds.width;

        break;
      }

      default: {
        getHostFigure().translateToAbsolute(rcBounds);
        balloonForPositioning.translateToRelative(rcBounds);

        theoffset.height = ((rcBounds.height - balloonForPositioning.getBounds().height) / 2);
        theoffset.width = ((rcBounds.width - balloonForPositioning.getBounds().width) / 2);

      }
    }
    thePoint.x = rcBounds.x + theoffset.width;
    thePoint.y = rcBounds.y + theoffset.height;
    adjustToFitInViewport(balloonForPositioning, thePoint);

    return thePoint;
  }

  /**
   * Uses the balloon location passed in and its size to determine if the balloon will appear outside the viewport. If so, the balloon location will
   * be modified accordingly.
   * 
   * @param balloonForPositioning
   * @param balloonLocation the suggested balloon location passed in and potentially modified when this method completes
   */
  private void adjustToFitInViewport(IFigure balloonForPositioning, Point balloonLocation) {
    Control control = getHost().getViewer().getControl();
    if (control instanceof FigureCanvas) {
      Rectangle viewportRect = ((FigureCanvas) control).getViewport().getClientArea();
      Rectangle balloonRect = new Rectangle(balloonLocation, balloonForPositioning.getSize());

      int yDiff = viewportRect.y - balloonRect.y;
      if (yDiff > 0) {
        // balloon is above the viewport, shift down
        balloonLocation.translate(0, yDiff);
      }

      int yDiffBottom = balloonRect.bottom() - viewportRect.bottom();
      if (yDiffBottom > 0) {
        // balloon is above the viewport, shift down
        balloonLocation.translate(0, -yDiffBottom);
      }

      int xDiff = balloonRect.right() - viewportRect.right();
      if (xDiff > 0) {
        // balloon is to the right of the viewport, shift left
        balloonLocation.translate(-xDiff, 0);
      }

      int xDiffLeft = viewportRect.x - balloonRect.x;
      if (xDiffLeft > 0) {
        // balloon is to the right of the viewport, shift left
        balloonLocation.translate(xDiffLeft, 0);
      }
    }
  }

  private void teardownPopupBar() {
    for (PopupBarPosition popupBarPosition : _balloons.keySet()) {
      getBalloon().get(popupBarPosition).removeMouseMotionListener(this);
      getBalloon().get(popupBarPosition).removeMouseListener(myMouseKeyListener);
      // the feedback layer figures do not recieve mouse events
      IFigure layer = getLayer(LayerConstants.HANDLE_LAYER);
      if (_balloons.get(popupBarPosition).getParent() != null) {
        layer.remove(_balloons.get(popupBarPosition));
      }
    }
    _balloons.clear();

    if (imagesToBeDisposed != null) {
      for (Iterator<?> iter = imagesToBeDisposed.iterator(); iter.hasNext();) {
        ((Image) iter.next()).dispose();
      }
      imagesToBeDisposed.clear();
    }

  }

  protected void hidePopupBar() {
    if (!getBalloon().isEmpty()) {

      teardownPopupBar();
    }

  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramAssistantEditPolicy#showDiagramAssistantAfterDelay(int)
   */
  protected void showDiagramAssistantAfterDelay(int theDelay) {
    // only show the popup bar if it isn't already showing
    if (!isPopupBarShowing()) {
      if (theDelay >= 0) {
        showPopupBarJob.setOriginalMouseLocation(getMouseLocation());
        setOriginMouseLocation(getMouseLocation());
        showPopupBarJob.cancel();
        showPopupBarJob.schedule(theDelay);
      }
    }
  }

  @Override
  public void activate() {
    super.activate();
    IFigure hostFigure = getHostFigure();
    if (hostFigure != null) {
      hostFigure.addMouseListener(this.myMouseKeyListener);
      hostFigure.addFigureListener(this.myOwnerMovedListener);
      Control control = getHost().getViewer().getControl();
      if (control != null) {
        control.addKeyListener(popupBarKeyListener);
      }
      hostFigure.addMouseMotionListener(this);
    }
  }

  @Override
  public void deactivate() {
    IFigure hostFigure = getHostFigure();
    if (hostFigure != null) {
      hostFigure.removeMouseListener(this.myMouseKeyListener);
      hostFigure.removeFigureListener(this.myOwnerMovedListener);
      Control control = getHost().getViewer().getControl();
      if (control != null) {
        control.removeKeyListener(popupBarKeyListener);
      }
      hostFigure.removeMouseMotionListener(this);
      hidePopupBar();
    }
    super.deactivate();

  }

  protected String getPopupBarID() {
    return AbstractPopupBarEditPolicy.class.getName();
  }

  /**
   * Gets the current mouse location. This will be null if the mouse is outside the host and diagram assistant figure.
   * 
   * @return Returns the current mouse location
   */
  protected Point getMouseLocation() {
    return mouseLocation;
  }

  /**
   * Sets the current mouse location. If set to null, this implies that the mouse is outside the host and diagram assistant figure.
   * 
   * @param mouseLocationParam the current mouse location
   */
  protected void setMouseLocation(Point mouseLocationParam) {
    this.mouseLocation = mouseLocationParam;
  }

  /**
   * Sets the flag to indicate that the diagram assistant should not be hidden.
   * 
   * @param avoidHidingDiagramAssistantParam Flag to indicate that the diagram assistant should not be hidden
   */
  protected void setAvoidHidingDiagramAssistant(boolean avoidHidingDiagramAssistantParam) {
    this.avoidHidingDiagramAssistant = avoidHidingDiagramAssistantParam;
  }

  /**
   * Returns true if the diagram assistant should not be hidden; false otherwise.
   * 
   * @return true if the diagram assistant should not be hidden; false otherwise.
   */
  protected boolean shouldAvoidHidingPopupBar() {
    return avoidHidingDiagramAssistant;
  }

  protected int getBallonEditPartXOffset() {
	  return 5;
//    return GenericDiagramFrameworkPlugin.getDefault().getPreferenceStore().getInt(PopupBarConstants.GENERAL_POPUPBAR_EDIT_PART_X_OFFSET);
  }

  protected int getBallonEditPartYOffset() {
	  return 6;
//    return GenericDiagramFrameworkPlugin.getDefault().getPreferenceStore().getInt(PopupBarConstants.GENERAL_POPUPBAR_EDIT_PART_Y_OFFSET);
  }

  /**
   * Gets the amount of time to wait before showing the diagram assistant.
   * 
   * @return the time to wait in milliseconds
   */
  protected int getAppearanceDelay() {
	  return 2;
//    return GenericDiagramFrameworkPlugin.getDefault().getPreferenceStore().getInt(PopupBarConstants.GENERAL_POPUPBAR_APPEARANCE_DELAY);
  }

  /**
   * Gets the amount of time to wait before hiding the diagram assistant after it has been made visible.
   * 
   * @return the time to wait in milliseconds
   */
  protected int getDisappearanceDelay() {
	  return 5;
//    return GenericDiagramFrameworkPlugin.getDefault().getPreferenceStore().getInt(PopupBarConstants.GENERAL_POPUPBAR_DISAPPEARANCE_DELAY);
  }

  /**
   * Gets the amount of time to wait before hiding the diagram assistant after the user has moved the mouse outside of the editpart.
   * 
   * @return the time to wait in milliseconds
   */
  protected int getDisappearanceDelayUponExit() {
	  return 2;
//    return GenericDiagramFrameworkPlugin.getDefault().getPreferenceStore().getInt(PopupBarConstants.GENERAL_POPUPBAR_DISAPPEARANCE_DELAY_UPON_EXIT);
  }

  /**
   * Hides the diagram assistant after a certain amount of time has passed.
   * 
   * @param delay the delay in milliseconds
   */
  protected void hideDiagramAssistantAfterDelay(int delay) {
    if (isPopupBarShowing() && delay >= 0) {
      hidePopupBarJob.cancel();
      hidePopupBarJob.schedule(delay);
    }
  }

  /**
   * Checks if the host editpart is editable.
   * 
   * @return True if the host is editable; false otherwise.
   */
  private boolean isHostEditable() {
	  return true;
  }

  /**
   * Checks if the diagram part is active.
   * 
   * @return True if the diagram part is active; false otherwise.
   */
  private boolean isDiagramPartActive() {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    if (window != null) {
      IWorkbenchPage page = window.getActivePage();
      if (page != null) {
        //TODO Correct Check for working part active??
        IWorkbenchPart activePart = page.getActivePart();
        if (activePart != null) {
          return true;
        }
      }
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.draw2d.MouseMotionListener#mouseEntered(org.eclipse.draw2d.MouseEvent)
   */
  public void mouseEntered(MouseEvent me) {
    setMouseLocation(me.getLocation());
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.draw2d.MouseMotionListener#mouseExited(org.eclipse.draw2d.MouseEvent)
   */
  public void mouseExited(MouseEvent me) {
    setMouseLocation(null);
    hideDiagramAssistantAfterDelay(getDisappearanceDelayUponExit());
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.draw2d.MouseMotionListener#mouseDragged(org.eclipse.draw2d.MouseEvent)
   */
  public void mouseDragged(MouseEvent me) {
    // do nothing
  }

  private void setOriginMouseLocation(Point originMousePosition) {
    Point where = translateToAbsolute(originMousePosition);
    _originMouseLocation = where;
  }

  private Point getOriginMouseLocation() {
    return _originMouseLocation;
  }

  protected Point translateToAbsolute(Point location) {
    Point where = new Point(location);
    IFigure figure = ((AbstractGraphicalEditPart) getHost()).getFigure();
    figure.translateToAbsolute(where);
    return where;
  }

}
