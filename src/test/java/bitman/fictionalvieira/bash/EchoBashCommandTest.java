package bitman.fictionalvieira.bash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EchoBashCommandTest
{
    @Test
    public void testConstructorDoesnThrowException() {
        new EchoBashCommand("");
    }

    @Test
    public void testConstructorNotNull() {
        assertNotNull(  new EchoBashCommand("") );
    }

    @Test
    public void test() {
      assertEquals( "echo \"\"", new EchoBashCommand("").getCode() );
    }
}
