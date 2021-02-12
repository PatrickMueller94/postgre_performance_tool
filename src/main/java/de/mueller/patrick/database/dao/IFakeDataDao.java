package de.mueller.patrick.database.dao;

import de.mueller.patrick.models.FakeDataRecord;

import java.util.List;

public interface IFakeDataDao
{
    boolean insertSingleRecord( final FakeDataRecord record );

    boolean insertMultipleRecords( final List< FakeDataRecord > records );

    boolean deleteAllRecords( );

    int countRecords( );
}