/**
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xbase.tests.typesystem;

import com.google.common.base.Objects;
import java.util.List;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.tests.typesystem.ValidatingReassigningResolvedTypes;
import org.eclipse.xtext.xbase.tests.typesystem.ValidatingStackedResolvedTypes;
import org.eclipse.xtext.xbase.typesystem.internal.AbstractTypeExpectation;
import org.eclipse.xtext.xbase.typesystem.internal.ExpressionAwareStackedResolvedTypes;
import org.eclipse.xtext.xbase.typesystem.internal.ResolvedTypes;
import org.eclipse.xtext.xbase.typesystem.internal.StackedResolvedTypes;
import org.eclipse.xtext.xbase.typesystem.internal.TypeData;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.references.LightweightBoundTypeArgument;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.UnboundTypeReference;

@SuppressWarnings("all")
public class ValidatingExpressionAwareResolvedTypes extends ExpressionAwareStackedResolvedTypes {
  public ValidatingExpressionAwareResolvedTypes(final ResolvedTypes parent, final XExpression expression) {
    super(parent, expression);
  }
  
  @Override
  public StackedResolvedTypes pushReassigningTypes() {
    return new ValidatingReassigningResolvedTypes(this);
  }
  
  @Override
  public StackedResolvedTypes pushTypes() {
    return new ValidatingStackedResolvedTypes(this);
  }
  
  @Override
  public ExpressionAwareStackedResolvedTypes pushTypes(final XExpression context) {
    return new ValidatingExpressionAwareResolvedTypes(this, context);
  }
  
  @Override
  public void setType(final JvmIdentifiableElement identifiable, final LightweightTypeReference reference) {
    ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
    boolean _isOwnedBy = reference.isOwnedBy(_referenceOwner);
    boolean _not = (!_isOwnedBy);
    if (_not) {
      throw new IllegalArgumentException("reference is not owned by this resolved types");
    }
    super.setType(identifiable, reference);
  }
  
  @Override
  public void reassignType(final JvmIdentifiableElement identifiable, final LightweightTypeReference reference) {
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(reference, null));
    if (!_notEquals) {
      _and = false;
    } else {
      ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
      boolean _isOwnedBy = reference.isOwnedBy(_referenceOwner);
      boolean _not = (!_isOwnedBy);
      _and = _not;
    }
    if (_and) {
      throw new IllegalArgumentException("reference is not owned by this resolved types");
    }
    super.reassignType(identifiable, reference);
  }
  
  @Override
  public void acceptHint(final Object handle, final LightweightBoundTypeArgument boundTypeArgument) {
    boolean _and = false;
    LightweightTypeReference _typeReference = boundTypeArgument.getTypeReference();
    boolean _notEquals = (!Objects.equal(_typeReference, null));
    if (!_notEquals) {
      _and = false;
    } else {
      LightweightTypeReference _typeReference_1 = boundTypeArgument.getTypeReference();
      ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
      boolean _isOwnedBy = _typeReference_1.isOwnedBy(_referenceOwner);
      boolean _not = (!_isOwnedBy);
      _and = _not;
    }
    if (_and) {
      throw new IllegalArgumentException("reference is not owned by this resolved types");
    }
    super.acceptHint(handle, boundTypeArgument);
  }
  
  @Override
  protected List<LightweightBoundTypeArgument> getHints(final Object handle) {
    final List<LightweightBoundTypeArgument> result = super.getHints(handle);
    final Procedure1<LightweightBoundTypeArgument> _function = new Procedure1<LightweightBoundTypeArgument>() {
      @Override
      public void apply(final LightweightBoundTypeArgument it) {
        boolean _and = false;
        LightweightTypeReference _typeReference = it.getTypeReference();
        boolean _notEquals = (!Objects.equal(_typeReference, null));
        if (!_notEquals) {
          _and = false;
        } else {
          LightweightTypeReference _typeReference_1 = it.getTypeReference();
          ITypeReferenceOwner _referenceOwner = ValidatingExpressionAwareResolvedTypes.this.getReferenceOwner();
          boolean _isOwnedBy = _typeReference_1.isOwnedBy(_referenceOwner);
          boolean _not = (!_isOwnedBy);
          _and = _not;
        }
        if (_and) {
          throw new IllegalArgumentException("reference is not owned by this resolved types");
        }
      }
    };
    IterableExtensions.<LightweightBoundTypeArgument>forEach(result, _function);
    return result;
  }
  
  @Override
  public LightweightTypeReference acceptType(final XExpression expression, final AbstractTypeExpectation expectation, final LightweightTypeReference type, final boolean returnType, final int hints) {
    LightweightTypeReference _xblockexpression = null;
    {
      ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
      boolean _isOwnedBy = expectation.isOwnedBy(_referenceOwner);
      boolean _not = (!_isOwnedBy);
      if (_not) {
        throw new IllegalArgumentException("expectation is not owned by this resolved types");
      }
      ITypeReferenceOwner _referenceOwner_1 = this.getReferenceOwner();
      boolean _isOwnedBy_1 = type.isOwnedBy(_referenceOwner_1);
      boolean _not_1 = (!_isOwnedBy_1);
      if (_not_1) {
        throw new IllegalArgumentException("type is not owned by this resolved types");
      }
      _xblockexpression = super.acceptType(expression, expectation, type, returnType, hints);
    }
    return _xblockexpression;
  }
  
  @Override
  public List<LightweightBoundTypeArgument> getAllHints(final Object handle) {
    final List<LightweightBoundTypeArgument> result = super.getAllHints(handle);
    final Procedure1<LightweightBoundTypeArgument> _function = new Procedure1<LightweightBoundTypeArgument>() {
      @Override
      public void apply(final LightweightBoundTypeArgument it) {
        boolean _and = false;
        LightweightTypeReference _typeReference = it.getTypeReference();
        boolean _notEquals = (!Objects.equal(_typeReference, null));
        if (!_notEquals) {
          _and = false;
        } else {
          LightweightTypeReference _typeReference_1 = it.getTypeReference();
          ITypeReferenceOwner _referenceOwner = ValidatingExpressionAwareResolvedTypes.this.getReferenceOwner();
          boolean _isOwnedBy = _typeReference_1.isOwnedBy(_referenceOwner);
          boolean _not = (!_isOwnedBy);
          _and = _not;
        }
        if (_and) {
          throw new IllegalArgumentException("hint is not owned by this resolved types");
        }
      }
    };
    IterableExtensions.<LightweightBoundTypeArgument>forEach(result, _function);
    return result;
  }
  
  @Override
  public UnboundTypeReference getUnboundTypeReference(final Object handle) {
    final UnboundTypeReference result = super.getUnboundTypeReference(handle);
    ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
    boolean _isOwnedBy = result.isOwnedBy(_referenceOwner);
    boolean _not = (!_isOwnedBy);
    if (_not) {
      throw new IllegalArgumentException("result is not owned by this resolved types");
    }
    return result;
  }
  
  @Override
  public LightweightTypeReference getActualType(final JvmIdentifiableElement identifiable) {
    final LightweightTypeReference result = super.getActualType(identifiable);
    ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
    boolean _isOwnedBy = result.isOwnedBy(_referenceOwner);
    boolean _not = (!_isOwnedBy);
    if (_not) {
      throw new IllegalArgumentException("result is not owned by this resolved types");
    }
    return result;
  }
  
  @Override
  public LightweightTypeReference getActualType(final XExpression expression) {
    final LightweightTypeReference result = super.getActualType(expression);
    ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
    boolean _isOwnedBy = result.isOwnedBy(_referenceOwner);
    boolean _not = (!_isOwnedBy);
    if (_not) {
      throw new IllegalArgumentException("result is not owned by this resolved types");
    }
    return result;
  }
  
  @Override
  public LightweightTypeReference getExpectedType(final XExpression expression) {
    final LightweightTypeReference result = super.getExpectedType(expression);
    ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
    boolean _isOwnedBy = result.isOwnedBy(_referenceOwner);
    boolean _not = (!_isOwnedBy);
    if (_not) {
      throw new IllegalArgumentException("result is not owned by this resolved types");
    }
    return result;
  }
  
  @Override
  public LightweightTypeReference getMergedType(final List<LightweightTypeReference> types) {
    final Procedure1<LightweightTypeReference> _function = new Procedure1<LightweightTypeReference>() {
      @Override
      public void apply(final LightweightTypeReference it) {
        ITypeReferenceOwner _owner = it.getOwner();
        boolean _isOwnedBy = it.isOwnedBy(_owner);
        boolean _not = (!_isOwnedBy);
        if (_not) {
          throw new IllegalArgumentException("result is not owned by this resolved types");
        }
      }
    };
    IterableExtensions.<LightweightTypeReference>forEach(types, _function);
    final LightweightTypeReference result = super.getMergedType(types);
    ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
    boolean _isOwnedBy = result.isOwnedBy(_referenceOwner);
    boolean _not = (!_isOwnedBy);
    if (_not) {
      throw new IllegalArgumentException("result is not owned by this resolved types");
    }
    return result;
  }
  
  @Override
  public TypeData mergeTypeData(final XExpression expression, final List<TypeData> allValues, final boolean returnType, final boolean nullIfEmpty) {
    final Procedure1<TypeData> _function = new Procedure1<TypeData>() {
      @Override
      public void apply(final TypeData it) {
        ITypeReferenceOwner _referenceOwner = ValidatingExpressionAwareResolvedTypes.this.getReferenceOwner();
        boolean _isOwnedBy = it.isOwnedBy(_referenceOwner);
        boolean _not = (!_isOwnedBy);
        if (_not) {
          throw new IllegalArgumentException("result is not owned by this resolved types");
        }
      }
    };
    IterableExtensions.<TypeData>forEach(allValues, _function);
    final TypeData result = super.mergeTypeData(expression, allValues, returnType, nullIfEmpty);
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(result, null));
    if (!_notEquals) {
      _and = false;
    } else {
      ITypeReferenceOwner _referenceOwner = this.getReferenceOwner();
      boolean _isOwnedBy = result.isOwnedBy(_referenceOwner);
      boolean _not = (!_isOwnedBy);
      _and = _not;
    }
    if (_and) {
      throw new IllegalArgumentException("result is not owned by this resolved types");
    }
    return result;
  }
}
