package halsteadCheckPackage;

import global.LocalConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadDifficultyCheck extends AbstractCheck{

	private int count = 0;
	private double output = 0;
	private HashMap<Integer, Integer> operatorHashMap = new HashMap<>();
	private HashMap<Integer, Integer> operandHashMap = new HashMap<>();	
	
	
	private ArrayList<Integer> operands =  LocalConstants.getOperands();
	private ArrayList<Integer> operators = LocalConstants.getOperators();
	private int[] allOps = LocalConstants.getAllOps();
	
	
	private String messageBeginning = "Halstead Difficulty is: ";
	private String messageEnd = "";
	

	@Override
	public void init() {
		count = 0;
		output = 0;
	}
	
	@Override
    public void visitToken(DetailAST ast) {
	
		
		if (operators.contains(ast.getType())) 
		{
			operatorHashMap.put(ast.getType(), 1);
		}
		
		if (operands.contains(ast.getType())) 
		{
			count = getCount() + 1;
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
		int UOperands = operandHashMap.size();
		int UOperators = operatorHashMap.size();
		
		if (getCount() != 0) {
			this.output = ((UOperators/2)*getCount()) / UOperands;
		} else {
			output = 0;
		}
		
		
    	reportStyleError(aAST, messageBeginning + this.getOutput() + messageEnd);
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

	public int getCount() {
		return count;
	}

	public double getOutput() {
		return output;
	}
}
