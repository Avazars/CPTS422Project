package blackBox.HalsteadTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import halsteadCheckPackage.*;

class BBHalsteadDifficultyTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "HalsteadFiles";
	
	@Test
	void HalsteadDifficultyTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		String trueFilePath = NothingFilePath;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(0, check.getCount());
		assertEquals(0, check.getOutput());
	}
	
	@Test
	void HalsteadDifficultyTestUnique() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		String fileName = "HalsteadDifficultyTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(10, check.getCount());
		assertEquals(30.0, check.getOutput());
	}
	
}