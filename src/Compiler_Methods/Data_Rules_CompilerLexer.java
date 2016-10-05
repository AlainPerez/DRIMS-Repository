// Generated from C:\Users\Alain\Desktop\Compilador\Data_Rules_Compiler.g4 by ANTLR 4.1

package Compiler_Methods;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Data_Rules_CompilerLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__5=1, T__4=2, T__3=3, T__2=4, T__1=5, T__0=6, IMPLIES=7, IN=8, EQUAL=9, 
		RANGE_INT=10, RANGE_FLOAT=11, LEFTP=12, RIGTHP=13, ID=14, INT=15, FLOAT=16, 
		LABEL=17, STRING=18, WS=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'['", "'||'", "'¬'", "'&&'", "','", "']'", "'-->'", "IN", "EQUAL", "RANGE_INT", 
		"RANGE_FLOAT", "'('", "')'", "ID", "INT", "FLOAT", "LABEL", "STRING", 
		"WS"
	};
	public static final String[] ruleNames = {
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "IMPLIES", "IN", "EQUAL", 
		"RANGE_INT", "RANGE_FLOAT", "LEFTP", "RIGTHP", "ID", "INT", "FLOAT", "LABEL", 
		"STRING", "WS", "EXPONENT", "HEX_DIGIT", "ESC_SEQ", "OCTAL_ESC", "UNICODE_ESC"
	};


	public Data_Rules_CompilerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Data_Rules_Compiler.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 18: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip(); break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\25\u00ca\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\5\tJ\n\t\3\n\3\n\3\n\5\nO\n\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\7"+
		"\17c\n\17\f\17\16\17f\13\17\3\20\6\20i\n\20\r\20\16\20j\3\21\6\21n\n\21"+
		"\r\21\16\21o\3\21\3\21\7\21t\n\21\f\21\16\21w\13\21\3\21\5\21z\n\21\3"+
		"\21\3\21\6\21~\n\21\r\21\16\21\177\3\21\5\21\u0083\n\21\3\21\6\21\u0086"+
		"\n\21\r\21\16\21\u0087\3\21\5\21\u008b\n\21\3\22\3\22\3\22\7\22\u0090"+
		"\n\22\f\22\16\22\u0093\13\22\3\22\3\22\3\23\3\23\3\23\7\23\u009a\n\23"+
		"\f\23\16\23\u009d\13\23\3\23\3\23\3\24\6\24\u00a2\n\24\r\24\16\24\u00a3"+
		"\3\24\3\24\3\25\3\25\5\25\u00aa\n\25\3\25\6\25\u00ad\n\25\r\25\16\25\u00ae"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\5\27\u00b7\n\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\5\30\u00c2\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\2\32\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f"+
		"\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\2)\2"+
		"\1+\2\1-\2\1/\2\1\61\2\1\3\2\n\5\2C\\aac|\6\2\62;C\\aac|\4\2$$^^\5\2\13"+
		"\f\17\17\"\"\4\2GGgg\4\2--//\5\2\62;CHch\n\2$$))^^ddhhppttvv\u00da\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\3\63\3\2\2\2\5\65\3\2\2\2\78\3\2\2\2\t:\3\2\2"+
		"\2\13=\3\2\2\2\r?\3\2\2\2\17A\3\2\2\2\21I\3\2\2\2\23N\3\2\2\2\25P\3\2"+
		"\2\2\27V\3\2\2\2\31\\\3\2\2\2\33^\3\2\2\2\35`\3\2\2\2\37h\3\2\2\2!\u008a"+
		"\3\2\2\2#\u008c\3\2\2\2%\u0096\3\2\2\2\'\u00a1\3\2\2\2)\u00a7\3\2\2\2"+
		"+\u00b0\3\2\2\2-\u00b6\3\2\2\2/\u00c1\3\2\2\2\61\u00c3\3\2\2\2\63\64\7"+
		"]\2\2\64\4\3\2\2\2\65\66\7~\2\2\66\67\7~\2\2\67\6\3\2\2\289\7\u00ae\2"+
		"\29\b\3\2\2\2:;\7(\2\2;<\7(\2\2<\n\3\2\2\2=>\7.\2\2>\f\3\2\2\2?@\7_\2"+
		"\2@\16\3\2\2\2AB\7/\2\2BC\7/\2\2CD\7@\2\2D\20\3\2\2\2EF\7k\2\2FJ\7p\2"+
		"\2GH\7K\2\2HJ\7P\2\2IE\3\2\2\2IG\3\2\2\2J\22\3\2\2\2KO\7?\2\2LM\7?\2\2"+
		"MO\7?\2\2NK\3\2\2\2NL\3\2\2\2O\24\3\2\2\2PQ\7]\2\2QR\5\37\20\2RS\7.\2"+
		"\2ST\5\37\20\2TU\7_\2\2U\26\3\2\2\2VW\7]\2\2WX\5!\21\2XY\7.\2\2YZ\5!\21"+
		"\2Z[\7_\2\2[\30\3\2\2\2\\]\7*\2\2]\32\3\2\2\2^_\7+\2\2_\34\3\2\2\2`d\t"+
		"\2\2\2ac\t\3\2\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2e\36\3\2\2\2f"+
		"d\3\2\2\2gi\4\62;\2hg\3\2\2\2ij\3\2\2\2jh\3\2\2\2jk\3\2\2\2k \3\2\2\2"+
		"ln\4\62;\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pq\3\2\2\2qu\7\60\2"+
		"\2rt\4\62;\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vy\3\2\2\2wu\3\2\2"+
		"\2xz\5)\25\2yx\3\2\2\2yz\3\2\2\2z\u008b\3\2\2\2{}\7\60\2\2|~\4\62;\2}"+
		"|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082\3\2"+
		"\2\2\u0081\u0083\5)\25\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u008b\3\2\2\2\u0084\u0086\4\62;\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2"+
		"\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"\u008b\5)\25\2\u008am\3\2\2\2\u008a{\3\2\2\2\u008a\u0085\3\2\2\2\u008b"+
		"\"\3\2\2\2\u008c\u008d\7)\2\2\u008d\u0091\t\2\2\2\u008e\u0090\t\3\2\2"+
		"\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092"+
		"\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0095\7)\2\2\u0095"+
		"$\3\2\2\2\u0096\u009b\7$\2\2\u0097\u009a\5-\27\2\u0098\u009a\n\4\2\2\u0099"+
		"\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2"+
		"\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"\u009f\7$\2\2\u009f&\3\2\2\2\u00a0\u00a2\t\5\2\2\u00a1\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a6\b\24\2\2\u00a6(\3\2\2\2\u00a7\u00a9\t\6\2\2\u00a8\u00aa"+
		"\t\7\2\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00ad\4\62;\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2"+
		"\2\2\u00ae\u00af\3\2\2\2\u00af*\3\2\2\2\u00b0\u00b1\t\b\2\2\u00b1,\3\2"+
		"\2\2\u00b2\u00b3\7^\2\2\u00b3\u00b7\t\t\2\2\u00b4\u00b7\5\61\31\2\u00b5"+
		"\u00b7\5/\30\2\u00b6\u00b2\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b5\3\2"+
		"\2\2\u00b7.\3\2\2\2\u00b8\u00b9\7^\2\2\u00b9\u00ba\4\62\65\2\u00ba\u00bb"+
		"\4\629\2\u00bb\u00c2\4\629\2\u00bc\u00bd\7^\2\2\u00bd\u00be\4\629\2\u00be"+
		"\u00c2\4\629\2\u00bf\u00c0\7^\2\2\u00c0\u00c2\4\629\2\u00c1\u00b8\3\2"+
		"\2\2\u00c1\u00bc\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\60\3\2\2\2\u00c3\u00c4"+
		"\7^\2\2\u00c4\u00c5\7w\2\2\u00c5\u00c6\5+\26\2\u00c6\u00c7\5+\26\2\u00c7"+
		"\u00c8\5+\26\2\u00c8\u00c9\5+\26\2\u00c9\62\3\2\2\2\26\2INdjouy\177\u0082"+
		"\u0087\u008a\u0091\u0099\u009b\u00a3\u00a9\u00ae\u00b6\u00c1";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}