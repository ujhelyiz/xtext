/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress;

import org.eclipse.xtext.service.OperationCanceledError;

/**
 * Converts platform specific implementations to {@link IProgressReporter}.
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public interface IProgressReporterProvider<PlatformSpecific, PlatformSpecificException extends Exception> {

	IProgressReporter toProgressReporter(PlatformSpecific platformSpecific);
	
	PlatformSpecific toPlatformSpecific(IProgressReporter reporter);
	
	<Result> Result computeWithProgress(PlatformSpecific platformReporter, ComputationWithProgress<? extends Result> op) throws PlatformSpecificException;
	
	interface ComputationWithProgress<Result> {
		
		Result compute(IProgressReporter reporter) throws OperationCanceledError;
		
	}
	
	void runWithProgress(PlatformSpecific platformReporter, OperationWithProgress op) throws PlatformSpecificException;
	
	interface OperationWithProgress {
		
		void run(IProgressReporter reporter) throws OperationCanceledError;
		
	}
	
	
}
