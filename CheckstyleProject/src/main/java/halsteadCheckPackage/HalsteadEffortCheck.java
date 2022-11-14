package halsteadCheckPackage;

import java.util.ArrayList;
import java.util.HashMap;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import global.LocalConstants;

public class HalsteadEffortCheck extends AbstractCheck {

	private int operandCount = 0;
	private HashMap<Integer, Integer> operatorHashMap = new HashMap<>();
	private HashMap<Integer, Integer> operandHashMap = new HashMap<>();	
	
	
	private ArrayList<Integer> operands =  LocalConstants.getOperands();
	private ArrayList<Integer> operators = LocalConstants.getOperators();
	private int[] allOps = LocalConstants.getAllOps();
	
	
	private String messageBeginning = "Halstead Effort is: ";
	private String messageEnd = "";
	
	
	@Override
    public void visitToken(DetailAST ast) {
	
		
		if (operators.contains(ast.getType())) 
		{
			operatorHashMap.put(ast.getType(), 1);
		}
		
		if (operands.contains(ast.getType())) 
		{
			operandCount++;
			operandHashMap.put(ast.getType(), 1);
		}
		
    }
	
	private void reportStyleError(DetailAST aAST, String variableName) {
	    log(0, variableName + " :JDS");
	}
	
	
	@Override
    public void finishTree(DetailAST aAST) 
    {
		int N = aAST.getLineNo();
		int n = operandHashMap.size() + operatorHashMap.size();
		double volume = N * (Math.log(n * 1.0));
		
		int UOperands = operandHashMap.size();
		int UOperators = operatorHashMap.size();
		
		double difficulty = ((UOperators/2)*operandCount) / UOperands; 
		
		double effort = volume * difficulty;
		
		System.out.println(effort);
		
    	reportStyleError(aAST, messageBeginning + effort + messageEnd);
    	operandCount = 0;
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
