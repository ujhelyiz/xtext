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
public abstract class AbstractProgressReporterProvider<
		Sink extends IProgressSink,
		PlatformSpecific,
		PlatformSpecificException extends RuntimeException> implements IProgressReporterProvider<PlatformSpecific, PlatformSpecificException> {

	private final Class<PlatformSpecificException> exceptionClass;

	protected AbstractProgressReporterProvider(Class<PlatformSpecificException> exceptionClass) {
		this.exceptionClass = exceptionClass;
	}
	
	@Override
	public IProgressReporter toProgressReporter(PlatformSpecific platformSpecific) {
		return new ProgressReporter(createSink(platformSpecific), 0, IProgressSink.TOTAL_TICKS_IN_SINK, 1);
	}
	
	protected abstract Sink createSink(PlatformSpecific platformSpecific);

	@SuppressWarnings("unchecked")
	protected Sink getSink(IProgressReporter reporter) {
		if (reporter instanceof ProgressReporter) {
			return (Sink) ((ProgressReporter) reporter).sink;
		}
		return null;
	}

	@Override
	public <Result> Result computeWithProgress(PlatformSpecific platformReporter,
			IProgressReporterProvider.ComputationWithProgress<? extends Result> op) throws PlatformSpecificException {
		try {
			return op.compute(toProgressReporter(platformReporter));
		} catch(OperationCanceledError e) {
			throw exceptionClass.cast(e.getWrapped());
		}
	}
	
	@Override
	public void runWithProgress(PlatformSpecific platformReporter,
			IProgressReporterProvider.OperationWithProgress op) throws PlatformSpecificException {
		try {
			op.run(toProgressReporter(platformReporter));
		} catch(OperationCanceledError e) {
			throw exceptionClass.cast(e.getWrapped());
		}
	}

}
