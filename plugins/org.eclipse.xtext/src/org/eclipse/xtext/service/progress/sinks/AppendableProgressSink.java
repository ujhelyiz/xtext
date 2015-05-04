/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service.progress.sinks;

import java.io.IOException;

import org.eclipse.xtext.service.OperationCanceledManager;
import org.eclipse.xtext.util.Strings;

import com.google.common.base.Joiner;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class AppendableProgressSink extends AbstractProgressSink {

	private volatile boolean canceled = false;
	private final Appendable appendable;
	private final Joiner joiner = Joiner.on(" > ").skipNulls();
	private final String newLine;
	
	public AppendableProgressSink(Appendable appendable, OperationCanceledManager operationCanceledManager) {
		this(appendable, Strings.newLine(), operationCanceledManager);
	}
	
	public AppendableProgressSink(Appendable appendable, String newLine, OperationCanceledManager operationCanceledManager) {
		super(operationCanceledManager, NO_TICKS);
		this.appendable = appendable;
		this.newLine = newLine;
	}
	
	@Override
	public boolean isCanceled() {
		return canceled;
	}

	@Override
	public void cancel() {
		canceled = true;
	}

	@Override
	public void setDescription(int nesting, CharSequence description) {
		super.setDescription(nesting, description);
		printDescriptions();
	}

	private void printDescriptions() {
		try {
			joiner.appendTo(appendable, descriptions);
			appendable.append(newLine);
		} catch (IOException e) {
			handleIOException(e);
		}
	}

	protected void handleIOException(IOException e) {
		throw new RuntimeException(e);
	}

	@Override
	public void worked(int ticksToAnnounce) {
		// nothing to do
	}
	
	Appendable getAppendable() {
		return appendable;
	}

}
