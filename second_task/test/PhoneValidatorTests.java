import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PhoneValidatorTests {

    PhoneValidator phoneValidator;

    @BeforeEach
    void setUp(){
        phoneValidator = new PhoneValidator();
    }

    @Test
    void checkNumber() {
        assertTrue(phoneValidator.hasOnlyNumbers("123123123"));
    }

    @Test
    void checkPrefix() {
        assertEquals("+37061231231", phoneValidator.changePrefix("861231231"));
    }

    @Test
    void checkLengthByCountryCode() {
        assertFalse(phoneValidator.isCorrectLength("LT", "123"));
    }

    @Test
    void checkPrefixByCountryCode() {
        assertFalse(phoneValidator.hasCorrectPrefix("LT", "+371010231212123"));
    }
}
