package app.converter;

import app.dto.EntityDTO;
import app.dto.EntityFieldDTO;
import app.util.DataTypeUtil;
import app.util.NameUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PostgresConverter
{
    public void convert(String connectionString,String user,String password,String path) throws SQLException
    {
        Connection connection=DriverManager.getConnection(connectionString, user, password);
        List<String> tableNames=getTableNames(connection);
        List<EntityDTO> entities=getEntities(connection,tableNames);
    }
    private List<EntityDTO> getEntities(Connection connection,List<String> tableNames) throws SQLException
    {
        List<EntityDTO> entities=new LinkedList<>();
        for(String tableName: tableNames)
        {
            List<EntityFieldDTO> fields=new LinkedList<>();
            EntityDTO entityDTO=new EntityDTO(NameUtil.convertTableNameToEntityName(tableName),fields);
            PreparedStatement preparedStatement=connection.prepareStatement(
                    "select column_name,data_type,character_maximum_length from information_schema.columns where table_name=?");
            preparedStatement.setString(1,tableName);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String name=resultSet.getString(1);
                String type=resultSet.getString(2);
                if(name.endsWith("_id"))
                {
                    String tmp=name.substring(0,name.length()-3);
                    name=NameUtil.convertColumnToFieldName(tmp);
                    type=NameUtil.convertTableNameToEntityName(tmp);
                }
                else
                    type=DataTypeUtil.convert(type);
                fields.add(new EntityFieldDTO(name,type));
            }
            entities.add(entityDTO);
        }
        return entities;
    }
    private List<String> getTableNames(Connection connection) throws SQLException
    {
        PreparedStatement preparedStatement=connection.prepareStatement(
                "SELECT table_name FROM information_schema.tables WHERE table_type='BASE TABLE' and table_schema='public'");
        ResultSet resultSet=preparedStatement.executeQuery();
        List<String> tableNames=new LinkedList<>();
        while(resultSet.next())
            tableNames.add(resultSet.getString(1));
        return tableNames;
    }
}