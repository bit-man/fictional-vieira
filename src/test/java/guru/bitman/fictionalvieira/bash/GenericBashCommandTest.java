package guru.bitman.fictionalvieira.bash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class GenericBashCommandTest {

	@Test
	public void testConstructorDontThrowException() {
		new GenericBashCommand("");
	}

	@Test
	public void testNotNullConstructor() {
		assertNotNull(new GenericBashCommand(""));
	}

	@Test
	public void test() {
		assertEquals("myCommand arg1 arg2", new GenericBashCommand("myCommand arg1 arg2").getCode() );
	}

}
