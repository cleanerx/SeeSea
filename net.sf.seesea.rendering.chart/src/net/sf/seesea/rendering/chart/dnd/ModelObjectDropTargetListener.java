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
package net.sf.seesea.rendering.chart.dnd;
//package net.sf.seesea.ui.dnd;
//
//import java.util.List;
//
//import net.sf.seesea.model.core.ModelObject;
//import net.sf.seesea.model.view.dnd.ModelObjectTransfer;
//
//import org.eclipse.draw2d.geometry.Point;
//import org.eclipse.gef.EditPartViewer;
//import org.eclipse.gef.Request;
//import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
//import org.eclipse.swt.dnd.DND;
//import org.eclipse.swt.dnd.DropTargetEvent;
//
//public class ModelObjectDropTargetListener extends AbstractTransferDropTargetListener {
//
//  public ModelObjectDropTargetListener(EditPartViewer viewer) {
//    super(viewer, ModelObjectTransfer.getInstance());
//    setEnablementDeterminedByCommand(true);
//  }
//
//
///*
//   * (non-Javadoc)
//   * 
//   * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#createTargetRequest()
//   */
//  @Override
//  protected Request createTargetRequest() {
//	  return new ModelObjectsDropRequest(getModelObjects());
////	  r
////	  CreateRequest request = new CreateRequest();
////	   request.setFactory(new CreationFactory() {
////		
////		public Object getObjectType() {
////			return GraphicalNode.class;
////		}
////		
////		public Object getNewObject() {
////			Temperature temperature = PhysxFactory.eINSTANCE.createTemperature();
////			temperature.setValue(10.0);
////			
////			((Instruments) getModelObjects().iterator().next()).setWaterTemperature(temperature);
////			return temperature;
////		}
////	});
////	 return request;
//
//	  
////    return new ModelObjectsDropRequest(getModelObjects());
//  }
//
//  private List<ModelObject> getModelObjects() {
////    return ((ModelObjectTransfer) getTransfer()).getObjects();
//	  return null;
//  }
//
//  @Override
//  protected void updateTargetRequest() {
//    if (getCurrentEvent().detail == DND.DROP_DEFAULT) {
//      getCurrentEvent().detail = DND.DROP_COPY;
//    }
//    Point dropLocation = getDropLocation();
//    Request targetRequest = getTargetRequest();
//      targetRequest.setType("DropModelObjects");
//      updateTargetEditPart();
////      if (getTargetEditPart() != null) {
////        SnapToHelper snapToHelper = (SnapToHelper) getTargetEditPart().getAdapter(SnapToHelper.class);
////        if (snapToHelper != null) {
////          Rectangle bounds = new Rectangle(dropLocation, dropLocation);
////          PrecisionRectangle baseRect = new PrecisionRectangle(bounds);
////          PrecisionRectangle result = baseRect.getPreciseCopy();
////          snapToHelper.snapRectangle(targetRequest, PositionConstants.NSEW, baseRect, result);
////          dropLocation = result.getLocation();
////        }
////      }
//      ((ModelObjectsDropRequest) getTargetRequest()).setLocation(dropLocation);
//    }
//
//  @Override
//  public void dragEnter(DropTargetEvent event) {
//    if (event.detail == DND.DROP_DEFAULT) {
//      event.detail = DND.DROP_COPY;
//    }
//    super.dragEnter(event);
//  }
//
//  @Override
//  public void dragOperationChanged(DropTargetEvent event) {
//    if (event.detail == DND.DROP_DEFAULT) {
//      event.detail = DND.DROP_COPY;
//    }
//    super.dragOperationChanged(event);
//  }
//
//  @Override
//  public void drop(DropTargetEvent event) {
//    if (event.detail == DND.DROP_DEFAULT) {
//      event.detail = DND.DROP_COPY;
//    }
//    super.drop(event);
//  }
//
//
//}
