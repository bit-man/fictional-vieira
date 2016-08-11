package guru.bitman.fictionalvieira.server;

class PropertyParser
{
    private String propertyName;
    private String defaultValue;
    private String clazz;
    private String method;

    PropertyParser(String propertyName, String defaultValue)
    {
        this.propertyName = propertyName;
        this.defaultValue = defaultValue;
    }

    String getClazz()
    {
        return clazz;
    }

    String getMethod()
    {
        return method;
    }

    PropertyParser parse()
    {
        final String propertyValue = System.getProperty(propertyName, defaultValue);
        extractClassAndMethod(propertyValue);
        return this;
    }

    private void extractClassAndMethod(String propertyValue)
    {
        try
        {
            splitPropertyValue(propertyValue);
        } catch (Exception e)
        {
            splitPropertyValue(defaultValue);
        }
    }

    private void splitPropertyValue(String propertyValue)
    {
        String[] split = propertyValue.split("#");
        clazz = split[0];
        method = split[1];
    }
}
