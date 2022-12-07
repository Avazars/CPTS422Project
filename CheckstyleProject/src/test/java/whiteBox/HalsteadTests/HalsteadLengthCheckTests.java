package whiteBox.HalsteadTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import global.LocalConstants;
import halsteadCheckPackage.HalsteadLengthCheck;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

class HalsteadLengthCheckTests {

private int[] tokens = LocalConstants.getAllOps();
    
    @Spy
    HalsteadLengthCheck spyHalsteadLengthCheck = spy(HalsteadLengthCheck.class);

    @Mock
    HalsteadLengthCheck mockedHalsteadLengthCheck = mock(HalsteadLengthCheck.class);
    HalsteadLengthCheck HalsteadLengthCheck = new HalsteadLengthCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(HalsteadLengthCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
        doReturn(TokenTypes.NUM_DOUBLE).when(mockAst).getType();
        spyHalsteadLengthCheck.visitToken(mockAst);
        verify(spyHalsteadLengthCheck, atLeastOnce()).visitToken(mockAst);
    }

    @Test
    void finishTree() {
        doNothing().when(spyHalsteadLengthCheck).log(0, "Halstead Length of: " + "0" + " :JDS");
        spyHalsteadLengthCheck.finishTree(mockAst);
        verify(spyHalsteadLengthCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(HalsteadLengthCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(HalsteadLengthCheck.getRequiredTokens(), tokens);
    }
}