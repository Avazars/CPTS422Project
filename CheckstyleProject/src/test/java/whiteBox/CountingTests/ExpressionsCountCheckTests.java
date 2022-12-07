package whiteBox.CountingTests;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import countingCheckPackage.ExpressionsCountCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ExpressionsCountCheckTests {

    private int[] tokens = {TokenTypes.EXPR};
    @Spy
    ExpressionsCountCheck spyExpressionCountCheck = spy(ExpressionsCountCheck.class);

    @Mock
    ExpressionsCountCheck mockedExpressionCountCheck = mock(ExpressionsCountCheck.class);
    ExpressionsCountCheck ExpressionCountCheck = new ExpressionsCountCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(ExpressionCountCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
        doReturn(TokenTypes.EXPR).when(mockAst).getType();
        spyExpressionCountCheck.visitToken(mockAst);
        verify(spyExpressionCountCheck, atLeastOnce()).visitToken(mockAst);
    }

    @Test
    void finishTree() {
        doNothing().when(spyExpressionCountCheck).log(0, "Found a total of: " + "0" + " expressions :JDS");
        spyExpressionCountCheck.finishTree(mockAst);
        verify(spyExpressionCountCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(ExpressionCountCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(ExpressionCountCheck.getRequiredTokens(), tokens);
    }
}