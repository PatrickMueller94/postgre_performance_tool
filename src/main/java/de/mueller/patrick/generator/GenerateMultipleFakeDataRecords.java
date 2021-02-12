package de.mueller.patrick.generator;

import de.mueller.patrick.models.FakeDataRecord;

import java.util.LinkedList;
import java.util.List;

public class GenerateMultipleFakeDataRecords
{
    public List< FakeDataRecord > generateRecords( final int numberOfRecords )
    {
        List< FakeDataRecord > records = new LinkedList<>( );

        for ( int i = 0; i < numberOfRecords; i++ )
        {
            records.add( generateRecord( ) );
        }

        return records;
    }

    private FakeDataRecord generateRecord( )
    {
        return new GenerateSingleFakeDataRecord( ).generateRecord( );
    }
}