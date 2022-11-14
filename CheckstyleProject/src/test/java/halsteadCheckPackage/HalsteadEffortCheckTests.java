package halsteadCheckPackage;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import global.LocalConstants;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

class HalsteadEffortCheckTests {

	private int[] tokens = LocalConstants.getAllOps();
	
    @Spy
    HalsteadEffortCheck spyHalsteadEffortCheck = spy(HalsteadEffortCheck.class);

    @Mock
    HalsteadEffortCheck mockedHalsteadEffortCheck = mock(HalsteadEffortCheck.class);
    HalsteadEffortCheck HalsteadEffortCheck = new HalsteadEffortCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(HalsteadEffortCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
    	
    	placeOnceTest();
    	enteredTwiceTest();
        
    }

    private void enteredTwiceTest() {	
        doReturn(TokenTypes.NUM_INT).when(mockAst).getType();
        spyHalsteadEffortCheck.visitToken(mockAst);
        spyHalsteadEffortCheck.visitToken(mockAst);
        verify(spyHalsteadEffortCheck, atLeastOnce()).visitToken(mockAst);
		
	}

	private void placeOnceTest() {
    	doReturn(TokenTypes.BOR).when(mockAst).getType();
        spyHalsteadEffortCheck.visitToken(mockAst);
        spyHalsteadEffortCheck.visitToken(mockAst);
        verify(spyHalsteadEffortCheck, atLeastOnce()).visitToken(mockAst);
	}

	@Test
    void finishTree() {
		doReturn(TokenTypes.BOR).when(mockAst).getType();
        spyHalsteadEffortCheck.visitToken(mockAst);
        spyHalsteadEffortCheck.visitToken(mockAst);
        doReturn(TokenTypes.NUM_INT).when(mockAst).getType();
        spyHalsteadEffortCheck.visitToken(mockAst);
        spyHalsteadEffortCheck.visitToken(mockAst);
        doNothing().when(spyHalsteadEffortCheck).log(0, "Halstead Effort is: " + "0.0" + " :JDS");
        spyHalsteadEffortCheck.finishTree(mockAst);
        verify(spyHalsteadEffortCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(HalsteadEffortCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(HalsteadEffortCheck.getRequiredTokens(), tokens);
    }
}