package de.mueller.patrick.configuration;

public class ToolConfiguration
{
    private final int numberOfThreads;
    private final int numberOfInsertStatementsPerCommit;
    private final int numberOfCommitsPerThread;
    private final int totalNumberOfInsertStatements;

    public ToolConfiguration(
            final int numberOfThreads,
            final int numberOfInsertStatementsPerCommit,
            final int totalNumberOfInsertStatements,
            final int numberOfCommitsPerThread )
    {
        this.numberOfThreads = numberOfThreads;
        this.numberOfInsertStatementsPerCommit = numberOfInsertStatementsPerCommit;
        this.totalNumberOfInsertStatements = totalNumberOfInsertStatements;
        this.numberOfCommitsPerThread = numberOfCommitsPerThread;
    }

    public void printConfiguration( )
    {
        printMessage( "*** Tool configuration ***" );
        printMessage( "(provided) Number of threads: " + this.numberOfThreads );
        printMessage( "(provided) Number of insert statements per commit: " + this.numberOfInsertStatementsPerCommit );
        printMessage( "(provided) Total number of insert statements: " + this.totalNumberOfInsertStatements );
        printMessage( "(computed) Number of commits per thread: " + this.numberOfCommitsPerThread );
    }

    private void printMessage( final String message )
    {
        System.out.println( message );
    }

    public int getNumberOfThreads( )
    {
        return this.numberOfThreads;
    }

    public int getNumberOfInsertStatementsPerCommit( )
    {
        return this.numberOfInsertStatementsPerCommit;
    }

    public int getNumberOfCommitsPerThread( )
    {
        return this.numberOfCommitsPerThread;
    }

    public int getTotalNumberOfInsertStatements( )
    {
        return this.totalNumberOfInsertStatements;
    }
}