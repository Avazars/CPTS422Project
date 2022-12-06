package testingBB;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import countingCheckPackage.ExpressionsCountCheck;

class BBExpressionCountTest {
	@Test
	void ExpressionTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		ExpressionsCountCheck check = new ExpressionsCountCheck();
		String filePath = "src/test/resources/testFiles/NoneExpressionCheck.java";
		TreeWalker treeWalker = new TreeWalker(filePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(0, check.getCount());
		System.out.println("Expression Test with nothing in it Done!");
	}
	@Test
	void ExpressionTestOne() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		ExpressionsCountCheck check = new ExpressionsCountCheck();
		String filePath = "src/test/resources/testFiles/OneExpressionCheck.java";
		TreeWalker treeWalker = new TreeWalker(filePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(1, check.getCount());
		System.out.println("Expression Test with one thing in it Done!");
	}
	@Test
	void ExpressionTestMore() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		ExpressionsCountCheck check = new ExpressionsCountCheck();
		String filePath = "src/test/resources/testFiles/MoreExpressionCheck.java";
		TreeWalker treeWalker = new TreeWalker(filePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(3, check.getCount());
		System.out.println("Expression Test with multiple things in it Done!");
	}
}


