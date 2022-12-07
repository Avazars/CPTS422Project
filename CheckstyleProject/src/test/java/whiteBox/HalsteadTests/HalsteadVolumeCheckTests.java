package whiteBox.HalsteadTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import global.LocalConstants;
import halsteadCheckPackage.HalsteadVolumeCheck;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

class HalsteadVolumeCheckTests {

	private int[] tokens = LocalConstants.getAllOps();
	
    @Spy
    HalsteadVolumeCheck spyHalsteadVolumeCheck = spy(HalsteadVolumeCheck.class);

    @Mock
    HalsteadVolumeCheck mockedHalsteadVolumeCheck = mock(HalsteadVolumeCheck.class);
    HalsteadVolumeCheck HalsteadVolumeCheck = new HalsteadVolumeCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(HalsteadVolumeCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
    	
    	placeOnceTest();
    	enteredTwiceTest();
        
    }

    private void enteredTwiceTest() {	
        doReturn(TokenTypes.NUM_INT).when(mockAst).getType();
        spyHalsteadVolumeCheck.visitToken(mockAst);
        spyHalsteadVolumeCheck.visitToken(mockAst);
        verify(spyHalsteadVolumeCheck, atLeastOnce()).visitToken(mockAst);
		
	}

	private void placeOnceTest() {
    	doReturn(TokenTypes.NUM_DOUBLE).when(mockAst).getType();
        spyHalsteadVolumeCheck.visitToken(mockAst);
        verify(spyHalsteadVolumeCheck, atLeastOnce()).visitToken(mockAst);
	}

	@Test
    void finishTree() {
        doNothing().when(spyHalsteadVolumeCheck).log(0, "Halstead Volume is: " + "-1.0" + " :JDS");
        spyHalsteadVolumeCheck.finishTree(mockAst);
        verify(spyHalsteadVolumeCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(HalsteadVolumeCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(HalsteadVolumeCheck.getRequiredTokens(), tokens);
    }
}