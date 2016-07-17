package guru.bitman.fictionalvieira.command;


import guru.bitman.fictionalvieira.bash.EnvironmentVariableCommand;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnvironmentVariableCommandTest
{
    @Test
    public void testNotNullObjectFromConstructor() {
        Assert.assertNotNull(new EnvironmentVariableCommand("",""));
    }

    @Test
    public void testNotNullObjectFromConstructorWithNoValue() {
        Assert.assertNotNull(new EnvironmentVariableCommand(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameCannotBeNull() {
        Assert.assertNotNull(new EnvironmentVariableCommand(null, ""));
    }

    @Test
    public void testRightNameAndValuesCreated() {
        TestWriter out = new TestWriter();
        new EnvironmentVariableCommand("name", "value").execute(out);
        assertEquals( "name=value", out.getOut() );
    }

    @Test
    public void testOnlyName() {
        TestWriter out = new TestWriter();
        new EnvironmentVariableCommand("name").execute(out);
        assertEquals( "name=", out.getOut() );
    }

    @Test
    public void testRightNameAndValuesCreatedOnExport() {
        TestWriter out = new TestWriter();
        new EnvironmentVariableCommand("name", "value", true).execute(out);
        assertEquals( "export name=value", out.getOut() );
    }
}
