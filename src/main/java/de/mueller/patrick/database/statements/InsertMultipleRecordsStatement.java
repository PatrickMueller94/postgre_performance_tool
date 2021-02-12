package de.mueller.patrick.database.statements;

import de.mueller.patrick.models.FakeDataRecord;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class InsertMultipleRecordsStatement extends AbstractInsertRecordStatement
{
    private final List< FakeDataRecord > records;

    public InsertMultipleRecordsStatement( final List< FakeDataRecord > records )
    {
        this.records = records;
    }

    @Override
    protected void setParametersInPreparedStatement( final PreparedStatement preparedStatement ) throws SQLException
    {
        for ( FakeDataRecord record : this.records )
        {
            setParametersUsingRecord( preparedStatement, record );

            preparedStatement.addBatch( );
        }
    }

    @Override
    protected Boolean executeQuery( final PreparedStatement preparedStatement ) throws SQLException
    {
        int[] updateCounts = preparedStatement.executeBatch( );

        return checkUpdateCounts( updateCounts );
    }

    public boolean checkUpdateCounts( final int[] updateCounts )
    {
        boolean areUpdateCountsOk = true;

        for ( int updateCount : updateCounts )
        {
            if ( updateCount >= 0 )
            {
                //System.out.println( "OK; updateCount=" + updateCount );
            }
            else if ( updateCount == Statement.SUCCESS_NO_INFO )
            {
                //System.out.println( "OK; updateCount=Statement.SUCCESS_NO_INFO" );
            }
            else if ( updateCount == Statement.EXECUTE_FAILED )
            {
                //System.out.println( "Failure; updateCount=Statement.EXECUTE_FAILED" );
                areUpdateCountsOk = false;
            }
        }

        return areUpdateCountsOk;
    }
}