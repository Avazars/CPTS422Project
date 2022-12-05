package testingBB;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

import countingCheckPackage.ExpressionsCountCheck;
import loopingStatementsPackage.LoopingStatementsCheck;

class BBExpressionCountTest {

	@Test
	void test() throws IOException, CheckstyleException 
	{
		// Build File
		String filePath = "src/test/resources/blackBoxTestFiles/";
		File file = new File(filePath + "BBExpressionCountTest.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		ExpressionsCountCheck check = new ExpressionsCountCheck();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check, root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		//for(LocalizedMessage lm : check.getMessages()) {
		//	System.out.println(lm.getMessage());
		//}
		
		Hashtable<String,Integer> results = check.getResults();
		
		// Verify clean up
		assertEquals(0, results.get("Looping statements count: "));
		System.out.println("LoopingStatementsCheck Done!");
	}

	
	public void helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}
}


