package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopingCountCheck extends AbstractCheck {

	private int count = 0;
	private String messageBeginning = "Found a total of: ";
	private String messageEnd = " looping Statements";
	
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
		int[] tokens = {TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE};
		return tokens;
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

}
