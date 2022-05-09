package app;

import app.converter.PostgresConverter;

import java.sql.SQLException;

public class Main
{
    public static void main(String[] args)
    {
        PostgresConverter converter=new PostgresConverter();
        try
        {
            converter.convert("jdbc:postgresql://localhost:5432/job_postings","postgres","password","C:\\Users\\User\\Documents\\e");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}