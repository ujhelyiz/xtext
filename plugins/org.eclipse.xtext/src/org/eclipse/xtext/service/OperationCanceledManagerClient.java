/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.service;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @since 2.9
 * @noreference This class is not intended to be referenced by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class OperationCanceledManagerClient {

	private OperationCanceledManager operationCanceledManager;

	protected OperationCanceledManagerClient(OperationCanceledManager operationCanceledManager) {
		this.operationCanceledManager = operationCanceledManager;
	}
	
	protected OperationCanceledError newOperationCanceledError() {
		return new OperationCanceledError(operationCanceledManager.getPlatformSpecificOperationCanceledException());
	}
	
	protected OperationCanceledManager getOperationCanceledManager() {
		return operationCanceledManager;
	}
	
}
