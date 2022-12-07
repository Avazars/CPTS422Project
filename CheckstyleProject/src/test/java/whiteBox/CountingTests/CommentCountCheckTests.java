package whiteBox.CountingTests;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import countingCheckPackage.CommentCountCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class CommentCountCheckTests {

    int[] testTypesList = {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
    @Spy
    CommentCountCheck spyCountCheck = spy(CommentCountCheck.class);

    @Mock
    CommentCountCheck mockedCountCheck = mock(CommentCountCheck.class);
    CommentCountCheck CountCheck = new CommentCountCheck();
    DetailAST mockAst = mock(DetailAST.class);

    @Test
    void isCommentNodesRequired() {
        assert CountCheck.isCommentNodesRequired();
    }

    @Test
    void getAcceptableTokens() {
        assert Arrays.equals(CountCheck.getAcceptableTokens(), new int[] {TokenTypes.VARIABLE_DEF});
    }

    @Test
    void testCountInit(){
        assert CountCheck.getCount() == 0;
    }

    @Test
    void visitToken() {
        doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(mockAst).getType();
        spyCountCheck.visitToken(mockAst);
        verify(spyCountCheck, atLeastOnce()).visitToken(mockAst);
    }

    @Test
    void finishTree() {
        String message = "found a total of " + "0" + " comments" + " JDS";
        doNothing().when(spyCountCheck).log(0,message);
        spyCountCheck.finishTree(mockAst);
        verify(spyCountCheck).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(CountCheck.getDefaultTokens(), testTypesList);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(CountCheck.getRequiredTokens(), testTypesList);
    }
}