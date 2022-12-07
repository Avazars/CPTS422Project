package halsteadCheckPackage;

import java.util.HashMap;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import global.LocalConstants;

public class HalsteadVocabularyCheck extends AbstractCheck {

	
	
	private int count = 0;
	private HashMap<Integer, Integer> hashMap = new HashMap<>();
	private int allOps[] = LocalConstants.getAllOps();
	private String messageBeginning = "Halstead Vocabulary is: ";
	private String messageEnd = "";
	
	@Override
	public void init() {
		count = 0;
	}
	
	@Override
    public void visitToken(DetailAST ast) {
		int value = 1;
		System.out.println(ast.getType());
		
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
		this.count = hashMap.size();
    	reportStyleError(aAST, messageBeginning + this.count + messageEnd);
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
}
