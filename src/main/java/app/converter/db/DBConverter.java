package app.converter.db;

import app.dto.EntityDTO;

import java.sql.SQLException;
import java.util.List;

public abstract class DBConverter
{
    public abstract List<String> getTableNames(String connectionString,String user,String password) throws SQLException;
    public abstract List<EntityDTO> getEntities(String connectionString,String user,String password,List<String> tableNames) throws SQLException;
}
