package de.mueller.patrick.models.reports;

import de.mueller.patrick.util.ThroughputCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class ThreadReport
{
    private int threadNumber;
    private long threadRuntimeInMillis;
    private List< CommitReport > commitReports;

    public double calculateThreadThroughput( )
    {
        return new ThroughputCalculator(
                calculateTotalNumberOfInsertedRecords( ),
                this.threadRuntimeInMillis ).calculate( );
    }

    public int calculateTotalNumberOfInsertedRecords( )
    {
        return this.commitReports
                .stream( )
                .map( CommitReport::getNumberOfInsertedRecords )
                .mapToInt( Integer::intValue )
                .sum( );
    }

    @Override
    public String toString( )
    {
        return "--- Thread report \n" +
                "Thread number: " + this.threadNumber + "\n" +
                "Thread runtime in millis: " + this.threadRuntimeInMillis + "\n" +
                "-- Commit reports: " + "\n" +
                commitPerformancesToString( ) + "\n";
    }

    private String commitPerformancesToString( )
    {
        return this.commitReports.stream( )
                .map( CommitReport::toString )
                .collect( Collectors.joining( "" ) );
    }

    public void print( )
    {
        System.out.println( this.toString( ) );
    }

    public int getThreadNumber( )
    {
        return this.threadNumber;
    }

    public void setThreadNumber( final int threadNumber )
    {
        this.threadNumber = threadNumber;
    }

    public long getThreadRuntimeInMillis( )
    {
        return this.threadRuntimeInMillis;
    }

    public void setThreadRuntimeInMillis( final long threadRuntimeInMillis )
    {
        this.threadRuntimeInMillis = threadRuntimeInMillis;
    }

    public List< CommitReport > getCommitReports( )
    {
        return this.commitReports;
    }

    public void setCommitReports( final List< CommitReport > commitReports )
    {
        this.commitReports = commitReports;
    }
}