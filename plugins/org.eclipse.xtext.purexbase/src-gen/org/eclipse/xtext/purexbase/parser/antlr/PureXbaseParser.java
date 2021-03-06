/*
 * generated by Xtext
 */
package org.eclipse.xtext.purexbase.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.purexbase.services.PureXbaseGrammarAccess;

public class PureXbaseParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private PureXbaseGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.eclipse.xtext.purexbase.parser.antlr.internal.InternalPureXbaseParser createParser(XtextTokenStream stream) {
		return new org.eclipse.xtext.purexbase.parser.antlr.internal.InternalPureXbaseParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "Model";
	}
	
	public PureXbaseGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(PureXbaseGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
