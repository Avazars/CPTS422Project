package blackBox.HalsteadTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import halsteadCheckPackage.*;

class BBHalsteadVocabluaryTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "HalsteadFiles";
	
	@Test
	void HalsteadEffortTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		String trueFilePath = NothingFilePath;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(3, check.getCount());
	}
	
	@Test
	void HalsteadEffortTestUnique() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		String fileName = "HalsteadVocabularyTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(8, check.getCount());
	}
	
}