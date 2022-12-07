package blackBox.CountingTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import countingCheckPackage.ExpressionsCountCheck;
import countingCheckPackage.LoopingCountCheck;

class BBLoopingCountTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "LoopingFiles";
	
	@Test
	void LoopingTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		LoopingCountCheck check = new LoopingCountCheck();
		TreeWalker treeWalker = new TreeWalker(NothingFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(0, check.getCount());
	}
	@Test
	void LoopingTestWhile() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		LoopingCountCheck check = new LoopingCountCheck();
		
		String fileName = "WhileLoopingTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(1, check.getCount());
	}
	@Test
	void LoopingTestFor() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		LoopingCountCheck check = new LoopingCountCheck();
		
		String fileName = "ForLoopingTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(1, check.getCount());
	}
	@Test
	void LoopingTestBoth() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		LoopingCountCheck check = new LoopingCountCheck();
		
		String fileName = "BothLoopingTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(2, check.getCount());
	}
}