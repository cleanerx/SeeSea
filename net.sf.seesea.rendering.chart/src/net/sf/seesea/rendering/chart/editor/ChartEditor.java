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
package net.sf.seesea.rendering.chart.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.seesea.model.core.IModelExtractor;
import net.sf.seesea.model.core.ModelObject;
import net.sf.seesea.model.core.geo.GeoFactory;
import net.sf.seesea.model.core.geo.GeoPosition;
import net.sf.seesea.model.core.geo.osm.OsmFactory;
import net.sf.seesea.model.core.geo.osm.World;
import net.sf.seesea.model.util.GeoParser;
import net.sf.seesea.rendering.chart.SeeSeaUIActivator;
import net.sf.seesea.rendering.chart.editpart.MouseWheelZoomHandler;
import net.sf.seesea.rendering.chart.editpart.ScalableZoomableRootEditPart;
import net.sf.seesea.rendering.chart.editpart.SeeSeaGraphicalPartFactory;
import net.sf.seesea.rendering.chart.rulers.RulerComposite;
import net.sf.seesea.rendering.chart.view.GeospatialGraphicalViewer;
import net.sf.seesea.tileservice.ITileProvider;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.draw2d.parts.Thumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ToggleGridAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * An editor that renders tiles and may additionally edit any map related stuff
 */
public class ChartEditor extends GraphicalEditorWithFlyoutPalette implements ISelectionProvider {

	/**
	 * 
	 */
	private static final String NET_SF_SEESEA_UI_CONTEXT_EDITOR = "net.sf.seesea.ui.context.editor"; //$NON-NLS-1$
	
	private RulerComposite rulerComp;

	private MapOverview outlinePage;

	private PaletteRoot palette;

	private final Collection<ISelectionChangedListener> selectionChangedListeners;

	private ISelection editorSelection;

//	private final ServiceTracker editingDomainServiceTracker;

	public ChartEditor() {
		setEditDomain(new DefaultEditDomain(this));
		selectionChangedListeners = new ArrayList<ISelectionChangedListener>();
		editorSelection = StructuredSelection.EMPTY;
//		editingDomainServiceTracker = new ServiceTracker(SeeSeaUIActivator.getDefault().getBundle().getBundleContext(), IEditingDomainProvider.class.getName(), null);
//		editingDomainServiceTracker.open();
	}
	
	

//	public EditingDomain getEditingDomain() {
//		return (EditingDomain) editingDomainServiceTracker.getService();
//	}

	@Override
	protected FlyoutPreferences getPalettePreferences() {
		FlyoutPreferences palettePreferences = super.getPalettePreferences();
		if(!((MapEditorInput)getEditorInput()).isShowPalette()) {
			palettePreferences.setPaletteState(8);
		} else {
			palettePreferences.setPaletteState(0);
		}
		return palettePreferences;
	}
	

	@Override
	protected PaletteRoot getPaletteRoot() {
		if(palette == null) {
			palette = SeeSeaPaletteFactory.createPalette();
		}
		return palette;
	}

	@Override
	public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class) {
			outlinePage = new MapOverview(new GeospatialGraphicalViewer());
			return outlinePage;
		} else if (type == IPropertySheetPage.class) {
//			IViewReference[] viewReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPages()[0].getViewReferences();
//			for (IViewReference viewReference : viewReferences) {
//				if(viewReference.getId().equals("net.sf.seesea.model.view.views.MapView")) {
//					return ((ModelView)viewReference.getPart(false)).getAdapter(type);
//				}
//			}
		} else if(IModelExtractor.class.isAssignableFrom(type)) {
			return new EditPart2ModelExtractor();
		}

//		if (type == ZoomManager.class)
//			return getGraphicalViewer().getProperty(ZoomManager.class.toString());
		return super.getAdapter(type);
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
		// 
	}
	
	

	@Override
	protected void setInput(IEditorInput input) {
		if (outlinePage != null) {
			outlinePage.setContents(((MapEditorInput)input).getWorld());
		}
		super.setInput(input);
	}

	@Override
	protected void createGraphicalViewer(Composite parent) {
		rulerComp = new RulerComposite(parent, SWT.NONE);
		GraphicalViewer viewer = new GeospatialGraphicalViewer();
		viewer.createControl(rulerComp);
		setGraphicalViewer(viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
		rulerComp.setGraphicalViewer((ScrollingGraphicalViewer)getGraphicalViewer());
		IContextService contextService = (IContextService) PlatformUI.getWorkbench().getService(IContextService.class);
		contextService.activateContext(NET_SF_SEESEA_UI_CONTEXT_EDITOR);
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GeospatialGraphicalViewer viewer = (GeospatialGraphicalViewer)getGraphicalViewer();
		
		ScalableRootEditPart root = new ScalableZoomableRootEditPart();
		viewer.setRootEditPart(root);
		
		viewer.setEditPartFactory(new SeeSeaGraphicalPartFactory());
		if(((MapEditorInput) getEditorInput()).isShowContextMenu()) {
			ContextMenuProvider provider = new ChartContextMenuProvider(viewer, getActionRegistry());
			viewer.setContextMenu(provider);
		}
		
//	    viewer.addDropTargetListener(new ModelObjectDropTargetListener(viewer));

//		IAction zoomIn = new ZoomInAction(root.getZoomManager());
//		IAction zoomOut = new ZoomOutAction(root.getZoomManager());
//		getActionRegistry().registerAction(zoomIn);
//		getActionRegistry().registerAction(zoomOut);
//		
//		// Zoom
//		ZoomManager manager = (ZoomManager)getGraphicalViewer()
//				.getProperty(ZoomManager.class.toString());
//		if (manager != null)
//			manager.setZoom(getMap().getZoomLevel());
//		// Scroll-wheel Zoom
		getGraphicalViewer().setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE), 
				MouseWheelZoomHandler.SINGLETON);

		IAction showGrid = new ToggleGridAction(getGraphicalViewer());
		getActionRegistry().registerAction(showGrid);

		
	}
	
	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		if(getEditorInput() instanceof MapEditorInput) {
			World world = ((MapEditorInput)getEditorInput()).getWorld();
			getGraphicalViewer().setContents(world);
			rulerComp.setWorld(world);
		} else {
			World world = OsmFactory.eINSTANCE.createWorld();
			world.setZoomLevel(9);
			GeoPosition geoPosition = GeoFactory.eINSTANCE.createGeoPosition();
			geoPosition.setLatitude(GeoParser.parseLatitude(8.4));
			geoPosition.setLongitude(GeoParser.parseLongitude(49.0));
			world.setMapCenterPosition(geoPosition);
			rulerComp.setWorld(world);
		}
	}

	public World getMap() {
		return ((MapEditorInput) getEditorInput()).getWorld();
	}
	
	@Override
	protected Control getGraphicalControl() {
		return rulerComp;
	}

	public GeoPosition getCurrentCenterPosition() {
		GeospatialGraphicalViewer geospatialGraphicalViewer = (GeospatialGraphicalViewer)getGraphicalViewer();
		Point scrollingPosition = geospatialGraphicalViewer.getCenterPosition();
		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference<ITileProvider> serviceReference = bundleContext.getServiceReference(ITileProvider.class);
		ITileProvider tileProvider =  (ITileProvider) bundleContext.getService(serviceReference);
		org.eclipse.swt.graphics.Point tileSize = tileProvider.getTileSize();
		ScalableZoomableRootEditPart scalableZoomableRootEditPart = (ScalableZoomableRootEditPart) geospatialGraphicalViewer.getRootEditPart();
		int zoom = scalableZoomableRootEditPart.getZoom();
		org.eclipse.swt.graphics.Point point = new org.eclipse.swt.graphics.Point(scrollingPosition.x, scrollingPosition.y);
		return tileProvider.getProjection().backproject(point, (1<< zoom) *  tileSize.x);
	}

	/**
	 * @param currentCenterPosition
	 */
	public void setCenterPosition(GeoPosition currentCenterPosition) {
		GeospatialGraphicalViewer geospatialGraphicalViewer = (GeospatialGraphicalViewer)getGraphicalViewer();

		BundleContext bundleContext = SeeSeaUIActivator.getDefault().getBundle().getBundleContext();
		ServiceReference<ITileProvider> serviceReference = bundleContext.getServiceReference(ITileProvider.class);
		ITileProvider tileProvider =  (ITileProvider) bundleContext.getService(serviceReference);
		org.eclipse.swt.graphics.Point tileSize = tileProvider.getTileSize();
		ScalableZoomableRootEditPart scalableZoomableRootEditPart = (ScalableZoomableRootEditPart) geospatialGraphicalViewer.getRootEditPart();
		int zoom = scalableZoomableRootEditPart.getZoom();
//		org.eclipse.swt.graphics.Point point = new org.eclipse.swt.graphics.Point(scrollingPosition.x, scrollingPosition.y);
		org.eclipse.swt.graphics.Point point = tileProvider.getProjection().project(currentCenterPosition, (1<< zoom) *  tileSize.x);
		geospatialGraphicalViewer.setScrollingPosition(new org.eclipse.draw2d.geometry.Point(point.x, point.y));
	}
	
	
	@Override
	public void dispose() {
//		editingDomainServiceTracker.close();
		IContextService contextService = (IContextService) PlatformUI.getWorkbench().getService(IContextService.class);
//		contextService. deactivateContext("net.sf.seesea.ui.context.editor");
		super.dispose();
	}

	@Override
	public GraphicalViewer getGraphicalViewer() {
		return super.getGraphicalViewer();
	}
	
	protected FigureCanvas getEditor(){
		return (FigureCanvas)getGraphicalViewer().getControl();
	}

	public class MapOverview extends ContentOutlinePage implements IAdaptable
	{

	private PageBook pageBook;
	private Control outline;
	private Canvas overview;
	private IAction showOutlineAction, showOverviewAction;
	static final int ID_OUTLINE  = 0;
	static final int ID_OVERVIEW = 1;
	private Thumbnail thumbnail;
	private DisposeListener disposeListener;

	public MapOverview(EditPartViewer viewer){
		super(viewer);
	}
	@Override
	public void init(IPageSite pageSite) {
		super.init(pageSite);
//		ActionRegistry registry = getActionRegistry();
//		IActionBars bars = pageSite.getActionBars();
//		String id = ActionFactory.UNDO.getId();
//		bars.setGlobalActionHandler(id, registry.getAction(id));
//		id = ActionFactory.REDO.getId();
//		bars.setGlobalActionHandler(id, registry.getAction(id));
//		id = ActionFactory.DELETE.getId();
//		bars.setGlobalActionHandler(id, registry.getAction(id));
//		id = IncrementDecrementAction.INCREMENT;
//		bars.setGlobalActionHandler(id, registry.getAction(id));
//		id = IncrementDecrementAction.DECREMENT;
//		bars.setGlobalActionHandler(id, registry.getAction(id));
//		bars.updateActionBars();
	}

	protected void configureOutlineViewer(){
		getViewer().setEditDomain(getEditDomain());
		getViewer().setEditPartFactory(new SeeSeaGraphicalPartFactory());
		
		ScalableRootEditPart root = new ScalableZoomableRootEditPart();
		getViewer().setRootEditPart(root);

//		ContextMenuProvider provider = new LogicContextMenuProvider(getViewer(), getActionRegistry());
//		getViewer().setContextMenu(provider);
//		getSite().registerContextMenu(
//			"org.eclipse.gef.examples.logic.outline.contextmenu", //$NON-NLS-1$
//			provider, getSite().getSelectionProvider());
//		getViewer().setKeyHandler(getCommonKeyHandler());
		getViewer().addDropTargetListener((TransferDropTargetListener)
			new TemplateTransferDropTargetListener(getViewer()));
		IToolBarManager tbm = getSite().getActionBars().getToolBarManager();
		showOutlineAction = new Action() {
			@Override
			public void run() {
				showPage(ID_OUTLINE);
			}
		};
		showOutlineAction.setImageDescriptor(ImageDescriptor.getMissingImageDescriptor());
		showOutlineAction.setToolTipText("Fixme");
		tbm.add(showOutlineAction);
		showOverviewAction = new Action() {
			@Override
			public void run() {
				showPage(ID_OVERVIEW);
			}
		};
		showOverviewAction.setImageDescriptor(ImageDescriptor.getMissingImageDescriptor()); 
		showOverviewAction.setToolTipText("Fixme");
		tbm.add(showOverviewAction);
		showPage(ID_OUTLINE);
	}

	@Override
	public void createControl(Composite parent){
		pageBook = new PageBook(parent, SWT.NONE);
		outline = getViewer().createControl(pageBook);
		overview = new Canvas(pageBook, SWT.NONE);
		pageBook.showPage(outline);
		configureOutlineViewer();
		hookOutlineViewer();
		initializeOutlineViewer();
	}

	@Override
	public void dispose(){
		unhookOutlineViewer();
		if (thumbnail != null) {
			thumbnail.deactivate();
			thumbnail = null;
		}
		super.dispose();
		ChartEditor.this.outlinePage = null;
		outlinePage = null;
	}


	@Override
	public Control getControl() {
		return pageBook;
	}

	protected void hookOutlineViewer(){
		getSelectionSynchronizer().addViewer(getViewer());
	}

	protected void initializeOutlineViewer(){
		setContents(((MapEditorInput)getEditorInput()).getWorld());
	}

	protected void initializeOverview() {
		LightweightSystem lws = new LightweightSystem(overview);
		RootEditPart rep = getGraphicalViewer().getRootEditPart();
		if (rep instanceof ScalableFreeformRootEditPart) {
			ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart)rep;
			thumbnail = new ScrollableThumbnail((Viewport)root.getFigure());
			thumbnail.setBorder(new MarginBorder(3));
			thumbnail.setSource(root.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			getEditor().addDisposeListener(disposeListener);
		}
	}

	public void setContents(Object contents) {
		getViewer().setContents(contents);
	}

	protected void showPage(int id) {
		if (id == ID_OUTLINE) {
			showOutlineAction.setChecked(true);
			showOverviewAction.setChecked(false);
			pageBook.showPage(outline);
			if (thumbnail != null)
				thumbnail.setVisible(false);
		} else if (id == ID_OVERVIEW) {
			if (thumbnail == null)
				initializeOverview();
			showOutlineAction.setChecked(false);
			showOverviewAction.setChecked(true);
			pageBook.showPage(overview);
			thumbnail.setVisible(true);
		}
	}

	protected void unhookOutlineViewer(){
		getSelectionSynchronizer().removeViewer(getViewer());
		if (disposeListener != null && getEditor() != null && !getEditor().isDisposed())
			getEditor().removeDisposeListener(disposeListener);
	}
	public Object getAdapter(Class adapter) {
		return null;
	}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(part == this) {
			return;
		}
			IModelExtractor modelExtractor = (IModelExtractor) part.getAdapter(IModelExtractor.class);
			List<EditPart> editParts = new ArrayList<EditPart>();
			if(modelExtractor != null) {
				List<ModelObject> elements = modelExtractor.getElements(selection);
				for (ModelObject modelObject : elements) {
					Object object = getGraphicalViewer().getEditPartRegistry().get(modelObject);
					if(object instanceof EditPart) {
						editParts.add((EditPart) object);
					}
				}
				getGraphicalViewer().setSelection(new StructuredSelection(editParts));
			}
//
//		super.selectionChanged(part, selection);
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	    selectionChangedListeners.add(listener);
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return editorSelection;
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener(org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
	    selectionChangedListeners.remove(listener);
	}



	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
	    editorSelection = selection;

	    for (ISelectionChangedListener listener : selectionChangedListeners)
	    {
	      listener.selectionChanged(new SelectionChangedEvent(this, selection));
	    }
	}
	
}
