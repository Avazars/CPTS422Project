package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CommentCountCheck extends AbstractCheck{


	private int count = 0;
	//private static final String CATCH_MSG = "This is the end of a statement. ";
	
	@Override
	public void init() {
		count = 0;
	}
	
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
		count = getCount() + 1;
    }

    @Override
    public void finishTree(DetailAST aAST) 
    {
		log(0, "found a total of " + getCount() + " comments" + " JDS");
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

	public int getCount() {
		return count;
	}

}
