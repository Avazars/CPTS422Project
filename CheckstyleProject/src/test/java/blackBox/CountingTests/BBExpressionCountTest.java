package blackBox.CountingTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import countingCheckPackage.ExpressionsCountCheck;

class BBExpressionCountTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "ExpressionFiles";
	
	
	@Test
	void ExpressionTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		ExpressionsCountCheck check = new ExpressionsCountCheck();
		TreeWalker treeWalker = new TreeWalker(NothingFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(0, check.getCount());
	}
	@Test
	void ExpressionTestOne() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		ExpressionsCountCheck check = new ExpressionsCountCheck();
		String fileName = "OneExpressionCheck.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(1, check.getCount());
	}
	@Test
	void ExpressionTestMore() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		ExpressionsCountCheck check = new ExpressionsCountCheck();
		String fileName = "MoreExpressionCheck.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(3, check.getCount());
	}
}