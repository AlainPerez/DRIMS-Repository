// Generated from C:\Users\Alain\Desktop\Compilador\Data_Rules_Compiler.g4 by ANTLR 4.1

package Compiler_Methods;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Data_Rules_CompilerParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__5=1, T__4=2, T__3=3, T__2=4, T__1=5, T__0=6, IMPLIES=7, IN=8, EQUAL=9, 
		RANGE_INT=10, RANGE_FLOAT=11, LEFTP=12, RIGTHP=13, ID=14, INT=15, FLOAT=16, 
		LABEL=17, STRING=18, WS=19;
	public static final String[] tokenNames = {
		"<INVALID>", "'['", "'||'", "'¬'", "'&&'", "','", "']'", "'-->'", "IN", 
		"EQUAL", "RANGE_INT", "RANGE_FLOAT", "'('", "')'", "ID", "INT", "FLOAT", 
		"LABEL", "STRING", "WS"
	};
	public static final int
		RULE_rule_set = 0, RULE_rule_type = 1, RULE_ar = 2, RULE_ad = 3, RULE_far = 4, 
		RULE_expr_AR = 5, RULE_value_AR = 6, RULE_expr_AD = 7, RULE_expr_FAR = 8, 
		RULE_value_FAR = 9;
	public static final String[] ruleNames = {
		"rule_set", "rule_type", "ar", "ad", "far", "expr_AR", "value_AR", "expr_AD", 
		"expr_FAR", "value_FAR"
	};

	@Override
	public String getGrammarFileName() { return "Data_Rules_Compiler.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	public Rules_Compiler_Manager r_c = new Rules_Compiler_Manager();

	public Data_Rules_CompilerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Rule_setContext extends ParserRuleContext {
		public Rule_typeContext rule_type(int i) {
			return getRuleContext(Rule_typeContext.class,i);
		}
		public List<Rule_typeContext> rule_type() {
			return getRuleContexts(Rule_typeContext.class);
		}
		public Rule_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_set; }
	}

	public final Rule_setContext rule_set() throws RecognitionException {
		Rule_setContext _localctx = new Rule_setContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_rule_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20); rule_type();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << LEFTP) | (1L << ID))) != 0) );
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

	public static class Rule_typeContext extends ParserRuleContext {
		public ArContext ar() {
			return getRuleContext(ArContext.class,0);
		}
		public FarContext far() {
			return getRuleContext(FarContext.class,0);
		}
		public AdContext ad() {
			return getRuleContext(AdContext.class,0);
		}
		public Rule_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_type; }
	}

	public final Rule_typeContext rule_type() throws RecognitionException {
		Rule_typeContext _localctx = new Rule_typeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_rule_type);
		try {
			setState(28);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(25); ar();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(26); ad();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(27); far();
				}
				break;
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

	public static class ArContext extends ParserRuleContext {
		public TerminalNode IMPLIES() { return getToken(Data_Rules_CompilerParser.IMPLIES, 0); }
		public List<Expr_ARContext> expr_AR() {
			return getRuleContexts(Expr_ARContext.class);
		}
		public Expr_ARContext expr_AR(int i) {
			return getRuleContext(Expr_ARContext.class,i);
		}
		public ArContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ar; }
	}

	public final ArContext ar() throws RecognitionException {
		ArContext _localctx = new ArContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); expr_AR();
			r_c.Ant_Check();
			setState(32); match(IMPLIES);
			setState(33); expr_AR();
			r_c.Con_Check();
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

	public static class AdContext extends ParserRuleContext {
		public TerminalNode IMPLIES() { return getToken(Data_Rules_CompilerParser.IMPLIES, 0); }
		public Expr_ADContext expr_AD(int i) {
			return getRuleContext(Expr_ADContext.class,i);
		}
		public List<Expr_ADContext> expr_AD() {
			return getRuleContexts(Expr_ADContext.class);
		}
		public AdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ad; }
	}

	public final AdContext ad() throws RecognitionException {
		AdContext _localctx = new AdContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); expr_AD();
			r_c.Ant_Check();
			setState(38); match(IMPLIES);
			setState(39); expr_AD();
			r_c.Con_Check();
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

	public static class FarContext extends ParserRuleContext {
		public List<Expr_FARContext> expr_FAR() {
			return getRuleContexts(Expr_FARContext.class);
		}
		public TerminalNode IMPLIES() { return getToken(Data_Rules_CompilerParser.IMPLIES, 0); }
		public Expr_FARContext expr_FAR(int i) {
			return getRuleContext(Expr_FARContext.class,i);
		}
		public FarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_far; }
	}

	public final FarContext far() throws RecognitionException {
		FarContext _localctx = new FarContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_far);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42); expr_FAR();
			r_c.Ant_Check();
			setState(44); match(IMPLIES);
			setState(45); expr_FAR();
			r_c.Con_Check();
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

	public static class Expr_ARContext extends ParserRuleContext {
		public Value_ARContext value_AR(int i) {
			return getRuleContext(Value_ARContext.class,i);
		}
		public List<Value_ARContext> value_AR() {
			return getRuleContexts(Value_ARContext.class);
		}
		public Expr_ARContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_AR; }
	}

	public final Expr_ARContext expr_AR() throws RecognitionException {
		Expr_ARContext _localctx = new Expr_ARContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expr_AR);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); value_AR();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 4) | (1L << 5))) != 0)) {
				{
				{
				setState(53);
				switch (_input.LA(1)) {
				case 4:
				case 5:
					{
					setState(49);
					_la = _input.LA(1);
					if ( !(_la==4 || _la==5) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					r_c.setTempQ(r_c.getTempQ()+ " AND ");
					}
					break;
				case 2:
					{
					{
					setState(51); match(2);
					}
					r_c.setTempQ(r_c.getTempQ()+ " OR ");
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(55); value_AR();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Value_ARContext extends ParserRuleContext {
		public Token ID;
		public Token a;
		public Token b;
		public TerminalNode RIGTHP() { return getToken(Data_Rules_CompilerParser.RIGTHP, 0); }
		public TerminalNode ID() { return getToken(Data_Rules_CompilerParser.ID, 0); }
		public TerminalNode EQUAL() { return getToken(Data_Rules_CompilerParser.EQUAL, 0); }
		public TerminalNode LEFTP() { return getToken(Data_Rules_CompilerParser.LEFTP, 0); }
		public TerminalNode STRING() { return getToken(Data_Rules_CompilerParser.STRING, 0); }
		public TerminalNode INT() { return getToken(Data_Rules_CompilerParser.INT, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(Data_Rules_CompilerParser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(Data_Rules_CompilerParser.FLOAT, i);
		}
		public TerminalNode IN() { return getToken(Data_Rules_CompilerParser.IN, 0); }
		public Expr_ARContext expr_AR() {
			return getRuleContext(Expr_ARContext.class,0);
		}
		public Value_ARContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_AR; }
	}

	public final Value_ARContext value_AR() throws RecognitionException {
		Value_ARContext _localctx = new Value_ARContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value_AR);
		try {
			setState(100);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61); ((Value_ARContext)_localctx).ID = match(ID);
				setState(62); match(EQUAL);
				setState(69);
				switch (_input.LA(1)) {
				case INT:
					{
					setState(63); ((Value_ARContext)_localctx).a = match(INT);
					r_c.Check_INT_ATT((((Value_ARContext)_localctx).ID!=null?((Value_ARContext)_localctx).ID.getText():null));
					}
					break;
				case FLOAT:
					{
					setState(65); ((Value_ARContext)_localctx).a = match(FLOAT);
					r_c.Check_FLOAT_ATT((((Value_ARContext)_localctx).ID!=null?((Value_ARContext)_localctx).ID.getText():null));
					}
					break;
				case STRING:
					{
					setState(67); ((Value_ARContext)_localctx).a = match(STRING);
					r_c.Check_STRING_ATT((((Value_ARContext)_localctx).ID!=null?((Value_ARContext)_localctx).ID.getText():null));
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				r_c.setTempQ(r_c.getTempQ()+ "%MK%"+"%CO%"+(((Value_ARContext)_localctx).ID!=null?((Value_ARContext)_localctx).ID.getText():null)+"%CO%"+" = "+(((Value_ARContext)_localctx).a!=null?((Value_ARContext)_localctx).a.getText():null)); r_c.Add_ID((((Value_ARContext)_localctx).ID!=null?((Value_ARContext)_localctx).ID.getText():null));
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72); ((Value_ARContext)_localctx).ID = match(ID);
				setState(73); match(IN);
				setState(84);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					{
					setState(74); match(1);
					setState(75); ((Value_ARContext)_localctx).a = match(INT);
					setState(76); match(5);
					setState(77); ((Value_ARContext)_localctx).b = match(INT);
					setState(78); match(6);
					}
					}
					break;

				case 2:
					{
					{
					setState(79); match(1);
					setState(80); ((Value_ARContext)_localctx).a = match(FLOAT);
					setState(81); match(5);
					setState(82); ((Value_ARContext)_localctx).b = match(FLOAT);
					setState(83); match(6);
					}
					}
					break;
				}
				r_c.setTempQ(r_c.getTempQ()+"%MK%"+"%CO%"+(((Value_ARContext)_localctx).ID!=null?((Value_ARContext)_localctx).ID.getText():null)+"%CO%"+" BETWEEN "+(((Value_ARContext)_localctx).a!=null?((Value_ARContext)_localctx).a.getText():null)+" AND "+(((Value_ARContext)_localctx).b!=null?((Value_ARContext)_localctx).b.getText():null)); r_c.Check_AB((((Value_ARContext)_localctx).a!=null?((Value_ARContext)_localctx).a.getText():null),(((Value_ARContext)_localctx).b!=null?((Value_ARContext)_localctx).b.getText():null)); r_c.Add_ID((((Value_ARContext)_localctx).ID!=null?((Value_ARContext)_localctx).ID.getText():null));
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(87); match(LEFTP);
				r_c.setTempQ(r_c.getTempQ()+"(");
				setState(89); expr_AR();
				setState(90); match(RIGTHP);
				r_c.setTempQ(r_c.getTempQ()+")");
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(93); match(3);
				setState(94); match(LEFTP);
				r_c.setTempQ(r_c.getTempQ()+" NOT(");
				setState(96); expr_AR();
				setState(97); match(RIGTHP);
				r_c.setTempQ(r_c.getTempQ()+")");
				}
				break;
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

	public static class Expr_ADContext extends ParserRuleContext {
		public Token a;
		public Token b;
		public List<TerminalNode> ID() { return getTokens(Data_Rules_CompilerParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(Data_Rules_CompilerParser.ID, i);
		}
		public Expr_ADContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_AD; }
	}

	public final Expr_ADContext expr_AD() throws RecognitionException {
		Expr_ADContext _localctx = new Expr_ADContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expr_AD);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); ((Expr_ADContext)_localctx).a = match(ID);
			r_c.Add_ID((((Expr_ADContext)_localctx).a!=null?((Expr_ADContext)_localctx).a.getText():null)); r_c.setTempQ(r_c.getTempQ()+ "%MK%"+"%CO%"+(((Expr_ADContext)_localctx).a!=null?((Expr_ADContext)_localctx).a.getText():null)+"%CO%");
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4 || _la==5) {
				{
				{
				setState(104);
				_la = _input.LA(1);
				if ( !(_la==4 || _la==5) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(105); ((Expr_ADContext)_localctx).b = match(ID);
				r_c.Add_ID((((Expr_ADContext)_localctx).b!=null?((Expr_ADContext)_localctx).b.getText():null)); r_c.setTempQ(r_c.getTempQ()+ "%MK%"+", %CO%"+(((Expr_ADContext)_localctx).b!=null?((Expr_ADContext)_localctx).b.getText():null)+"%CO%");
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Expr_FARContext extends ParserRuleContext {
		public Value_FARContext value_FAR(int i) {
			return getRuleContext(Value_FARContext.class,i);
		}
		public List<Value_FARContext> value_FAR() {
			return getRuleContexts(Value_FARContext.class);
		}
		public Expr_FARContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_FAR; }
	}

	public final Expr_FARContext expr_FAR() throws RecognitionException {
		Expr_FARContext _localctx = new Expr_FARContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr_FAR);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); value_FAR();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4 || _la==5) {
				{
				{
				setState(113);
				_la = _input.LA(1);
				if ( !(_la==4 || _la==5) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(114); value_FAR();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Value_FARContext extends ParserRuleContext {
		public Token ID;
		public Token LABEL;
		public TerminalNode LABEL() { return getToken(Data_Rules_CompilerParser.LABEL, 0); }
		public TerminalNode ID() { return getToken(Data_Rules_CompilerParser.ID, 0); }
		public TerminalNode EQUAL() { return getToken(Data_Rules_CompilerParser.EQUAL, 0); }
		public Value_FARContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_FAR; }
	}

	public final Value_FARContext value_FAR() throws RecognitionException {
		Value_FARContext _localctx = new Value_FARContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value_FAR);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); ((Value_FARContext)_localctx).ID = match(ID);
			setState(121); match(EQUAL);
			setState(122); ((Value_FARContext)_localctx).LABEL = match(LABEL);
			r_c.Check_ID_Label((((Value_FARContext)_localctx).ID!=null?((Value_FARContext)_localctx).ID.getText():null), (((Value_FARContext)_localctx).LABEL!=null?((Value_FARContext)_localctx).LABEL.getText():null)); r_c.setTempQ(r_c.getTempQ()+ "%MK%"+"%CO%"+(((Value_FARContext)_localctx).ID!=null?((Value_FARContext)_localctx).ID.getText():null)+"%CO%"+" = "+(((Value_FARContext)_localctx).LABEL!=null?((Value_FARContext)_localctx).LABEL.getText():null));
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

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\25\u0080\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\3\2\6\2\30\n\2\r\2\16\2\31\3\3\3\3\3\3\5\3\37\n\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\5\78\n\7\3\7\7\7;\n\7\f\7\16\7>\13\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\bH\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\5\bW\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b"+
		"g\n\b\3\t\3\t\3\t\3\t\3\t\7\tn\n\t\f\t\16\tq\13\t\3\n\3\n\3\n\7\nv\n\n"+
		"\f\n\16\ny\13\n\3\13\3\13\3\13\3\13\3\13\3\13\2\f\2\4\6\b\n\f\16\20\22"+
		"\24\2\3\3\2\6\7\u0082\2\27\3\2\2\2\4\36\3\2\2\2\6 \3\2\2\2\b&\3\2\2\2"+
		"\n,\3\2\2\2\f\62\3\2\2\2\16f\3\2\2\2\20h\3\2\2\2\22r\3\2\2\2\24z\3\2\2"+
		"\2\26\30\5\4\3\2\27\26\3\2\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2"+
		"\2\32\3\3\2\2\2\33\37\5\6\4\2\34\37\5\b\5\2\35\37\5\n\6\2\36\33\3\2\2"+
		"\2\36\34\3\2\2\2\36\35\3\2\2\2\37\5\3\2\2\2 !\5\f\7\2!\"\b\4\1\2\"#\7"+
		"\t\2\2#$\5\f\7\2$%\b\4\1\2%\7\3\2\2\2&\'\5\20\t\2\'(\b\5\1\2()\7\t\2\2"+
		")*\5\20\t\2*+\b\5\1\2+\t\3\2\2\2,-\5\22\n\2-.\b\6\1\2./\7\t\2\2/\60\5"+
		"\22\n\2\60\61\b\6\1\2\61\13\3\2\2\2\62<\5\16\b\2\63\64\t\2\2\2\648\b\7"+
		"\1\2\65\66\7\4\2\2\668\b\7\1\2\67\63\3\2\2\2\67\65\3\2\2\289\3\2\2\29"+
		";\5\16\b\2:\67\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\r\3\2\2\2><\3\2"+
		"\2\2?@\7\20\2\2@G\7\13\2\2AB\7\21\2\2BH\b\b\1\2CD\7\22\2\2DH\b\b\1\2E"+
		"F\7\24\2\2FH\b\b\1\2GA\3\2\2\2GC\3\2\2\2GE\3\2\2\2HI\3\2\2\2Ig\b\b\1\2"+
		"JK\7\20\2\2KV\7\n\2\2LM\7\3\2\2MN\7\21\2\2NO\7\7\2\2OP\7\21\2\2PW\7\b"+
		"\2\2QR\7\3\2\2RS\7\22\2\2ST\7\7\2\2TU\7\22\2\2UW\7\b\2\2VL\3\2\2\2VQ\3"+
		"\2\2\2WX\3\2\2\2Xg\b\b\1\2YZ\7\16\2\2Z[\b\b\1\2[\\\5\f\7\2\\]\7\17\2\2"+
		"]^\b\b\1\2^g\3\2\2\2_`\7\5\2\2`a\7\16\2\2ab\b\b\1\2bc\5\f\7\2cd\7\17\2"+
		"\2de\b\b\1\2eg\3\2\2\2f?\3\2\2\2fJ\3\2\2\2fY\3\2\2\2f_\3\2\2\2g\17\3\2"+
		"\2\2hi\7\20\2\2io\b\t\1\2jk\t\2\2\2kl\7\20\2\2ln\b\t\1\2mj\3\2\2\2nq\3"+
		"\2\2\2om\3\2\2\2op\3\2\2\2p\21\3\2\2\2qo\3\2\2\2rw\5\24\13\2st\t\2\2\2"+
		"tv\5\24\13\2us\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\23\3\2\2\2yw\3\2"+
		"\2\2z{\7\20\2\2{|\7\13\2\2|}\7\23\2\2}~\b\13\1\2~\25\3\2\2\2\13\31\36"+
		"\67<GVfow";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}