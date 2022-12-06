package testingBB;
import java.io.File;
import java.io.IOException;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.JavaParser.Options;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

public class TreeWalker {

	private AbstractCheck check;
	private String path;
	private DetailAST root;
	
	public TreeWalker(String filePath, AbstractCheck check) {
		this.path = filePath;
		this.check = check;
	}
	
	public void test() throws IOException, CheckstyleException {
		// Build File
				String filePath = path;
				File file = new File(filePath);
				FileText ft = new FileText(file,"UTF-8");
				FileContents fc = new FileContents(ft);
				
				// Fill AST with FileContents
				if (this.check.isCommentNodesRequired()) {
					root = JavaParser.parseFile(file, Options.WITH_COMMENTS);
				} else {
					root = JavaParser.parse(fc);
				}
				
				// Configure Check
				this.check.configure(new DefaultConfiguration("Local"));
				this.check.contextualize(new DefaultContext());
				
				// Initialize Local Variables in Check
				this.check.beginTree(root);
				
				// Visit Each Token in Tree
				//helper(check, root);
				helper(this.check, root);
				
				// Complete tree and display intended logs to user.
				this.check.finishTree(root);
	}
	
	
	public void helper(AbstractCheck b, DetailAST token) {
		int[] defaultTokens = this.check.getDefaultTokens();
		while(token != null) 
		{
			int tokenType = token.getType();
			for(int i = 0; i < defaultTokens.length; ++i)
			{
				if (defaultTokens[i] == tokenType)
				{
					this.check.visitToken(token);
					break;
				}
			}
			
			helper(this.check, token.getFirstChild());
			token = token.getNextSibling();
		}
	}
}
