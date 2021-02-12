package de.mueller.patrick;

import de.mueller.patrick.analysis.PerformanceAnalyzer;
import de.mueller.patrick.models.reports.PerformanceReport;
import de.mueller.patrick.models.reports.PerformanceReportPrinter;
import de.mueller.patrick.configuration.ToolConfiguration;
import de.mueller.patrick.configuration.ToolConfigurationCreator;
import de.mueller.patrick.database.dao.FakeDataDaoImpl;
import de.mueller.patrick.models.reports.ThreadReport;
import de.mueller.patrick.thread.ThreadExecutor;
import de.mueller.patrick.util.StopWatch;
import de.mueller.patrick.util.ThroughputCalculator;

import java.time.Instant;
import java.util.List;

public class PerformanceTool
{
    public void run( )
    {
        deleteAllRecords( );

        ToolConfiguration configuration = createToolConfiguration( );

        printToolConfiguration( configuration );

        Instant programStart = Instant.now( );

        List< ThreadReport > threadReports = executeThreads( configuration );

        Instant programEnd = Instant.now( );

        long programRuntimeInMillis = calculateProgramRuntimeInMillis( programStart, programEnd );

        double programThroughput =
                calculateProgramThroughput( configuration.getTotalNumberOfInsertStatements( ), programRuntimeInMillis );

        PerformanceReport performanceReport = createPerformanceReport( threadReports );

        printThreadReports( threadReports );

        printPerformanceReport( programRuntimeInMillis, programThroughput, performanceReport );

        countRecordsInDatabase( );
    }

    private void deleteAllRecords( )
    {
        new FakeDataDaoImpl( ).deleteAllRecords( );
    }

    private ToolConfiguration createToolConfiguration( )
    {
        return new ToolConfigurationCreator( ).create( );
    }

    private void printToolConfiguration( final ToolConfiguration configuration )
    {
        configuration.printConfiguration( );
    }

    private List< ThreadReport > executeThreads( final ToolConfiguration configuration )
    {
        return new ThreadExecutor( ).execute( configuration );
    }

    private long calculateProgramRuntimeInMillis( final Instant start, final Instant end )
    {
        return new StopWatch( start, end ).computeDurationInMillis( );
    }

    private double calculateProgramThroughput(
            final int totalNumberOfInsertStatements,
            final long programRuntimeInMillis )
    {
        return new ThroughputCalculator( totalNumberOfInsertStatements, programRuntimeInMillis ).calculate( );
    }

    private PerformanceReport createPerformanceReport( final List< ThreadReport > threadReports )
    {
        return new PerformanceAnalyzer( threadReports ).analyzeThreadPerformances( );
    }

    private void printPerformanceReport(
            final long programRuntimeInMillis,
            final double programThroughput,
            final PerformanceReport performanceReport )
    {
        new PerformanceReportPrinter( programRuntimeInMillis, programThroughput, performanceReport ).print( );
    }

    private void printThreadReports( final List< ThreadReport > threadReports )
    {
        System.out.println( "---- Thread reports" );
        System.out.println( "size: " + threadReports.size( ) + "\n" );

        for ( ThreadReport threadReport : threadReports )
        {
            threadReport.print( );
        }
    }

    private void countRecordsInDatabase( )
    {
        int numberOfRecords = new FakeDataDaoImpl( ).countRecords( );

        System.out.println( "Number of records in database: " + numberOfRecords );
    }
}