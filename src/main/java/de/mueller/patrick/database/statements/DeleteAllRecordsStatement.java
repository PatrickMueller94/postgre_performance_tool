package de.mueller.patrick.database.statements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllRecordsStatement extends AbstractStatement< Boolean >
{
    @Override
    protected String defineSqlStatement( )
    {
        return "TRUNCATE TABLE fake_data;";
    }

    @Override
    protected Boolean executeQuery( final PreparedStatement preparedStatement ) throws SQLException
    {
        int i = preparedStatement.executeUpdate( );

        boolean returnValue = false;

        if ( i == 1 )
        {
            returnValue = true;
        }

        return returnValue;
    }
}