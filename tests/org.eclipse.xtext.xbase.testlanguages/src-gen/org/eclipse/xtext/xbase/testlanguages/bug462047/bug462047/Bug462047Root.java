/**
 */
package org.eclipse.xtext.xbase.testlanguages.bug462047.bug462047;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.xbase.testlanguages.bug462047.bug462047.Bug462047Root#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.xbase.testlanguages.bug462047.bug462047.Bug462047Package#getBug462047Root()
 * @model
 * @generated
 */
public interface Bug462047Root extends EObject
{
  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.xbase.testlanguages.bug462047.bug462047.Bug462047Element}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see org.eclipse.xtext.xbase.testlanguages.bug462047.bug462047.Bug462047Package#getBug462047Root_Elements()
   * @model containment="true"
   * @generated
   */
  EList<Bug462047Element> getElements();

} // Bug462047Root
