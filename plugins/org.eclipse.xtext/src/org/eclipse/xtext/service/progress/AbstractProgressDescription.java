/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
abstract class AbstractProgressDescription implements CharSequence {

	@Override
	public abstract String toString();
	
	@Override
	public char charAt(int index) {
		return toString().charAt(index);
	}

	@Override
	public int length() {
		return toString().length();
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return toString().subSequence(start, end);
	}

}
