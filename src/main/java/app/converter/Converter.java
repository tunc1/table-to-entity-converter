package app.converter;

import app.converter.db.DBConverter;
import app.dto.EntityDTO;
import app.util.EntityUtil;
import app.util.IOUtil;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Converter
{
    private DBConverter converter;
    public Converter(DBConverter converter)
    {
        this.converter=converter;
    }
    public void convert(String connectionString,String user,String password,String path) throws SQLException, IOException
    {
        List<String> tableNames=converter.getTableNames(connectionString,user,password);
        List<EntityDTO> entities=converter.getEntities(connectionString,user,password,tableNames);
        for(EntityDTO entity: entities)
        {
            String content=EntityUtil.generateContent(entity);
            IOUtil.writeToFile(content,entity.getEntityName(),path);
        }
    }
}