// Generated from c:/Users/04dan/Dropbox/Mi PC (LAPTOP-35D3RKBD)/Desktop/URL/2025/COMPILADORES/PR-Compis-pt.2/analizadorsintactico/compilador/src/main/java/antlr/com/Algebra.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class AlgebraLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, VARIABLE=2, NUMERO_REAL=3, LPAREN=4, RPAREN=5, MAS=6, MENOS=7, 
		POR=8, DIV=9, GT=10, LT=11, EQ=12, POW=13, ASIGN=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "VARIABLE", "NUMERO_REAL", "LPAREN", "RPAREN", "MAS", "MENOS", 
			"POR", "DIV", "GT", "LT", "EQ", "POW", "ASIGN", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", null, null, "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'>'", 
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


	public AlgebraLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Algebra.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000fS\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0005\u0001$\b\u0001\n\u0001"+
		"\f\u0001\'\t\u0001\u0001\u0002\u0004\u0002*\b\u0002\u000b\u0002\f\u0002"+
		"+\u0001\u0002\u0001\u0002\u0004\u00020\b\u0002\u000b\u0002\f\u00021\u0003"+
		"\u00024\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0004\u000eN\b\u000e\u000b"+
		"\u000e\f\u000eO\u0001\u000e\u0001\u000e\u0000\u0000\u000f\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u0001\u0000\u0004\u0003\u0000AZ__az\u0004\u000009AZ__az\u0001\u00000"+
		"9\u0003\u0000\t\n\r\r  W\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000"+
		"\u0000\u0000\u0003!\u0001\u0000\u0000\u0000\u0005)\u0001\u0000\u0000\u0000"+
		"\u00075\u0001\u0000\u0000\u0000\t7\u0001\u0000\u0000\u0000\u000b9\u0001"+
		"\u0000\u0000\u0000\r;\u0001\u0000\u0000\u0000\u000f=\u0001\u0000\u0000"+
		"\u0000\u0011?\u0001\u0000\u0000\u0000\u0013A\u0001\u0000\u0000\u0000\u0015"+
		"C\u0001\u0000\u0000\u0000\u0017E\u0001\u0000\u0000\u0000\u0019G\u0001"+
		"\u0000\u0000\u0000\u001bI\u0001\u0000\u0000\u0000\u001dM\u0001\u0000\u0000"+
		"\u0000\u001f \u0005;\u0000\u0000 \u0002\u0001\u0000\u0000\u0000!%\u0007"+
		"\u0000\u0000\u0000\"$\u0007\u0001\u0000\u0000#\"\u0001\u0000\u0000\u0000"+
		"$\'\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000"+
		"\u0000&\u0004\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000(*\u0007"+
		"\u0002\u0000\u0000)(\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000"+
		"+)\u0001\u0000\u0000\u0000+,\u0001\u0000\u0000\u0000,3\u0001\u0000\u0000"+
		"\u0000-/\u0005.\u0000\u0000.0\u0007\u0002\u0000\u0000/.\u0001\u0000\u0000"+
		"\u000001\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u000012\u0001\u0000"+
		"\u0000\u000024\u0001\u0000\u0000\u00003-\u0001\u0000\u0000\u000034\u0001"+
		"\u0000\u0000\u00004\u0006\u0001\u0000\u0000\u000056\u0005{\u0000\u0000"+
		"6\b\u0001\u0000\u0000\u000078\u0005}\u0000\u00008\n\u0001\u0000\u0000"+
		"\u00009:\u0005+\u0000\u0000:\f\u0001\u0000\u0000\u0000;<\u0005-\u0000"+
		"\u0000<\u000e\u0001\u0000\u0000\u0000=>\u0005*\u0000\u0000>\u0010\u0001"+
		"\u0000\u0000\u0000?@\u0005/\u0000\u0000@\u0012\u0001\u0000\u0000\u0000"+
		"AB\u0005>\u0000\u0000B\u0014\u0001\u0000\u0000\u0000CD\u0005<\u0000\u0000"+
		"D\u0016\u0001\u0000\u0000\u0000EF\u0005=\u0000\u0000F\u0018\u0001\u0000"+
		"\u0000\u0000GH\u0005^\u0000\u0000H\u001a\u0001\u0000\u0000\u0000IJ\u0005"+
		"=\u0000\u0000JK\u0005>\u0000\u0000K\u001c\u0001\u0000\u0000\u0000LN\u0007"+
		"\u0003\u0000\u0000ML\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000"+
		"OM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000"+
		"\u0000QR\u0006\u000e\u0000\u0000R\u001e\u0001\u0000\u0000\u0000\u0006"+
		"\u0000%+13O\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}