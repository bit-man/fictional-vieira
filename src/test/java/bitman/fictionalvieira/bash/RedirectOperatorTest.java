package bitman.fictionalvieira.bash;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RedirectOperatorTest {
	@Test
	public void testConstructorDontThrowException() {
		new RedirectOperator(new GenericBashCommand("cmd"), "file", RedirectOperator.Type.OVERWRITE);
	}

	@Test
	public void testNotNullConstructor() {
		assertNotNull(new RedirectOperator(new GenericBashCommand("cmd"), "file", RedirectOperator.Type.OVERWRITE));
	}

	@Test
	public void testOverwriteOperatorInRightPosition() {
		String expectedCode = "cmd > file";
		assertNotNull(expectedCode, new RedirectOperator(new GenericBashCommand("cmd"), "file", RedirectOperator.Type.OVERWRITE).getCode());
	}

	@Test
	public void testAppendOperatorInRightPosition() {
		String expectedCode = "cmd >> file";
		assertNotNull(expectedCode, new RedirectOperator(new GenericBashCommand("cmd"), "file", RedirectOperator.Type.APPEND).getCode());
	}

}
