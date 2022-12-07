package whiteBox.HalsteadTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import global.LocalConstants;
import halsteadCheckPackage.HalsteadVocabularyCheck;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

class HalsteadVocabularyCheckTests {

	private int[] tokens = LocalConstants.getAllOps();
	
    @Spy
    HalsteadVocabularyCheck spyHalsteadVocabularyCheck = spy(HalsteadVocabularyCheck.class);

    @Mock
    HalsteadVocabularyCheck mockedHalsteadVocabularyCheck = mock(HalsteadVocabularyCheck.class);
    HalsteadVocabularyCheck HalsteadVocabularyCheck = new HalsteadVocabularyCheck();
    DetailAST mockAst = mock(DetailAST.class);
    
    @Test
    void getAcceptableTokens() {
        int[] tokens = {TokenTypes.VARIABLE_DEF};
        assert Arrays.equals(HalsteadVocabularyCheck.getAcceptableTokens(), tokens);
    }

    @Test
    void visitToken() {
    	
    	placeOnceTest();
    	enteredTwiceTest();
        
    }

    private void enteredTwiceTest() {	
        doReturn(TokenTypes.NUM_INT).when(mockAst).getType();
        spyHalsteadVocabularyCheck.visitToken(mockAst);
        spyHalsteadVocabularyCheck.visitToken(mockAst);
        verify(spyHalsteadVocabularyCheck, atLeastOnce()).visitToken(mockAst);
		
	}

	private void placeOnceTest() {
    	doReturn(TokenTypes.NUM_DOUBLE).when(mockAst).getType();
        spyHalsteadVocabularyCheck.visitToken(mockAst);
        verify(spyHalsteadVocabularyCheck, atLeastOnce()).visitToken(mockAst);
	}

	@Test
    void finishTree() {
        doNothing().when(spyHalsteadVocabularyCheck).log(0, "Halstead Vocabulary is: " + "0" + " :JDS");
        spyHalsteadVocabularyCheck.finishTree(mockAst);
        verify(spyHalsteadVocabularyCheck, atLeastOnce()).finishTree(mockAst);
    }

    @Test
    void getDefaultTokens() {
        assert Arrays.equals(HalsteadVocabularyCheck.getDefaultTokens(), tokens);
    }

    @Test
    void getRequiredTokens() {
        assert Arrays.equals(HalsteadVocabularyCheck.getRequiredTokens(), tokens);
    }
}