import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.xml.stream.events.Characters;

class GeneratorTest {

    private final String test = "test";
    private final Password testPassword= new Password(test);
    private final Generator generator = new Generator();
    private final int length = 5;

    @Test
    void testPasswordValue() {

        assertEquals(testPassword.Value, test);
    }
    @Test
    void setTestPasswordLength(){
        assertEquals(testPassword.Length, test.length());
    }
    @Test
    void noCriteriaSelectedIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    generator.CheckGeneratedPassword(false,false,false,false,length);
                });
    }
    @Test
    void passwordLengthZeroIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    generator.CheckGeneratedPassword(true,true,true,true,0);
                });
    }
    @Test
    void upperTrue(){
        final String upperCase = generator.CheckGeneratedPassword(true,false,false,false,length);
        assertTrue(upperCase.replace("[^A-Z]","")==upperCase);
    }
    @Test
    void lowerTrue(){
        final String lowerCase = generator.CheckGeneratedPassword(false,true,false,false,length);
        assertTrue(lowerCase.replace("[^a-z]","")==lowerCase);
    }
    @Test
    void numberTrue(){
        final String number = generator.CheckGeneratedPassword(false,false,true,false,length);
        assertTrue(number.replace("[^0-9]","")==number);
    }
    @Test
    void symbolTrue(){
        final String symbol = generator.CheckGeneratedPassword(false,false,false,true,length);
        assertTrue(symbol.replace("[A-Za-z0-9]","")==symbol);
    }
    @Test
    void upperLowerTrue(){
        final String upperLower = generator.CheckGeneratedPassword(true,true,false,false,length);
        assertTrue(upperLower.replace("[^A-Za-z]","")==upperLower);
    }
    @Test
    void upperNumberTrue(){
        final String upperNumber = generator.CheckGeneratedPassword(true,false,true,false,length);
        assertTrue(upperNumber.replace("[^A-Z0-9]","")==upperNumber);
    }
    @Test
    void upperSymbolTrue(){
        final String upperSymbol = generator.CheckGeneratedPassword(true,false,false,true,length);
        assertTrue(upperSymbol.replace("[a-z0-9]","")==upperSymbol);
    }
    @Test
    void lowerNumberTrue(){
        final String lowerNumber = generator.CheckGeneratedPassword(false,true,true,false,length);
        assertTrue(lowerNumber.replace("[^a-z0-9]","")==lowerNumber);
    }
    @Test
    void lowerSymbolTrue(){
        final String lowerSymbol = generator.CheckGeneratedPassword(false,true,false,true,length);
        assertTrue(lowerSymbol.replace("[A-Z0-9]","")==lowerSymbol);
    }
    @Test
    void numberSymbolTrue(){
        final String numberSymbol = generator.CheckGeneratedPassword(false,false,true,true,length);
        assertTrue(numberSymbol.replace("[A-Za-z]","")==numberSymbol);
    }
    @Test
    void upperLowerNumberTrue(){
        final String upperLowerNumber = generator.CheckGeneratedPassword(true,true,true,false,length);
        assertTrue(upperLowerNumber.replace("[^A-Za-z0-9]","")==upperLowerNumber);
    }
    @Test
    void upperLowerSymbolTrue(){
        final String upperLowerSymbol = generator.CheckGeneratedPassword(true,true,false,true,length);
        assertTrue(upperLowerSymbol.replace("[0-9]","")==upperLowerSymbol);
    }
    @Test
    void lowerNumberSymbolTrue(){
        final String lowerNumberSymbol = generator.CheckGeneratedPassword(false,true,true,true,length);
        assertTrue(lowerNumberSymbol.replace("[A-Z]","")==lowerNumberSymbol);
    }
    @Test
    void upperLowerNumberSymbolTrue(){
        final String upperLowerNumberSymbol = generator.CheckGeneratedPassword(true,true,true,true,length);
        assertTrue(upperLowerNumberSymbol.replace("[A-Za-z0-9]","").replace("[^A-Za-z0-9]","")==upperLowerNumberSymbol);
    }

    @Test
    void strengthOne(){
        final String scoreOneString = generator.CheckGeneratedPassword(true,false,false,false,1);
        final Password scoreOnePassword = new Password(scoreOneString);
        assertEquals(scoreOnePassword.Strength(),1);
    }

    @Test
    void strengthTwo(){
        final String scoreOneString = generator.CheckGeneratedPassword(true,false,false,false,10);
        final Password scoreOnePassword = new Password(scoreOneString);
        assertEquals(scoreOnePassword.Strength(),2);
    }

    @Test
    void strengthThree(){
        final String scoreOneString = generator.CheckGeneratedPassword(true,true,true,false,4);
        final Password scoreOnePassword = new Password(scoreOneString);
        assertEquals(scoreOnePassword.Strength(),3);
    }

    @Test
    void strengthFour(){
        final String scoreOneString = generator.CheckGeneratedPassword(true,true,true,false,10);
        final Password scoreOnePassword = new Password(scoreOneString);
        assertEquals(scoreOnePassword.Strength(),4);
    }

    @Test
    void strengthFive(){
        final String scoreOneString = generator.CheckGeneratedPassword(true,true,true,true,10);
        final Password scoreOnePassword = new Password(scoreOneString);
        assertEquals(scoreOnePassword.Strength(),5);
    }


}