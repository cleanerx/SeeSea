/**
 * <copyright>
Copyright (c) 2010-2012, Jens Kübler
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * </copyright>
 *
 * $Id$
 */
package net.sf.seesea.model.core.diagramInterchange;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.sf.seesea.model.core.diagramInterchange.DiagramInterchangeFactory
 * @model kind="package"
 * @generated
 */
public interface DiagramInterchangePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "diagramInterchange";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "diagramInterchange";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "diagramInterchange";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DiagramInterchangePackage eINSTANCE = net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl.init();

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.GraphicalElementImpl <em>Graphical Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.GraphicalElementImpl
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getGraphicalElement()
	 * @generated
	 */
	int GRAPHICAL_ELEMENT = 4;

	/**
	 * The number of structural features of the '<em>Graphical Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.GraphicalNodeImpl <em>Graphical Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.GraphicalNodeImpl
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getGraphicalNode()
	 * @generated
	 */
	int GRAPHICAL_NODE = 0;

	/**
	 * The feature id for the '<em><b>Represents</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_NODE__REPRESENTS = GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Graphical Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_NODE_FEATURE_COUNT = GRAPHICAL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.GraphicalEdgeImpl <em>Graphical Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.GraphicalEdgeImpl
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getGraphicalEdge()
	 * @generated
	 */
	int GRAPHICAL_EDGE = 1;

	/**
	 * The number of structural features of the '<em>Graphical Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_EDGE_FEATURE_COUNT = GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.GraphicalContainerImpl <em>Graphical Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.GraphicalContainerImpl
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getGraphicalContainer()
	 * @generated
	 */
	int GRAPHICAL_CONTAINER = 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_CONTAINER__CHILDREN = GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Graphical Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPHICAL_CONTAINER_FEATURE_COUNT = GRAPHICAL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramImpl
	 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getDiagram()
	 * @generated
	 */
	int DIAGRAM = 3;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__CHILDREN = GRAPHICAL_CONTAINER__CHILDREN;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = GRAPHICAL_CONTAINER_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.diagramInterchange.GraphicalNode <em>Graphical Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphical Node</em>'.
	 * @see net.sf.seesea.model.core.diagramInterchange.GraphicalNode
	 * @generated
	 */
	EClass getGraphicalNode();

	/**
	 * Returns the meta object for the reference '{@link net.sf.seesea.model.core.diagramInterchange.GraphicalNode#getRepresents <em>Represents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Represents</em>'.
	 * @see net.sf.seesea.model.core.diagramInterchange.GraphicalNode#getRepresents()
	 * @see #getGraphicalNode()
	 * @generated
	 */
	EReference getGraphicalNode_Represents();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.diagramInterchange.GraphicalEdge <em>Graphical Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphical Edge</em>'.
	 * @see net.sf.seesea.model.core.diagramInterchange.GraphicalEdge
	 * @generated
	 */
	EClass getGraphicalEdge();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.diagramInterchange.GraphicalContainer <em>Graphical Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphical Container</em>'.
	 * @see net.sf.seesea.model.core.diagramInterchange.GraphicalContainer
	 * @generated
	 */
	EClass getGraphicalContainer();

	/**
	 * Returns the meta object for the reference list '{@link net.sf.seesea.model.core.diagramInterchange.GraphicalContainer#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see net.sf.seesea.model.core.diagramInterchange.GraphicalContainer#getChildren()
	 * @see #getGraphicalContainer()
	 * @generated
	 */
	EReference getGraphicalContainer_Children();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.diagramInterchange.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see net.sf.seesea.model.core.diagramInterchange.Diagram
	 * @generated
	 */
	EClass getDiagram();

	/**
	 * Returns the meta object for class '{@link net.sf.seesea.model.core.diagramInterchange.GraphicalElement <em>Graphical Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graphical Element</em>'.
	 * @see net.sf.seesea.model.core.diagramInterchange.GraphicalElement
	 * @generated
	 */
	EClass getGraphicalElement();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DiagramInterchangeFactory getDiagramInterchangeFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.GraphicalNodeImpl <em>Graphical Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.GraphicalNodeImpl
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getGraphicalNode()
		 * @generated
		 */
		EClass GRAPHICAL_NODE = eINSTANCE.getGraphicalNode();

		/**
		 * The meta object literal for the '<em><b>Represents</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICAL_NODE__REPRESENTS = eINSTANCE.getGraphicalNode_Represents();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.GraphicalEdgeImpl <em>Graphical Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.GraphicalEdgeImpl
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getGraphicalEdge()
		 * @generated
		 */
		EClass GRAPHICAL_EDGE = eINSTANCE.getGraphicalEdge();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.GraphicalContainerImpl <em>Graphical Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.GraphicalContainerImpl
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getGraphicalContainer()
		 * @generated
		 */
		EClass GRAPHICAL_CONTAINER = eINSTANCE.getGraphicalContainer();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPHICAL_CONTAINER__CHILDREN = eINSTANCE.getGraphicalContainer_Children();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramImpl
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '{@link net.sf.seesea.model.core.diagramInterchange.impl.GraphicalElementImpl <em>Graphical Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.GraphicalElementImpl
		 * @see net.sf.seesea.model.core.diagramInterchange.impl.DiagramInterchangePackageImpl#getGraphicalElement()
		 * @generated
		 */
		EClass GRAPHICAL_ELEMENT = eINSTANCE.getGraphicalElement();

	}

} //DiagramInterchangePackage
