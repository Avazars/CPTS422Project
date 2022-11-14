package halsteadCheckPackage;

import global.LocalConstants;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadLengthCheck extends AbstractCheck{

	private int count = 0;

	private ArrayList<Integer> operands = LocalConstants.getOperands();
	private ArrayList<Integer> operators = LocalConstants.getOperators();
	private int allOps[] = LocalConstants.getAllOps();
	private String messageBeginning = "Halstead Length of: ";
	private String messageEnd = "";
	
	@Override
    public void visitToken(DetailAST aAST) {
		count ++;
    }
	
	private void reportStyleError(DetailAST aAST, String variableName) {
	    log(0, variableName + " :JDS");
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
