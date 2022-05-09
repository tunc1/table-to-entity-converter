package app.util;

public class DataTypeUtil
{
    public static String convert(String dataType)
    {
        switch(dataType)
        {
            case "bigint":
                return "Long";
            case "character varying":
                return "String";
            case "boolean":
                return "boolean";
            case "timestamp without time zone":
                return "Date";
            default:
                throw new IllegalArgumentException(dataType);
        }
    }
}