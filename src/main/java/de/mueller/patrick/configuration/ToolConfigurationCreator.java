package de.mueller.patrick.configuration;

public class ToolConfigurationCreator
{
    public ToolConfiguration create( )
    {
        return new ToolConfiguration(
                getNumberOfThreads( ),
                getNumberOfInsertStatementsPerCommit( ),
                getTotalNumberOfInsertStatements( ),
                getNumberOfCommitsPerThread( ) );
    }

    private int getNumberOfThreads( )
    {
        String numberOfThreadsPropertyName = "number.of.threads";

        final int numberOfThreadsProperty = getSystemProperty( numberOfThreadsPropertyName, "1" );

        if ( numberOfThreadsProperty <= 0 )
        {
            throwException( "The parameter \"" + numberOfThreadsPropertyName + "\" must be greater than 0" );
        }

        return numberOfThreadsProperty;
    }

    private int getSystemProperty( final String propertyName, final String defaultValue )
    {
        return Integer.parseInt( System.getProperty( propertyName, defaultValue ) );
    }

    private void throwException( final String message )
    {
        throw new IllegalArgumentException( message );
    }

    private int getNumberOfInsertStatementsPerCommit( )
    {
        String numberOfInsertStatementsPerCommitPropertyName = "number.of.insert.statements.per.commit";

        final int numberOfInsertStatementsPerCommit =
                getSystemProperty( numberOfInsertStatementsPerCommitPropertyName, "5" );

        if ( numberOfInsertStatementsPerCommit <= 0 )
        {
            throwException( "The parameter \"" +
                    numberOfInsertStatementsPerCommitPropertyName +
                    "\" must be greater than 0" );
        }

        return numberOfInsertStatementsPerCommit;
    }

    private int getTotalNumberOfInsertStatements( )
    {
        String totalNumberOfInsertStatements = "total.number.of.insert.statements";

        int totalNumberOfInsertStatementsProvidedByUser =
                getSystemProperty( totalNumberOfInsertStatements, "10" );

        if ( totalNumberOfInsertStatementsProvidedByUser <= 0 )
        {
            throwException( "The parameter \"" + totalNumberOfInsertStatements + "\" must be greater than 0" );
        }

        int exceedingInsertStatements =
                calculateExceedingInsertStatements( totalNumberOfInsertStatementsProvidedByUser );

        return totalNumberOfInsertStatementsProvidedByUser - exceedingInsertStatements;
    }

    private int calculateExceedingInsertStatements( final int totalNumberOfInsertStatementsProvidedByUser )
    {
        return totalNumberOfInsertStatementsProvidedByUser % ( getNumberOfThreads( ) * getNumberOfInsertStatementsPerCommit( ) );
    }

    private int getNumberOfCommitsPerThread( )
    {
        return getTotalNumberOfInsertStatements( ) / ( getNumberOfThreads( ) * getNumberOfInsertStatementsPerCommit( ) );
    }
}