package app.util.db;

import app.dto.EntityDTO;
import app.dto.EntityFieldDTO;
import app.util.DataTypeUtil;
import app.util.NameUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PostgresDBUtil
{
    private static Connection getConnection(String connectionString,String user,String password) throws SQLException
    {
        return DriverManager.getConnection(connectionString, user, password);
    }
    public static List<String> getTableNames(String connectionString,String user,String password) throws SQLException
    {
        Connection connection=getConnection(connectionString, user, password);
        PreparedStatement preparedStatement=connection.prepareStatement(
                "SELECT table_name FROM information_schema.tables WHERE table_type='BASE TABLE' and table_schema='public'");
        ResultSet resultSet=preparedStatement.executeQuery();
        List<String> tableNames=new LinkedList<>();
        while(resultSet.next())
            tableNames.add(resultSet.getString(1));
        return tableNames;
    }
    public static List<EntityDTO> getEntities(String connectionString,String user,String password,List<String> tableNames) throws SQLException
    {
        Connection connection=getConnection(connectionString, user, password);
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
                boolean isFK;
                if(name.endsWith("_id"))
                {
                    isFK=true;
                    String tmp=name.substring(0,name.length()-3);
                    name=NameUtil.convertColumnToFieldName(tmp);
                    type=NameUtil.convertTableNameToEntityName(tmp);
                }
                else
                {
                    isFK=false;
                    type=DataTypeUtil.convert(type);
                    name=NameUtil.convertColumnToFieldName(name);
                }
                fields.add(new EntityFieldDTO(name,type,name.equals("id"),isFK));
            }
            entities.add(entityDTO);
        }
        return entities;
    }
}