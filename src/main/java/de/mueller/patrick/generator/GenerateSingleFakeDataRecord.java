package de.mueller.patrick.generator;

import de.mueller.patrick.models.FakeDataRecord;

import java.util.Random;

public class GenerateSingleFakeDataRecord
{
    public FakeDataRecord generateRecord( )
    {
        final FakeDataRecord record = new FakeDataRecord( );

        record.setColumn1( generateRandomString( ) );
        record.setColumn2( generateRandomString( ) );
        record.setColumn3( generateRandomString( ) );
        record.setColumn4( generateRandomString( ) );
        record.setColumn5( generateRandomString( ) );
        record.setColumn6( generateRandomString( ) );
        record.setColumn7( generateRandomString( ) );
        record.setColumn8( generateRandomString( ) );
        record.setColumn9( generateRandomString( ) );
        record.setColumn10( generateRandomString( ) );

        return record;
    }

    private String generateRandomString( )
    {
        final int stringLength = generateStringLength( );

        StringBuilder buffer = new StringBuilder( stringLength );

        for ( int i = 0; i < stringLength; i++ )
        {
            String selectedCharacter = selectRandomCharacterFromAlphabet( );

            buffer.append( selectedCharacter );
        }

        return buffer.toString( );
    }

    private Integer generateStringLength( )
    {
        return ( int ) Math.round( sampleValueFromNormalDistribution( 20.0, 4.0 ) );
    }

    private double sampleValueFromNormalDistribution( final double meanValue, final double standardDeviance )
    {
        return new Random( ).nextGaussian( ) * standardDeviance + meanValue;
    }

    private String selectRandomCharacterFromAlphabet( )
    {
        String alphabetAsString = defineAlphabet( );

        int alphabetLength = alphabetAsString.length( );

        int index = new Random( ).nextInt( alphabetLength );

        char selectedCharacter = alphabetAsString.charAt( index );

        return Character.toString( selectedCharacter );
    }

    private String defineAlphabet( )
    {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + ",.-;:_+?=)(/&";
    }
}