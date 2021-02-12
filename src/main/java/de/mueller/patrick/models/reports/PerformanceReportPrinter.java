package de.mueller.patrick.models.reports;

import java.text.DecimalFormat;

public class PerformanceReportPrinter
{
    private final long programRuntimeInMillis;
    private final double programThroughput;
    private final PerformanceReport performanceReport;

    public PerformanceReportPrinter(
            final long programRuntimeInMillis,
            final double programThroughput,
            final PerformanceReport performanceReport )
    {
        this.programRuntimeInMillis = programRuntimeInMillis;
        this.programThroughput = programThroughput;
        this.performanceReport = performanceReport;
    }

    public void print( )
    {
        printMessage( "*** PERFORMANCE REPORT ***" );
        printMessage( "Total runtime: " + this.programRuntimeInMillis + "ms" );
        printMessage( "Total throughput: " + format( this.programThroughput ) + " records/sec" );
        printMessage( "" );
        printMessage( "Total runtime of fastest thread: "
                + this.performanceReport.getTotalRuntimeOfFastestThread( ) + "ms" );
        printMessage( "Total runtime of slowest thread: "
                + this.performanceReport.getTotalRuntimeOfSlowestThread( ) + "ms" );
        printMessage( "" );
        printMessage( "Average throughput of all threads: "
                + format( this.performanceReport.getAverageThroughputOfAllThreads( ) ) + " records/sec" );
        printMessage( "Throughput of fastest thread: "
                + format( this.performanceReport.getThroughputOfFastestThread( ) ) + " records/sec" );
        printMessage( "Throughput of slowest thread: "
                + format( this.performanceReport.getThroughputOfSlowestThread( ) ) + " records/sec" );
        printMessage( "" );
        printMessage( "Average throughput of all commits: "
                + format( this.performanceReport.getAverageThroughputOfAllCommits( ) ) + " records/sec" );
        printMessage( "Throughput of fastest commit: "
                + format( this.performanceReport.getThroughputOfFastestCommit( ) ) + " records/sec" );
        printMessage( "Throughput of slowest commit: "
                + format( this.performanceReport.getThroughputOfSlowestCommit( ) ) + " records/sec" );
        printMessage( "" );
    }

    private void printMessage( final String message )
    {
        System.out.println( message );
    }

    private String format( final double value )
    {
        return new DecimalFormat( "0.00" ).format( value );
    }

}