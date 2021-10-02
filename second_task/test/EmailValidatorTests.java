import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTests {

    EmailValidator emailValidator;

    @BeforeEach
    void setUp(){
        emailValidator = new EmailValidator();
    }

    @Test
    void checkAtSymbol() {
        assertTrue(emailValidator.hasAtSymbol("test@test"));
    }

    @Test
    void checkInvalidSymbols() {
        assertFalse(emailValidator.hasForbiddenSymbols("test@test"));
    }

    @Test
    void checkDomain() {
        assertFalse(emailValidator.hasValidDomain("test@test"));
    }

}