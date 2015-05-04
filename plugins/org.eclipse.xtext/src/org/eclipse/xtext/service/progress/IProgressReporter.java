/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress;

import org.eclipse.xtext.service.OperationCanceledError;
import org.eclipse.xtext.util.CancelIndicator;

/**
 * Platform independent facicility to report progress. Can be backed
 * by Eclipse's progress monitor, IntelliJ's ProgressIndicator or some
 * command line progress reporter.
 * 
 * Platform specific implementations are converted by means of specific
 * implementations of the {@link IProgressReporterProvider}.
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public interface IProgressReporter extends CancelIndicator {

	/**
	 * Notifies that this reporter is done. Can be called more than once.
	 * Subsequent calls don't have any effects. After a reporter is {@link #done()}
	 * all mutating operations are ignored.
	 */
	void done();

	/**
	 * Marks this reporter as cancelled and throws {@link OperationCanceledError} to abort
	 * running computation. Can be called from within the runnign operation to bail our based
	 * on some internal cancellation criteria.
	 */
	void cancel() throws OperationCanceledError;
	
	/**
	 * Throws an {@link OperationCanceledError} if the current operation was cancelled.
	 */
	void checkCanceled() throws OperationCanceledError;
	
	/**
	 * Announce that a certain number of ticks have been finished.
	 */
	void worked(int steps);
	
	/**
	 * Creates a new sub-reporter that will consume the number of steps in total.
	 * The child will be marked as {@link #done()} automatically if the parent is changed
	 * afterwards.
	 */
	IProgressReporter newChild(int steps);
	
	/**
	 * Sets the remaining work for this reporter. Can be called arbitrarily often to adjust
	 * the number of ticks that are expected.
	 */
	void setRemaining(int steps);
	
	/**
	 * Sets the description of the currently running operation.
	 * 
	 * @see TimedProgressDescription
	 * @see LazyTimedProgressDescription
	 */
	void setDescription(CharSequence description);
	
}
