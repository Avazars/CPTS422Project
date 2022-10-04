package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class OperandCountCheck extends AbstractCheck {
	
	private int operands[] = {TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.METHOD_CALL, TokenTypes.IDENT};
	
	private int count = 0;
	private String messageBeginning = "Found a total of: ";
	private String messageEnd = " operands";
	
	@Override
	public int[] getAcceptableTokens() {
		int[] tokens = {TokenTypes.VARIABLE_DEF};
		return tokens;
	}

	@Override
	public void visitToken(DetailAST ast) 
	{
		count++;
	}
	
	
	@Override
    public void finishTree(DetailAST aAST) 
    {
    	reportStyleError(aAST, messageBeginning + count + messageEnd);
    	count = 0;
    }
	
	private void reportStyleError(DetailAST aAST, String variableName) {
        log(0, variableName + " :JDS");
    }
	
	@Override
	public int[] getDefaultTokens() {
		int[] tokens = operands;
		return tokens;
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

}
