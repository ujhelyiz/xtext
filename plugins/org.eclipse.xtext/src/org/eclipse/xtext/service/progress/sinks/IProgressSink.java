/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress.sinks;

import org.eclipse.xtext.service.OperationCanceledError;
import org.eclipse.xtext.util.CancelIndicator;

/**
 * Bridge SPI to provide adapters to platform specific progress reporting.
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public interface IProgressSink extends CancelIndicator {

	int TOTAL_TICKS_IN_SINK = 100000000;
	
	void begin(int nesting);
	
	void done(int nesting);

	void cancel();

	OperationCanceledError newOperationCanceledError();

	void setDescription(int nesting, CharSequence description);

	/**
	 * The number of ticks that have been done.
	 * Will total up to {@link #TOTAL_TICKS_IN_SINK} = {@value #TOTAL_TICKS_IN_SINK}.
	 */
	void worked(int ticks);
	
	int getRemainingWork();

}
