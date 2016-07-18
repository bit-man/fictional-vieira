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
        assertEquals( "name=value", new EnvironmentVariableCommand("name", "value").getCode() );
    }

    @Test
    public void testOnlyName() {
        assertEquals( "name=", new EnvironmentVariableCommand("name").getCode() );
    }

    @Test
    public void testRightNameAndValuesCreatedOnExport() {
        assertEquals( "export name=value", new EnvironmentVariableCommand("name", "value", true).getCode() );
    }
}
