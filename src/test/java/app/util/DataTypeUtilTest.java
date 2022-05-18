package app.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTypeUtilTest
{
    @Test
    void convertLong()
    {
        String actual=DataTypeUtil.convert("bigint");
        assertEquals("Long",actual);
    }
    @Test
    void convertString()
    {
        String actual=DataTypeUtil.convert("character varying");
        assertEquals("String",actual);
    }
    @Test
    void convertBoolean()
    {
        String actual=DataTypeUtil.convert("boolean");
        assertEquals("boolean",actual);
    }
    @Test
    void convertDate()
    {
        String actual=DataTypeUtil.convert("timestamp without time zone");
        assertEquals("Date",actual);
    }
    @Test
    void convertException()
    {
        assertThrows(IllegalArgumentException.class,()->DataTypeUtil.convert("a"));
    }
}