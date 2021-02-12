package de.mueller.patrick.database.statements;

import de.mueller.patrick.models.FakeDataRecord;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractInsertRecordStatement extends AbstractStatement< Boolean >
{
    @Override
    protected String defineSqlStatement( )
    {
        return "INSERT INTO fake_data (" +
                "column1, column2, column3, column4, column5, " +
                "column6, column7, column8, column9, column10) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";
    }

    protected void setParametersUsingRecord( final PreparedStatement ps, final FakeDataRecord record ) throws SQLException
    {
        ps.setString( 1, record.getColumn1( ) );
        ps.setString( 2, record.getColumn2( ) );
        ps.setString( 3, record.getColumn3( ) );
        ps.setString( 4, record.getColumn4( ) );
        ps.setString( 5, record.getColumn5( ) );
        ps.setString( 6, record.getColumn6( ) );
        ps.setString( 7, record.getColumn7( ) );
        ps.setString( 8, record.getColumn8( ) );
        ps.setString( 9, record.getColumn9( ) );
        ps.setString( 10, record.getColumn10( ) );
    }
}