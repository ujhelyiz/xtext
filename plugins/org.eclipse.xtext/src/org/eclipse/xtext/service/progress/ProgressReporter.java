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
public class ProgressReporter implements IProgressReporter {

	protected final IProgressSink sink;
	private final int nesting;
	
	private double remainingTicksInSink;
	private boolean done;
	private IProgressReporter currentChild;
	private int remainingLocalTicks;
	private double factor;

	protected ProgressReporter(IProgressSink sink, int nesting, int ticksToAnnounceInSink, int remainingLocalTicks) {
		this.sink = sink;
		this.nesting = nesting;
		this.remainingTicksInSink = ticksToAnnounceInSink < 1 ? 0 : ticksToAnnounceInSink;
		setRemaining(remainingLocalTicks);
		sink.begin(nesting);
	}
	
	@Override
	public boolean isCanceled() {
		return sink.isCanceled();
	}

	@Override
	public void done() {
		if (done) {
			return;
		}
		markChildDone();
		worked(remainingLocalTicks);
		sink.done(nesting);
		done = true;
	}

	private void markChildDone() {
		if (currentChild != null) {
			currentChild.done();
			currentChild = null;
		}
	}
	
	@Override
	public void cancel() throws OperationCanceledError {
		sink.cancel();
		throw sink.newOperationCanceledError();
	}

	@Override
	public void checkCanceled() throws OperationCanceledError {
		if (isCanceled()) {
			throw sink.newOperationCanceledError();
		}
	}

	@Override
	public void worked(int ticks) {
		markChildDone();
		int ticksToAnnounceInSink = computeTicksToAnnounceInSink(ticks); 
		if (ticksToAnnounceInSink > 0) {
			// do announce tick if necessary
			sink.worked(ticksToAnnounceInSink);
		}
	}

	protected int computeTicksToAnnounceInSink(int localTicks) {
		if (localTicks <= 0)
			return 0;
		// subtract from local ticks
		remainingLocalTicks -= localTicks;
		// convert to ticks to announce
		double ticksToAnnounce = localTicks * factor;
		// check if we need to announce a tick
		double remainingTicksInSink = this.remainingTicksInSink;
		this.remainingTicksInSink = remainingTicksInSink - ticksToAnnounce;
		int ticksToAnnounceInSink = (int) (Math.floor(this.remainingTicksInSink) - Math.floor(remainingTicksInSink));
		return ticksToAnnounceInSink;
	}

	@Override
	public IProgressReporter newChild(int ticks) {
		if (done) {
			return new NullProgressReporter(sink);
		}
		markChildDone();
		return new ProgressReporter(sink, nesting+1, computeTicksToAnnounceInSink(ticks), 1);
	}

	@Override
	public void setRemaining(int ticks) {
		markChildDone();
		remainingLocalTicks = Math.max(0, ticks);
		if (remainingTicksInSink < 1) {
			factor = 0;
		} else {
			remainingLocalTicks = Math.max(0, ticks);
			factor = remainingLocalTicks / remainingTicksInSink; 
		}
	}

	@Override
	public void setDescription(CharSequence description) {
		markChildDone();
		sink.setDescription(nesting, description);
	}

}
