package app.converter;

import app.dto.EntityDTO;
import app.util.EntityUtil;
import app.util.IOUtil;
import app.util.db.PostgresDBUtil;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class PostgresConverter
{
    public void convert(String connectionString,String user,String password,String path) throws SQLException, IOException
    {
        Connection connection=DriverManager.getConnection(connectionString, user, password);
        List<String> tableNames=PostgresDBUtil.getTableNames(connection);
        List<EntityDTO> entities=PostgresDBUtil.getEntities(connection,tableNames);
        for(EntityDTO entity: entities)
        {
            String content=EntityUtil.generateContent(entity);
            IOUtil.writeToFile(content,entity.getEntityName(),path);
        }
    }
}