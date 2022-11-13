package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExpressionsCountCheck extends AbstractCheck {


	private int count = 0;

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
		log(0, "Found a total of: " + count + " expressions :JDS");
		count = 0;
    }
	
	@Override
	public int[] getDefaultTokens() {
		int[] tokens = {TokenTypes.EXPR};
		return tokens;
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}
}
