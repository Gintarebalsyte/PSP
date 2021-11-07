import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTests {

    PasswordValidator passwordValidator;

    @BeforeEach
    void setUp(){
        passwordValidator = new PasswordValidator();
    }

    @Test
    public void checkPasswordLength(){
        assertFalse(passwordValidator.isCorrectLength(6, "test"));
    }

    @Test
    public void checkIfHasUppercase(){
        assertTrue(passwordValidator.hasUppercase("tEst"));
    }

    @Test
    public void checkSpecialSymbols(){
        assertFalse(passwordValidator.hasSpecialSymbols("test", "¡™£¢∞§¶•ªº"));
    }
}
