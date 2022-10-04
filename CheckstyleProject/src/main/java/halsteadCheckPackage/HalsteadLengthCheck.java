package halsteadCheckPackage;


import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadLengthCheck extends AbstractCheck{

	private int count = 0;

	private int operands[] = {TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.METHOD_CALL, TokenTypes.IDENT};
	private int operators[] = {TokenTypes.METHOD_CALL, TokenTypes.IDENT,TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR,TokenTypes.DIV, TokenTypes.MOD, TokenTypes.DEC, TokenTypes.ASSIGN, TokenTypes.BNOT, TokenTypes.LNOT, TokenTypes.UNARY_PLUS, TokenTypes.UNARY_MINUS, TokenTypes.POST_INC ,TokenTypes.POST_DEC,TokenTypes.STAR_ASSIGN, TokenTypes.DIV_ASSIGN, TokenTypes.MOD_ASSIGN,TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN,TokenTypes.SR_ASSIGN, TokenTypes.SL_ASSIGN, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR_ASSIGN, TokenTypes.BOR_ASSIGN, TokenTypes.BAND_ASSIGN, TokenTypes.BSR, TokenTypes.SR, TokenTypes.SL, TokenTypes.LAND, TokenTypes.BOR, TokenTypes.BXOR, TokenTypes.BAND, TokenTypes.LT, TokenTypes.GT, TokenTypes.LE, TokenTypes.GE,TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.LOR, TokenTypes.ENUM,TokenTypes.QUESTION, TokenTypes.LITERAL_ASSERT, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INTERFACE,  TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL, TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RECORD, TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID, TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE,TokenTypes.SEMI, TokenTypes.COLON, TokenTypes.DOUBLE_COLON, TokenTypes.COMMA, TokenTypes.ELLIPSIS, TokenTypes.LCURLY, TokenTypes.LPAREN, TokenTypes.LAMBDA, TokenTypes.DOT,TokenTypes.ARRAY_DECLARATOR, TokenTypes.IMPORT, TokenTypes.PACKAGE_DEF,  TokenTypes.ABSTRACT, TokenTypes.FINAL, TokenTypes.DO_WHILE, TokenTypes.SLIST};
	private int allOps[] = {TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.METHOD_CALL, TokenTypes.IDENT,TokenTypes.METHOD_CALL, TokenTypes.IDENT,TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.STAR,TokenTypes.DIV, TokenTypes.MOD, TokenTypes.DEC, TokenTypes.ASSIGN, TokenTypes.BNOT, TokenTypes.LNOT, TokenTypes.UNARY_PLUS, TokenTypes.UNARY_MINUS, TokenTypes.POST_INC ,TokenTypes.POST_DEC,TokenTypes.STAR_ASSIGN, TokenTypes.DIV_ASSIGN, TokenTypes.MOD_ASSIGN,TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN,TokenTypes.SR_ASSIGN, TokenTypes.SL_ASSIGN, TokenTypes.BSR_ASSIGN, TokenTypes.BXOR_ASSIGN, TokenTypes.BOR_ASSIGN, TokenTypes.BAND_ASSIGN, TokenTypes.BSR, TokenTypes.SR, TokenTypes.SL, TokenTypes.LAND, TokenTypes.BOR, TokenTypes.BXOR, TokenTypes.BAND, TokenTypes.LT, TokenTypes.GT, TokenTypes.LE, TokenTypes.GE,TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.LOR, TokenTypes.ENUM,TokenTypes.QUESTION, TokenTypes.LITERAL_ASSERT, TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CLASS, TokenTypes.LITERAL_CONTINUE, TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_INTERFACE,  TokenTypes.LITERAL_NATIVE, TokenTypes.LITERAL_NEW, TokenTypes.LITERAL_NULL, TokenTypes.LITERAL_PRIVATE, TokenTypes.LITERAL_PROTECTED, TokenTypes.LITERAL_PUBLIC, TokenTypes.LITERAL_RECORD, TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_STATIC, TokenTypes.LITERAL_SUPER, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_SYNCHRONIZED, TokenTypes.LITERAL_THIS, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_TRANSIENT, TokenTypes.LITERAL_TRUE, TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_VOID, TokenTypes.LITERAL_VOLATILE, TokenTypes.LITERAL_WHILE,TokenTypes.SEMI, TokenTypes.COLON, TokenTypes.DOUBLE_COLON, TokenTypes.COMMA, TokenTypes.ELLIPSIS, TokenTypes.LCURLY, TokenTypes.LPAREN, TokenTypes.LAMBDA, TokenTypes.DOT,TokenTypes.ARRAY_DECLARATOR, TokenTypes.IMPORT, TokenTypes.PACKAGE_DEF,  TokenTypes.ABSTRACT, TokenTypes.FINAL, TokenTypes.DO_WHILE, TokenTypes.SLIST};
	private String messageBeginning = "Halstead Length of: ";
	private String messageEnd = " ";
	
	@Override
    public void visitToken(DetailAST aAST) {
		count ++;
    }
	
	private void reportStyleError(DetailAST aAST, String variableName) {
	    log(0, variableName + " JDS");
	}
	
	@Override
    public void finishTree(DetailAST aAST) 
    {
    	reportStyleError(aAST, messageBeginning + count + messageEnd);
    	count = 0;
    }
	
	@Override
	public int[] getAcceptableTokens() {
		int[] tokens = {TokenTypes.VARIABLE_DEF};
		return tokens;
	}


	
	@Override
	public int[] getDefaultTokens() {
		return allOps;
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

}
