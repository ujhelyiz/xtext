/*
 * generated by Xtext
 */
grammar DebugInternalTwoParametersTestLanguage ;

// Rule ParserRuleParameters
ruleParserRuleParameters :
	'#1' norm3_Scenario5 |
	'#2' ruleScenario5 |
	'#3' norm3_Scenario6 |
	'#4' norm2_Scenario6 |
	( (
	'#5' norm3_Scenario6
	) => (
		'#5' norm3_Scenario6
	) ) |
	( (
	'#6' ruleScenario6
	) => (
		'#6' ruleScenario6
	) ) |
	'#7' norm3_Scenario7 |
	'#8' norm2_Scenario7 |
	'#9' (
		( (
		norm7_IdOrKeyword2
		) => norm3_Scenario8 ) |
		norm3_Scenario6 'keyword'?
	) |
	'#10' (
		( (
		norm7_IdOrKeyword2
		) => norm3_Scenario8 ) |
		ruleScenario6 'keyword'?
	) |
	'#11' (
		( (
		ruleIdOrKeyword2
		) => ruleScenario8 ) |
		norm3_Scenario6 'keyword'?
	) |
	'#12' (
		( (
		ruleIdOrKeyword2
		) => ruleScenario8 ) |
		ruleScenario6 'keyword'?
	)
;

// Rule Scenario5
ruleScenario5 :
	RULE_ID
;

// Rule Scenario5
norm3_Scenario5 :
	RULE_ID
;

// Rule Scenario6
ruleScenario6 :
	ruleIdOrKeyword2
;

// Rule Scenario6
norm2_Scenario6 :
	norm2_IdOrKeyword2
;

// Rule Scenario6
norm3_Scenario6 :
	norm7_IdOrKeyword2
;

// Rule Scenario7
norm2_Scenario7 :
	( (
	norm2_IdOrKeyword2
	) => norm2_IdOrKeyword2 ) |
	'keyword'
;

// Rule Scenario7
norm3_Scenario7 :
	( (
	norm7_IdOrKeyword2
	) => norm7_IdOrKeyword2 ) |
	'keyword'
;

// Rule Scenario8
ruleScenario8 :
	( (
	ruleIdOrKeyword2
	) => ruleIdOrKeyword2 ) 'keyword'
;

// Rule Scenario8
norm3_Scenario8 :
	( (
	norm7_IdOrKeyword2
	) => norm7_IdOrKeyword2 ) 'keyword'
;

// Rule IdOrKeyword2
ruleIdOrKeyword2 :
	RULE_ID
;

// Rule IdOrKeyword2
norm1_IdOrKeyword2 :
	RULE_ID
;

// Rule IdOrKeyword2
norm2_IdOrKeyword2 :
	RULE_ID
;

// Rule IdOrKeyword2
norm5_IdOrKeyword2 :
	'keyword' |
	RULE_ID
;

// Rule IdOrKeyword2
norm6_IdOrKeyword2 :
	'keyword' |
	RULE_ID
;

// Rule IdOrKeyword2
norm7_IdOrKeyword2 :
	'keyword' |
	RULE_ID
;

// Rule IdOrKeyword
ruleIdOrKeyword :
	RULE_ID
;

// Rule IdOrKeyword
norm1_IdOrKeyword :
	'keyword' |
	RULE_ID
;

RULE_ID :
	'^'? (
		'a' .. 'z' |
		'A' .. 'Z' |
		'_'
	) (
		'a' .. 'z' |
		'A' .. 'Z' |
		'_' |
		'0' .. '9'
	)*
;

RULE_INT :
	'0' .. '9'+
;

RULE_STRING :
	'"' (
		'\\' . |
		~ (
			'\\' |
			'"'
		)
	)* '"' |
	'\'' (
		'\\' . |
		~ (
			'\\' |
			'\''
		)
	)* '\''
;

RULE_ML_COMMENT :
	'/*' (
		options { greedy = false ; } : .
	)* '*/' { skip(); }
;

RULE_SL_COMMENT :
	'//' ~ (
		'\n' |
		'\r'
	)* (
		'\r'? '\n'
	)? { skip(); }
;

RULE_WS :
	(
		' ' |
		'\t' |
		'\r' |
		'\n'
	)+ { skip(); }
;

RULE_ANY_OTHER :
	.
;