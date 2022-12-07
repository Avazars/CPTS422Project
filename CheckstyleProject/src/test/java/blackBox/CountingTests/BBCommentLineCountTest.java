package blackBox.CountingTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackBox.TreeWalker;
import countingCheckPackage.*;

class BBCommentLineCountTest {
	
	private String NothingFilePath = "src/test/resources/testFiles/NothingTestFile.java";
	
	private String FilePathBegin = "src/test/resources/testFiles";
	private String FileFolder = "CommentFiles";
	
	@Test
	void CommentTestNone() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		CommentLineCountCheck check = new CommentLineCountCheck();
		String trueFilePath = NothingFilePath;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(0, check.getCount());
	}
	@Test
	void CommentLineSingleLineTestOne() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		CommentLineCountCheck check = new CommentLineCountCheck();
		String fileName = "OneSingleCommentCountTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(1, check.getCount());
	}
	@Test
	void CommentLoneSingleLineTestMore() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		CommentLineCountCheck check = new CommentLineCountCheck();
		String fileName = "ManySingleCommentCountTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(3, check.getCount());
	}
	@Test
	void CommentMultiLineTestOne() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		CommentLineCountCheck check = new CommentLineCountCheck();
		String fileName = "OneMultiCommentCountTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(5, check.getCount());
	}
	@Test
	void CommentMultiLineTestMore() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		CommentLineCountCheck check = new CommentLineCountCheck();
		String fileName = "ManyMultiCommentCountTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(18, check.getCount());
	}
	@Test
	void CommentSingleAndMutiLineTestOne() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		CommentLineCountCheck check = new CommentLineCountCheck();
		String fileName = "OneMixedCommentCountTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(6, check.getCount());
	}
	@Test
	void CommentSingleAndMutiLineTestMore() throws IOException, CheckstyleException 
	{
		// Initialize Intended Check
		CommentLineCountCheck check = new CommentLineCountCheck();
		String fileName = "ManyMixedCommentCountTestFile.java";
		String trueFilePath = this.FilePathBegin + "/" + this.FileFolder + "/" + fileName;
		TreeWalker treeWalker = new TreeWalker(trueFilePath, check);
		treeWalker.test();
		// Verify clean up
		assertEquals(18, check.getCount());
	}
	
}