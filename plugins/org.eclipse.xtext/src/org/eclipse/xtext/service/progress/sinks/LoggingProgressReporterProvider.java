/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress.sinks;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.xtext.service.OperationCanceledManager;
import org.eclipse.xtext.service.progress.AbstractProgressReporterProvider;
import org.eclipse.xtext.service.progress.IProgressReporter;

import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class LoggingProgressReporterProvider extends AbstractProgressReporterProvider<AppendableProgressSink, Logger, OperationCanceledException> {

	@Inject
	private OperationCanceledManager operationCanceledManager;
	
	public LoggingProgressReporterProvider() {
		super(OperationCanceledException.class);
	}
	
	@Override
	public Logger toPlatformSpecific(IProgressReporter reporter) {
		AppendableProgressSink sink = getSink(reporter);
		return ((AppendableLoggerAdapter)sink.getAppendable()).logger;
	}

	@Override
	protected AppendableProgressSink createSink(Logger platformSpecific) {
		return new AppendableProgressSink(new AppendableLoggerAdapter(platformSpecific), operationCanceledManager);
	}

	private static final class AppendableLoggerAdapter implements Appendable {

		private Logger logger;

		AppendableLoggerAdapter(Logger logger) {
			this.logger = logger;
		}
		
		@Override
		public Appendable append(CharSequence csq) throws IOException {
			logger.info(csq);
			return this;
		}

		@Override
		public Appendable append(char c) throws IOException {
			append(String.valueOf(c));
			return this;
		}

		@Override
		public Appendable append(CharSequence csq, int start, int end) throws IOException {
			append(csq.subSequence(start, end));
			return this;
		}
		
	}
	
	
}
