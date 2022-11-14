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

class HalsteadDifficultyCheckTests {

	private int[] tokens = LocalConstants.getAllOps();
	
    @Spy
    HalsteadDifficultyCheck spyHalsteadDifficultyCheck = spy(HalsteadDifficultyCheck.class);

    @Mock
    HalsteadDifficultyCheck mockedHalsteadDifficultyCheck = mock(HalsteadDifficultyCheck.class);
    HalsteadDifficultyCheck HalsteadDifficultyCheck = new HalsteadDifficultyCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(HalsteadDifficultyCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
    	
    	placeOnceTest();
    	enteredTwiceTest();
        
    }

    private void enteredTwiceTest() {	
        doReturn(TokenTypes.NUM_INT).when(mockAst).getType();
        spyHalsteadDifficultyCheck.visitToken(mockAst);
        spyHalsteadDifficultyCheck.visitToken(mockAst);
        verify(spyHalsteadDifficultyCheck, atLeastOnce()).visitToken(mockAst);
		
	}

	private void placeOnceTest() {
    	doReturn(TokenTypes.BOR).when(mockAst).getType();
        spyHalsteadDifficultyCheck.visitToken(mockAst);
        spyHalsteadDifficultyCheck.visitToken(mockAst);
        verify(spyHalsteadDifficultyCheck, atLeastOnce()).visitToken(mockAst);
	}

	@Test
    void finishTree() {
		doReturn(TokenTypes.BOR).when(mockAst).getType();
        spyHalsteadDifficultyCheck.visitToken(mockAst);
        spyHalsteadDifficultyCheck.visitToken(mockAst);
        doReturn(TokenTypes.NUM_INT).when(mockAst).getType();
        spyHalsteadDifficultyCheck.visitToken(mockAst);
        spyHalsteadDifficultyCheck.visitToken(mockAst);
        doNothing().when(spyHalsteadDifficultyCheck).log(0, "Halstead Difficulty is: " + "0.0" + " :JDS");
        spyHalsteadDifficultyCheck.finishTree(mockAst);
        verify(spyHalsteadDifficultyCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(HalsteadDifficultyCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(HalsteadDifficultyCheck.getRequiredTokens(), tokens);
    }
}