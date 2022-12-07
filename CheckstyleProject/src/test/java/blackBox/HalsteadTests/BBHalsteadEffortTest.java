package blackBox.HalsteadTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import halsteadCheckPackage.*;

class BBHalsteadEffortTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "HalsteadFiles";
	
	@Test
	void HalsteadEffortTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		String trueFilePath = NothingFilePath;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(0, check.getCount());
		assertEquals(0, check.getEffort());
	}
	
	@Test
	void HalsteadEffortTestUnique() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		String fileName = "HalsteadEffortTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(1, check.getCount());
		assertEquals(6.238324625039507, check.getEffort());
	}
	
}