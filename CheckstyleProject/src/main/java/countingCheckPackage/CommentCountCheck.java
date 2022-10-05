package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CommentCountCheck extends AbstractCheck{

	public int getCount() {
		return count;
	}

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
    }

    @Override
    public void finishTree(DetailAST aAST) 
    {
		log(aAST.getLineNo(), "found a total of " + count + " comments" + " JDS");
    	count = 0;
    }
    
	@Override
	public int[] getDefaultTokens() {
		int[] tokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
		return tokens;
	}

	@Override
	public int[] getRequiredTokens() {
		return getDefaultTokens();
	}

}
