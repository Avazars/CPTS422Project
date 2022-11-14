package countingCheckPackage;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

class OperandCountCheckTests {

private int[] tokens = {TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.CHAR_LITERAL, TokenTypes.STRING_LITERAL, TokenTypes.METHOD_CALL, TokenTypes.IDENT};
    
    @Spy
    OperandCountCheck spyOperandCountCheck = spy(OperandCountCheck.class);

    @Mock
    OperandCountCheck mockedOperandCountCheck = mock(OperandCountCheck.class);
    OperandCountCheck operandCountCheck = new OperandCountCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(operandCountCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
        doReturn(TokenTypes.NUM_DOUBLE).when(mockAst).getType();
        spyOperandCountCheck.visitToken(mockAst);
        verify(spyOperandCountCheck, atLeastOnce()).visitToken(mockAst);
    }

    @Test
    void finishTree() {
        doNothing().when(spyOperandCountCheck).log(0, "Found a total of: " + "0" + " operands :JDS");
        spyOperandCountCheck.finishTree(mockAst);
        verify(spyOperandCountCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(operandCountCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(operandCountCheck.getRequiredTokens(), tokens);
    }
}