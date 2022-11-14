package countingCheckPackage;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class LoopingCountCheckTests {

    private int[] tokens = {TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE};
    
    @Spy
    LoopingCountCheck spyLoopingCountCheck = spy(LoopingCountCheck.class);

    @Mock
    LoopingCountCheck mockedLoopingCountCheck = mock(LoopingCountCheck.class);
    LoopingCountCheck loopingCountCheck = new LoopingCountCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(loopingCountCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
        doReturn(TokenTypes.LITERAL_FOR).when(mockAst).getType();
        spyLoopingCountCheck.visitToken(mockAst);
        verify(spyLoopingCountCheck, atLeastOnce()).visitToken(mockAst);
    }

    @Test
    void finishTree() {
        doNothing().when(spyLoopingCountCheck).log(0, "Found a total of: " + "0" + " looping Statements :JDS");
        spyLoopingCountCheck.finishTree(mockAst);
        verify(spyLoopingCountCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(loopingCountCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(loopingCountCheck.getRequiredTokens(), tokens);
    }
}