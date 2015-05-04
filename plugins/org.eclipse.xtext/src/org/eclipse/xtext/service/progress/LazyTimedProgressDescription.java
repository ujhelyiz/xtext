/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class LazyTimedProgressDescription extends AbstractProgressDescription {

	private CharSequence base;
	private Stopwatch stopwatch;
	private int threshold;
	private TimeUnit unit;

	public LazyTimedProgressDescription(CharSequence base, int threshold, TimeUnit unit) {
		this.base = base;
		this.threshold = threshold;
		this.unit = unit;
		stopwatch = Stopwatch.createStarted();
	}
	
	@Override
	public String toString() {
		if (stopwatch.elapsed(unit) > threshold) {
			return String.format("%s (%s)", base, stopwatch.toString());
		}
		return base.toString();
	}

}
