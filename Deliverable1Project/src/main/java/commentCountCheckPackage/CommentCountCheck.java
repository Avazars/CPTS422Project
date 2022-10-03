package commentCountCheckPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CommentCountCheck extends AbstractCheck{

	private int count = 0;
	//private static final String CATCH_MSG = "This is the end of a statement. ";
	
	@Override
	public boolean isCommentNodesRequired() 
	{
		return true;
	}
	
	@Override
	public int[] getAcceptableTokens() {
		int[] tokens = {TokenTypes.VARIABLE_DEF};
		return tokens;
	}

	@Override
    public void visitToken(DetailAST aAST) {
		count ++;
		reportStyleError(aAST, "Comment Found");
    }
 
    private void reportStyleError(DetailAST aAST, String variableName) {
        log(aAST.getLineNo(), variableName + "JDS");
    }
	
    @Override
    public void finishTree(DetailAST aAST) 
    {
    	reportStyleError(aAST, "found a total of " + count + " comments");
    }
    
	@Override
	public int[] getDefaultTokens() {
		int[] tokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
		return tokens;
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}

}
