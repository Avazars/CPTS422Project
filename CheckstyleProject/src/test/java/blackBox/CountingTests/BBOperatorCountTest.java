package blackBox.CountingTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import countingCheckPackage.*;

class BBOperatorCountTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "OperatorFiles";
	
	@Test
	void OperatorTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		OperatorCountCheck check = new OperatorCountCheck();
		TreeWalker treeWalker = new TreeWalker(NothingFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(2, check.getCount());
	}
	
	@Test
	void OperatorTestCouple() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		OperatorCountCheck check = new OperatorCountCheck();
		
		String fileName = "CoupleOperatorsTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(28, check.getCount());
	}
	
	@Test
	void OperatorTestNested() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		OperatorCountCheck check = new OperatorCountCheck();
		
		String fileName = "NestedOperatorsTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(45, check.getCount());
	}
}