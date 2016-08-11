package guru.bitman.fictionalvieira.server;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PropertyParserTest
{

    private static final String MY_PROPERTY = "myProperty";
    private static final String MY_PROPERTY_VALUE = "xxx.yyy#zzz";
    private static final String MALFORMED_PROPERTY = "myProperty2";
    private static final String INVALID_VALUE = "CLASS.METHOD";
    private static final String NON_EXISTENT_PROPERTY = "NonExistentProperty";
    private static String myPropertyValueBackup;
    private static String myProperty2ValueBackup;

    @BeforeClass
    public static void setup() {
        myPropertyValueBackup = System.getProperty(MY_PROPERTY);
        System.setProperty(MY_PROPERTY, MY_PROPERTY_VALUE);
        myProperty2ValueBackup = System.getProperty(MY_PROPERTY);
        System.setProperty(MALFORMED_PROPERTY, INVALID_VALUE);
    }

    @AfterClass
    public static void tearDown() {
        if (myPropertyValueBackup != null) {
            System.setProperty(MY_PROPERTY, myPropertyValueBackup);
        }
    }

    @Test
    public void testNotNullConstructor() {
        assertNotNull( new PropertyParser(MY_PROPERTY, "a.b.c#d") );
    }


    @Test
    public void testNonExistentPropertyParsesDefaultValue() {
        PropertyParser parser = new PropertyParser(NON_EXISTENT_PROPERTY, "a.b.c#d").parse();
        assertEquals("a.b.c", parser.getClazz());
        assertEquals("d", parser.getMethod());
    }


    @Test
    public void testMalformedPropertyParsesDefaultValue() {
        PropertyParser parser = new PropertyParser(MALFORMED_PROPERTY, "a.b.c#d").parse();
        assertEquals("a.b.c", parser.getClazz());
        assertEquals("d", parser.getMethod());
    }


    @Test
    public void testExistingPropertyParsesDefaultValue() {
        PropertyParser parser = new PropertyParser(MY_PROPERTY, "a.b.c#d").parse();
        assertEquals("xxx.yyy", parser.getClazz());
        assertEquals("zzz", parser.getMethod());
    }


}
