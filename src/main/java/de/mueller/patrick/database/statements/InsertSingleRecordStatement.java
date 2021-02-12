package de.mueller.patrick.database.statements;

import de.mueller.patrick.models.FakeDataRecord;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSingleRecordStatement extends AbstractInsertRecordStatement
{
    private final FakeDataRecord record;

    public InsertSingleRecordStatement( final FakeDataRecord record )
    {
        this.record = record;
    }

    @Override
    protected void setParametersInPreparedStatement( final PreparedStatement preparedStatement ) throws SQLException
    {
        setParametersUsingRecord( preparedStatement, this.record );
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