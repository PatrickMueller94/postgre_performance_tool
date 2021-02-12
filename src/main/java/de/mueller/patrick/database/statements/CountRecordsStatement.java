package de.mueller.patrick.database.statements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountRecordsStatement extends AbstractStatement< Integer >
{
    @Override
    protected String defineSqlStatement( )
    {
        return "SELECT COUNT (*) AS row_count FROM " + "fake_data" + ";";
    }

    @Override
    protected Integer executeQuery( final PreparedStatement preparedStatement ) throws SQLException
    {
        ResultSet resultSet = preparedStatement.executeQuery( );

        resultSet.next( );

        int rowCount = resultSet.getInt( "row_count" );

        resultSet.close( );

        return rowCount;
    }
}