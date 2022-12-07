package blackBox.CountingTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import countingCheckPackage.*;

class BBOperandCountTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "OperandFiles";
	
	@Test
	void OperandTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		OperandCountCheck check = new OperandCountCheck();
		TreeWalker treeWalker = new TreeWalker(NothingFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(0, check.getCount());
	}
	@Test
	void OperandTestCouple() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		OperandCountCheck check = new OperandCountCheck();
		
		String fileName = "CoupleOperandsTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		
		// Verify clean up
		assertEquals(6, check.getCount());
	}
}