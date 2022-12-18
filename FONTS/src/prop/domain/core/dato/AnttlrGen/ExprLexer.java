// Generated from C:/Users/Luis/Desktop/subgrup-prop2-3-master-0d932889cad1e938ad8dd5a3c335e76b9e5e509c/src/prop/core/dato/dev\Expr.g4 by ANTLR 4.9.2
package prop.domain.core.dato.AnttlrGen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, MULT=6, DIV=7, PLUS=8, MINUS=9, 
		ID=10, NAME=11, NUM=12, WHITESPACE=13, STRING=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "LETTER", "DIGIT", "MULT", "DIV", 
			"PLUS", "MINUS", "ID", "NAME", "NUM", "WHITESPACE", "STRING"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'^'", "':'", "','", "'*'", "'/'", "'+'", "'-'", 
			null, null, null, "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "MULT", "DIV", "PLUS", "MINUS", "ID", 
			"NAME", "NUM", "WHITESPACE", "STRING"
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


	public ExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20_\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\r\6\r;\n\r\r\r\16\r<\3\16\6\16@\n\16\r\16\16\16A\3\17\6"+
		"\17E\n\17\r\17\16\17F\3\17\3\17\6\17K\n\17\r\17\16\17L\5\17O\n\17\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\7\21W\n\21\f\21\16\21Z\13\21\3\21\3\21\3\21"+
		"\3\21\2\2\22\3\3\5\4\7\5\t\6\13\7\r\2\17\2\21\b\23\t\25\n\27\13\31\f\33"+
		"\r\35\16\37\17!\20\3\2\5\4\2C\\c|\3\2\62;\3\2$$\2b\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\3#\3\2\2\2\5%\3\2\2\2\7\'\3\2\2\2\t)\3\2\2\2\13+\3\2"+
		"\2\2\r-\3\2\2\2\17/\3\2\2\2\21\61\3\2\2\2\23\63\3\2\2\2\25\65\3\2\2\2"+
		"\27\67\3\2\2\2\31:\3\2\2\2\33?\3\2\2\2\35D\3\2\2\2\37P\3\2\2\2!T\3\2\2"+
		"\2#$\7*\2\2$\4\3\2\2\2%&\7+\2\2&\6\3\2\2\2\'(\7`\2\2(\b\3\2\2\2)*\7<\2"+
		"\2*\n\3\2\2\2+,\7.\2\2,\f\3\2\2\2-.\t\2\2\2.\16\3\2\2\2/\60\t\3\2\2\60"+
		"\20\3\2\2\2\61\62\7,\2\2\62\22\3\2\2\2\63\64\7\61\2\2\64\24\3\2\2\2\65"+
		"\66\7-\2\2\66\26\3\2\2\2\678\7/\2\28\30\3\2\2\29;\5\17\b\2:9\3\2\2\2;"+
		"<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\32\3\2\2\2>@\5\r\7\2?>\3\2\2\2@A\3\2\2"+
		"\2A?\3\2\2\2AB\3\2\2\2B\34\3\2\2\2CE\5\17\b\2DC\3\2\2\2EF\3\2\2\2FD\3"+
		"\2\2\2FG\3\2\2\2GN\3\2\2\2HJ\7\60\2\2IK\5\17\b\2JI\3\2\2\2KL\3\2\2\2L"+
		"J\3\2\2\2LM\3\2\2\2MO\3\2\2\2NH\3\2\2\2NO\3\2\2\2O\36\3\2\2\2PQ\7\"\2"+
		"\2QR\3\2\2\2RS\b\20\2\2S \3\2\2\2TX\7$\2\2UW\n\4\2\2VU\3\2\2\2WZ\3\2\2"+
		"\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2ZX\3\2\2\2[\\\7$\2\2\\]\3\2\2\2]^\b\21"+
		"\2\2^\"\3\2\2\2\t\2<AFLNX\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}