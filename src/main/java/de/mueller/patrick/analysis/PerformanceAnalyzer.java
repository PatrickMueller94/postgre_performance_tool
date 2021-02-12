package de.mueller.patrick.analysis;

import de.mueller.patrick.models.reports.CommitReport;
import de.mueller.patrick.models.reports.PerformanceReport;
import de.mueller.patrick.models.reports.ThreadReport;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PerformanceAnalyzer
{
    private final List< ThreadReport > threadReports;

    public PerformanceAnalyzer( final List< ThreadReport > threadReports )
    {
        this.threadReports = threadReports;
    }

    public PerformanceReport analyzeThreadPerformances( )
    {
        final long runtimeOfFastestThread = calculateRuntimeOfFastestThread( );
        final long runtimeOfSlowestThread = calculateRuntimeOfSlowestThread( );

        final double averageThroughputOfAllThreads = calculateAverageThroughputOfAllThreads( );
        final double throughputOfFastestThread = calculateThroughputOfFastestThread( );
        final double throughputOfSlowestThread = calculateThroughputOfSlowestThread( );

        final double averageThroughputOfAllCommits = calculateAverageThroughputOfAllCommits( );
        final double throughputOfFastestCommit = calculateThroughputOfFastestCommit( );
        final double throughputOfSlowestCommit = calculateThroughputOfSlowestCommit( );

        PerformanceReport performanceReport = new PerformanceReport( );
        performanceReport.setTotalRuntimeOfFastestThread( runtimeOfFastestThread );
        performanceReport.setTotalRuntimeOfSlowestThread( runtimeOfSlowestThread );

        performanceReport.setAverageThroughputOfAllThreads( averageThroughputOfAllThreads );
        performanceReport.setThroughputOfFastestThread( throughputOfFastestThread );
        performanceReport.setThroughputOfSlowestThread( throughputOfSlowestThread );

        performanceReport.setAverageThroughputOfAllCommits( averageThroughputOfAllCommits );
        performanceReport.setThroughputOfFastestCommit( throughputOfFastestCommit );
        performanceReport.setThroughputOfSlowestCommit( throughputOfSlowestCommit );

        return performanceReport;
    }

    private long calculateRuntimeOfFastestThread( )
    {
        return getThreadReportOfFastestThread( ).getThreadRuntimeInMillis( );
    }

    private ThreadReport getThreadReportOfFastestThread( )
    {
        return this.threadReports.stream( )
                .min( Comparator.comparing( ThreadReport::getThreadRuntimeInMillis ) )
                .orElseThrow( NoSuchElementException::new );
    }

    private long calculateRuntimeOfSlowestThread( )
    {
        return getThreadReportOfSlowestThread( ).getThreadRuntimeInMillis( );
    }

    private ThreadReport getThreadReportOfSlowestThread( )
    {
        return this.threadReports.stream( )
                .max( Comparator.comparing( ThreadReport::getThreadRuntimeInMillis ) )
                .orElseThrow( NoSuchElementException::new );
    }

    private double calculateAverageThroughputOfAllThreads( )
    {
        return this.threadReports.stream( )
                .mapToDouble( ThreadReport::calculateThreadThroughput )
                .average( )
                .orElse( Double.NaN );
    }

    private double calculateThroughputOfFastestThread( )
    {
        return getThreadReportOfFastestThread( ).calculateThreadThroughput( );
    }

    private double calculateThroughputOfSlowestThread( )
    {
        return getThreadReportOfSlowestThread( ).calculateThreadThroughput( );
    }

    private double calculateAverageThroughputOfAllCommits( )
    {
        return getAllCommitReports( ).stream( )
                .mapToDouble( CommitReport::calculateCommitThroughput )
                .average( )
                .orElse( Double.NaN );
    }

    private List< CommitReport > getAllCommitReports( )
    {
        return this.threadReports.stream( )
                .map( ThreadReport::getCommitReports )
                .flatMap( List::stream )
                .collect( Collectors.toList( ) );
    }

    private double calculateThroughputOfFastestCommit( )
    {
        return getCommitReportOfFastestCommit( ).calculateCommitThroughput( );
    }

    private CommitReport getCommitReportOfFastestCommit( )
    {
        return getAllCommitReports( ).stream( )
                .min( Comparator.comparing( CommitReport::getCommitRuntimeInMillis ) )
                .orElseThrow( NoSuchElementException::new );
    }

    private double calculateThroughputOfSlowestCommit( )
    {
        return getCommitReportOfSlowestCommit( ).calculateCommitThroughput( );
    }

    private CommitReport getCommitReportOfSlowestCommit( )
    {
        return getAllCommitReports( ).stream( )
                .max( Comparator.comparing( CommitReport::getCommitRuntimeInMillis ) )
                .orElseThrow( NoSuchElementException::new );
    }
}