/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress;

import org.eclipse.xtext.service.OperationCanceledError;
import org.eclipse.xtext.service.progress.sinks.IProgressSink;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class NullProgressReporter implements IProgressReporter {

	private IProgressSink sink;

	public NullProgressReporter(IProgressSink sink) {
		this.sink = sink;
	}
	
	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void done() {
	}

	@Override
	public void cancel() throws OperationCanceledError {
		throw sink.newOperationCanceledError();
	}

	@Override
	public void checkCanceled() throws OperationCanceledError {
	}

	@Override
	public void worked(int steps) {
	}

	@Override
	public IProgressReporter newChild(int steps) {
		return this;
	}

	@Override
	public void setRemaining(int steps) {
	}

	@Override
	public void setDescription(CharSequence description) {
	}

}
