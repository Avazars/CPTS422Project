package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import global.LocalConstants;

public class OperandCountCheck extends AbstractCheck {
		
	private int count = 0;
	private String messageBeginning = "Found a total of: ";
	private String messageEnd = " operands";
	
	@Override
	public void init() {
		count = 0;
	}
	
	@Override
	public int[] getAcceptableTokens() {
		int[] tokens = {TokenTypes.VARIABLE_DEF};
		return tokens;
	}

	@Override
	public void visitToken(DetailAST ast) 
	{
		count = getCount() + 1;
	}
	
	
	@Override
    public void finishTree(DetailAST aAST) 
    {
    	reportStyleError(aAST, messageBeginning + getCount() + messageEnd);
    }
	
	private void reportStyleError(DetailAST aAST, String variableName) {
        log(0, variableName + " :JDS");
    }
	
	@Override
	public int[] getDefaultTokens() {
		int[] tokens = LocalConstants.getOperandArray();
		return tokens;
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

	public int getCount() {
		return count;
	}

}
