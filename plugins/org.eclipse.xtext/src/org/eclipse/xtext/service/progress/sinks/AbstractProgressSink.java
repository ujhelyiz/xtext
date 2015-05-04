/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress.sinks;

import java.util.LinkedList;

import org.eclipse.xtext.service.OperationCanceledManagerClient;
import org.eclipse.xtext.service.OperationCanceledError;
import org.eclipse.xtext.service.OperationCanceledManager;

import com.google.common.collect.Lists;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public abstract class AbstractProgressSink extends OperationCanceledManagerClient implements IProgressSink {

	protected static final int NO_TICKS = 0;
	
	protected LinkedList<CharSequence> descriptions;
	protected int nestingLevel = -1;
	private int totalTicks;
	
	protected AbstractProgressSink(OperationCanceledManager operationCanceledManager, int totalTicks) {
		super(operationCanceledManager);
		this.totalTicks = totalTicks;
		descriptions = Lists.newLinkedList();
	}

	@Override
	public OperationCanceledError newOperationCanceledError() {
		return super.newOperationCanceledError();
	}
	
	@Override
	public void begin(int nesting) {
		if (nesting == nestingLevel + 1) {
			nestingLevel++;
			descriptions.add(null);
		} else {
			throw new IllegalArgumentException("Unexpected nesting level: " + nesting);
		}
	}

	@Override
	public void setDescription(int nesting, CharSequence description) {
		if (nesting == nestingLevel && nesting >= 0) {
			descriptions.set(nesting, description);
		} else {
			throw new IllegalArgumentException("Unexpected nesting level: " + nesting);
		}
	}
	
	@Override
	public void done(int nesting) {
		if (nesting == nestingLevel && nesting >= 0) {
			descriptions.removeLast();
			nestingLevel--;
		} else {
			throw new IllegalArgumentException("Unexpected nesting level: " + nesting);
		}
	}
	
	@Override
	public int getRemainingWork() {
		return totalTicks;
	}
	
	@Override
	public void worked(int ticks) {
		totalTicks = Math.max(0, totalTicks - Math.max(0, ticks));
	}

}
