package de.mueller.patrick.models.reports;

import de.mueller.patrick.util.ThroughputCalculator;

public class CommitReport
{
    private int commitNumber;
    private int numberOfInsertedRecords;
    private long commitRuntimeInMillis;

    public double calculateCommitThroughput( )
    {
        return new ThroughputCalculator( this.numberOfInsertedRecords, this.commitRuntimeInMillis ).calculate( );
    }

    @Override
    public String toString( )
    {
        return "- Commit number: " + this.commitNumber + "\n" +
                "Number of inserted records: " + this.numberOfInsertedRecords + "\n" +
                "Commit runtime in millis: " + this.commitRuntimeInMillis + "\n";
    }

    public int getCommitNumber( )
    {
        return this.commitNumber;
    }

    public void setCommitNumber( final int commitNumber )
    {
        this.commitNumber = commitNumber;
    }

    public int getNumberOfInsertedRecords( )
    {
        return this.numberOfInsertedRecords;
    }

    public void setNumberOfInsertedRecords( final int numberOfInsertedRecords )
    {
        this.numberOfInsertedRecords = numberOfInsertedRecords;
    }

    public long getCommitRuntimeInMillis( )
    {
        return this.commitRuntimeInMillis;
    }

    public void setCommitRuntimeInMillis( final long commitRuntimeInMillis )
    {
        this.commitRuntimeInMillis = commitRuntimeInMillis;
    }
}