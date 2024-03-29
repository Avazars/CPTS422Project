package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CommentLineCountCheck extends AbstractCheck{

	// used to count the lines of comments in the document.
	private int count = 0;
	
	// used to store the current line that we are on when we hit a block comments beginning
	private int lineCounter = 0;
	
	@Override
	public void init() {
		count = 0;
	}
	
	// used to allow the treewalker to look at comments
	@Override
	public boolean isCommentNodesRequired() 
	{
		return true;
	}
	
	@Override
    public void visitToken(DetailAST aAST) {
		
		if (aAST.getType() == TokenTypes.BLOCK_COMMENT_BEGIN)
		{
			lineCounter = aAST.getLineNo();
		}else if (aAST.getType()== TokenTypes.BLOCK_COMMENT_END) 
		{
			// count should equal the new further line number - the line number we are on, 
			// but +1 because we still count the number we are on
			count = getCount() + aAST.getLineNo() - lineCounter +1;
			lineCounter = 0;
		}else
		{
			count = getCount() + 1;
		}
    }
	
	@Override
    public void finishTree(DetailAST aAST) 
    {
		log(0, "found a total of: " + getCount() + " lines of comments" + " :JDS");
    }

	//used to declare the types of tokens that we are allowed to look at
	@Override
	public int[] getAcceptableTokens() {
		int[] tokens = {TokenTypes.VARIABLE_DEF};
		return tokens;
	}

	@Override
	public int[] getDefaultTokens() {
		int[] tokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END};
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
