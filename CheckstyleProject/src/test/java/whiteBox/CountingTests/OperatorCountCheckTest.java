package whiteBox.CountingTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import countingCheckPackage.OperatorCountCheck;
import global.LocalConstants;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

class OperatorCountCheckTest {

private int[] tokens = LocalConstants.getOperatorArray();
    @Spy
    OperatorCountCheck spyOperatorCountCheck = spy(OperatorCountCheck.class);

    @Mock
    OperatorCountCheck mockedOperatorCountCheck = mock(OperatorCountCheck.class);
    OperatorCountCheck operatorCountCheck = new OperatorCountCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(operatorCountCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
        doReturn(TokenTypes.NUM_DOUBLE).when(mockAst).getType();
        spyOperatorCountCheck.visitToken(mockAst);
        verify(spyOperatorCountCheck, atLeastOnce()).visitToken(mockAst);
    }

    @Test
    void finishTree() {
        doNothing().when(spyOperatorCountCheck).log(0, "Found a total of: " + "0" + " operators :JDS");
        spyOperatorCountCheck.finishTree(mockAst);
        verify(spyOperatorCountCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(operatorCountCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(operatorCountCheck.getRequiredTokens(), tokens);
    }
}