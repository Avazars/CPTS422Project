package blackBox.HalsteadTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import halsteadCheckPackage.*;

class BBHalsteadLengthTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "HalsteadFiles";
	
	@Test
	void HalsteadEffortTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		String trueFilePath = NothingFilePath;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(4, check.getCount());
	}
	
	@Test
	void HalsteadEffortTestUnique() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		String fileName = "HalsteadLengthTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(57, check.getCount());
	}
	
}