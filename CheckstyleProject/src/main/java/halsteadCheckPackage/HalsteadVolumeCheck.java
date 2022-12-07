package halsteadCheckPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import global.LocalConstants;

public class HalsteadVolumeCheck extends AbstractCheck{

	private double output = 0;
	private int count = 0;
	private HashMap<Integer, Integer> hashMap = new HashMap<>();
	
	private ArrayList<Integer> operands =  LocalConstants.getOperands();
	private ArrayList<Integer> operators = LocalConstants.getOperators();
	private int[] allOps = LocalConstants.getAllOps();
	
	private String messageBeginning = "Halstead Volume is: ";
	private String messageEnd = "";
	
	@Override
	public void init() {
		this.count = 0;
		this.output = 0;
	}
	
	@Override
    public void visitToken(DetailAST ast) {
		int value = 1;
		
		if (hashMap.containsKey(ast.getType())) 
		{
			value = hashMap.get(ast.getType()) + 1;
		}
		
		hashMap.put(ast.getType(), value);
    }
	
	private void reportStyleError(DetailAST aAST, String variableName) {
	    log(0, variableName + " :JDS");
	}
	
	@Override
    public void finishTree(DetailAST aAST) 
    {
		int N = aAST.getLineNo();
		int n = hashMap.size();
		this.output = N * (Math.log(n * 1.0)); 
		
		if (n == 0) {
			this.output = -1;
		}
		
		count = hashMap.size();
    	reportStyleError(aAST, messageBeginning + getOutput() + messageEnd);
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

	public double getOutput() {
		return output;
	}

	public int getCount() {
		return count;
	}
}
