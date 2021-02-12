package de.mueller.patrick.database.dao;

import de.mueller.patrick.database.connection.ConnectionFactory;
import de.mueller.patrick.database.statements.CountRecordsStatement;
import de.mueller.patrick.database.statements.DeleteAllRecordsStatement;
import de.mueller.patrick.database.statements.InsertMultipleRecordsStatement;
import de.mueller.patrick.database.statements.InsertSingleRecordStatement;
import de.mueller.patrick.models.FakeDataRecord;

import java.sql.*;
import java.util.List;

public class FakeDataDaoImpl implements IFakeDataDao
{
    @Override
    public boolean insertSingleRecord( final FakeDataRecord record )
    {
        return new InsertSingleRecordStatement( record ).execute( );
    }

    @Override
    public boolean insertMultipleRecords( final List< FakeDataRecord > records )
    {
        return new InsertMultipleRecordsStatement( records ).execute( );
    }

    @Override
    public boolean deleteAllRecords( )
    {
        return new DeleteAllRecordsStatement( ).execute( );
    }

    @Override
    public int countRecords( )
    {
        return new CountRecordsStatement( ).execute( );
    }
}