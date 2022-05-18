package app.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameUtilTest
{
    @Test
    void convertColumnToFieldName()
    {
        String expected="camelCaseFieldName";
        String actual=NameUtil.convertColumnToFieldName("camel_case_field_name");
        assertEquals(expected,actual);
    }
    @Test
    void convertTableNameToEntityName()
    {
        String expected="CamelCaseFieldName";
        String actual=NameUtil.convertTableNameToEntityName("camel_case_field_name");
        assertEquals(expected,actual);
    }
}