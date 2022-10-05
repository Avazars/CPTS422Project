package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

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
    void visitToken() {

    }

    @Test
    void finishTree() {

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