package whiteBox.CountingTests;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import countingCheckPackage.CommentLineCountCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class CommentLineCountCheckTests {

    private int[] acceptableTokens = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END};

    @Spy
    CommentLineCountCheck spyLineCountCheck = spy(CommentLineCountCheck.class);

    @Mock
    CommentLineCountCheck mockedLineCountCheck = mock(CommentLineCountCheck.class);
    CommentLineCountCheck LineCountCheck = new CommentLineCountCheck();
    DetailAST mockAst = mock(DetailAST.class);

    @Test
    void isCommentNodesRequired() {
        assert LineCountCheck.isCommentNodesRequired();
    }

    @Test
    void visitTokenSingleLine() {
        doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(mockAst).getType();
        spyLineCountCheck.visitToken(mockAst);
        verify(spyLineCountCheck, atLeastOnce()).visitToken(mockAst);
    }

    @Test
    void visitTokenBlockComment() {
        when(mockAst.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN).thenReturn(TokenTypes.BLOCK_COMMENT_END);
        spyLineCountCheck.visitToken(mockAst);
        spyLineCountCheck.visitToken(mockAst);
        verify(spyLineCountCheck, atLeast(2)).visitToken(mockAst);
        assert spyLineCountCheck.getCount() == 1;
    }

    @Test
    void finishTree() {
        String message = "found a total of: " + "0" + " lines of comments" + " :JDS";
        doNothing().when(spyLineCountCheck).log(0,message);
        spyLineCountCheck.finishTree(mockAst);
        verify(spyLineCountCheck).finishTree(mockAst);
    }

    @Test
    void getAcceptableTokens() {
        assert Arrays.equals(LineCountCheck.getAcceptableTokens(), new int[] {TokenTypes.VARIABLE_DEF});
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(LineCountCheck.getDefaultTokens(), acceptableTokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(LineCountCheck.getRequiredTokens(), acceptableTokens);
    }
}