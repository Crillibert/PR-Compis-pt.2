package antlr.com;
// Generated from com\antlr4\Algebra.g4 by ANTLR 4.9.3


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AlgebraParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, VARIABLE=2, NUMERO_REAL=3, LPAREN=4, RPAREN=5, MAS=6, MENOS=7, 
		POR=8, DIV=9, GT=10, LT=11, EQ=12, POW=13, ASIGN=14, WS=15;
	public static final int
		RULE_programa = 0, RULE_ecuacion = 1, RULE_expresion = 2, RULE_atom = 3, 
		RULE_reales = 4, RULE_variable = 5, RULE_relop = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "ecuacion", "expresion", "atom", "reales", "variable", "relop"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", null, null, "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'>'", 
			"'<'", "'='", "'^'", "'=>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "VARIABLE", "NUMERO_REAL", "LPAREN", "RPAREN", "MAS", "MENOS", 
			"POR", "DIV", "GT", "LT", "EQ", "POW", "ASIGN", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Algebra.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AlgebraParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(AlgebraParser.EOF, 0); }
		public List<EcuacionContext> ecuacion() {
			return getRuleContexts(EcuacionContext.class);
		}
		public EcuacionContext ecuacion(int i) {
			return getRuleContext(EcuacionContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARIABLE) | (1L << NUMERO_REAL) | (1L << LPAREN) | (1L << MAS) | (1L << MENOS))) != 0)) {
				{
				{
				setState(14);
				ecuacion();
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(20);
			match(T__0);
			setState(21);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EcuacionContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public RelopContext relop() {
			return getRuleContext(RelopContext.class,0);
		}
		public EcuacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ecuacion; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitEcuacion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EcuacionContext ecuacion() throws RecognitionException {
		EcuacionContext _localctx = new EcuacionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ecuacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			expresion(0);
			setState(24);
			relop();
			setState(25);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpresionContext extends ParserRuleContext {
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
	 
		public ExpresionContext() { }
		public void copyFrom(ExpresionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AddSubExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MAS() { return getToken(AlgebraParser.MAS, 0); }
		public TerminalNode MENOS() { return getToken(AlgebraParser.MENOS, 0); }
		public AddSubExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomExprContext extends ExpresionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<TerminalNode> MAS() { return getTokens(AlgebraParser.MAS); }
		public TerminalNode MAS(int i) {
			return getToken(AlgebraParser.MAS, i);
		}
		public List<TerminalNode> MENOS() { return getTokens(AlgebraParser.MENOS); }
		public TerminalNode MENOS(int i) {
			return getToken(AlgebraParser.MENOS, i);
		}
		public AtomExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PowExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode POW() { return getToken(AlgebraParser.POW, 0); }
		public PowExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitPowExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivExprContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode POR() { return getToken(AlgebraParser.POR, 0); }
		public TerminalNode DIV() { return getToken(AlgebraParser.DIV, 0); }
		public MulDivExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitMulDivExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExpresionContext {
		public TerminalNode LPAREN() { return getToken(AlgebraParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AlgebraParser.RPAREN, 0); }
		public ParenExprContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		return expresion(0);
	}

	private ExpresionContext expresion(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpresionContext _localctx = new ExpresionContext(_ctx, _parentState);
		ExpresionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expresion, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(28);
				match(LPAREN);
				setState(29);
				expresion(0);
				setState(30);
				match(RPAREN);
				}
				break;
			case VARIABLE:
			case NUMERO_REAL:
			case MAS:
			case MENOS:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==MAS || _la==MENOS) {
					{
					{
					setState(32);
					_la = _input.LA(1);
					if ( !(_la==MAS || _la==MENOS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(37);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(38);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(50);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new PowExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(41);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(42);
						match(POW);
						setState(43);
						expresion(6);
						}
						break;
					case 2:
						{
						_localctx = new MulDivExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(44);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(45);
						_la = _input.LA(1);
						if ( !(_la==POR || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(46);
						expresion(5);
						}
						break;
					case 3:
						{
						_localctx = new AddSubExprContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(47);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(48);
						_la = _input.LA(1);
						if ( !(_la==MAS || _la==MENOS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(49);
						expresion(4);
						}
						break;
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarAtomContext extends AtomContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VarAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitVarAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberAtomContext extends AtomContext {
		public RealesContext reales() {
			return getRuleContext(RealesContext.class,0);
		}
		public NumberAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitNumberAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMERO_REAL:
				_localctx = new NumberAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				reales();
				}
				break;
			case VARIABLE:
				_localctx = new VarAtomContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RealesContext extends ParserRuleContext {
		public TerminalNode NUMERO_REAL() { return getToken(AlgebraParser.NUMERO_REAL, 0); }
		public RealesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reales; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitReales(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RealesContext reales() throws RecognitionException {
		RealesContext _localctx = new RealesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_reales);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(NUMERO_REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(AlgebraParser.VARIABLE, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelopContext extends ParserRuleContext {
		public RelopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relop; }
	 
		public RelopContext() { }
		public void copyFrom(RelopContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EqOpContext extends RelopContext {
		public TerminalNode EQ() { return getToken(AlgebraParser.EQ, 0); }
		public EqOpContext(RelopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitEqOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignOpContext extends RelopContext {
		public TerminalNode ASIGN() { return getToken(AlgebraParser.ASIGN, 0); }
		public AssignOpContext(RelopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitAssignOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LtOpContext extends RelopContext {
		public TerminalNode LT() { return getToken(AlgebraParser.LT, 0); }
		public LtOpContext(RelopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitLtOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GtOpContext extends RelopContext {
		public TerminalNode GT() { return getToken(AlgebraParser.GT, 0); }
		public GtOpContext(RelopContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AlgebraVisitor ) return ((AlgebraVisitor<? extends T>)visitor).visitGtOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelopContext relop() throws RecognitionException {
		RelopContext _localctx = new RelopContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_relop);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQ:
				_localctx = new EqOpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				match(EQ);
				}
				break;
			case GT:
				_localctx = new GtOpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(GT);
				}
				break;
			case LT:
				_localctx = new LtOpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				match(LT);
				}
				break;
			case ASIGN:
				_localctx = new AssignOpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				match(ASIGN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expresion_sempred((ExpresionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expresion_sempred(ExpresionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21H\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\7\2\22\n\2\f\2\16\2\25"+
		"\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4$\n\4\f\4"+
		"\16\4\'\13\4\3\4\5\4*\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\65\n"+
		"\4\f\4\16\48\13\4\3\5\3\5\5\5<\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\5\b"+
		"F\n\b\3\b\2\3\6\t\2\4\6\b\n\f\16\2\4\3\2\b\t\3\2\n\13\2J\2\23\3\2\2\2"+
		"\4\31\3\2\2\2\6)\3\2\2\2\b;\3\2\2\2\n=\3\2\2\2\f?\3\2\2\2\16E\3\2\2\2"+
		"\20\22\5\4\3\2\21\20\3\2\2\2\22\25\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2"+
		"\24\26\3\2\2\2\25\23\3\2\2\2\26\27\7\3\2\2\27\30\7\2\2\3\30\3\3\2\2\2"+
		"\31\32\5\6\4\2\32\33\5\16\b\2\33\34\5\6\4\2\34\5\3\2\2\2\35\36\b\4\1\2"+
		"\36\37\7\6\2\2\37 \5\6\4\2 !\7\7\2\2!*\3\2\2\2\"$\t\2\2\2#\"\3\2\2\2$"+
		"\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3\2\2\2\'%\3\2\2\2(*\5\b\5\2)\35\3\2"+
		"\2\2)%\3\2\2\2*\66\3\2\2\2+,\f\7\2\2,-\7\17\2\2-\65\5\6\4\b./\f\6\2\2"+
		"/\60\t\3\2\2\60\65\5\6\4\7\61\62\f\5\2\2\62\63\t\2\2\2\63\65\5\6\4\6\64"+
		"+\3\2\2\2\64.\3\2\2\2\64\61\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3"+
		"\2\2\2\67\7\3\2\2\28\66\3\2\2\29<\5\n\6\2:<\5\f\7\2;9\3\2\2\2;:\3\2\2"+
		"\2<\t\3\2\2\2=>\7\5\2\2>\13\3\2\2\2?@\7\4\2\2@\r\3\2\2\2AF\7\16\2\2BF"+
		"\7\f\2\2CF\7\r\2\2DF\7\20\2\2EA\3\2\2\2EB\3\2\2\2EC\3\2\2\2ED\3\2\2\2"+
		"F\17\3\2\2\2\t\23%)\64\66;E";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}