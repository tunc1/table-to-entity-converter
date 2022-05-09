package app.util;

public class NameUtil
{
    public static String convertColumnToFieldName(String columnName)
    {
        String[] s=columnName.split("_");
        for(int i=1;i<s.length;i++)
            s[i]=s[i].substring(0,1).toUpperCase()+s[i].substring(1);
        return String.join("",s);
    }
    public static String convertTableNameToEntityName(String tableName)
    {
        String s=convertColumnToFieldName(tableName);
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }
}