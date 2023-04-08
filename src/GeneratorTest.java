import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.xml.stream.events.Characters;

class GeneratorTest {

    private final String test = "test";
    private final Password testPassword= new Password(test);

    @Test
    void test1() {
        assertEquals(test, testPassword.Value);
    }


}