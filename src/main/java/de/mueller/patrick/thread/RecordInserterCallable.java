package de.mueller.patrick.thread;

import de.mueller.patrick.database.dao.FakeDataDaoImpl;
import de.mueller.patrick.generator.GenerateMultipleFakeDataRecords;
import de.mueller.patrick.models.FakeDataRecord;
import de.mueller.patrick.models.reports.CommitReport;
import de.mueller.patrick.models.reports.ThreadReport;
import de.mueller.patrick.util.StopWatch;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class RecordInserterCallable implements Callable< ThreadReport >
{
    private final int threadNumber;
    private final int numberOfCommitsPerThread;
    private final int numberOfInsertStatementsPerCommit;

    public RecordInserterCallable(
            final int threadNumber,
            final int numberOfCommitsPerThread,
            final int numberOfInsertStatementsPerCommit )
    {
        this.threadNumber = threadNumber;
        this.numberOfCommitsPerThread = numberOfCommitsPerThread;
        this.numberOfInsertStatementsPerCommit = numberOfInsertStatementsPerCommit;
    }

    @Override
    public ThreadReport call( )
    {
        Instant threadStart = Instant.now( );

        List< CommitReport > commitReports = new LinkedList<>( );

        for ( int commitNumber = 1; commitNumber <= this.numberOfCommitsPerThread; commitNumber++ )
        {
            Instant startCommit = Instant.now( );

            insertMultipleRecords( );

            Instant finishCommit = Instant.now( );

            long commitRuntimeInMillis = calculateRuntimeInMillis( startCommit, finishCommit );

            CommitReport commitReport = createCommitReport( commitNumber, commitRuntimeInMillis );

            commitReports.add( commitReport );
        }

        Instant threadEnd = Instant.now( );

        long threadRuntimeInMillis = calculateRuntimeInMillis( threadStart, threadEnd );

        return createThreadReport( commitReports, threadRuntimeInMillis );
    }

    private void insertMultipleRecords( )
    {
        List< FakeDataRecord > records = generateMultipleRecords( );

        new FakeDataDaoImpl( ).insertMultipleRecords( records );
    }

    private List< FakeDataRecord > generateMultipleRecords( )
    {
        return new GenerateMultipleFakeDataRecords( ).generateRecords( this.numberOfInsertStatementsPerCommit );
    }

    private long calculateRuntimeInMillis( final Instant start, final Instant end )
    {
        return new StopWatch( start, end ).computeDurationInMillis( );
    }

    private CommitReport createCommitReport( final int commitNumber, final long commitRuntimeInMillis )
    {
        CommitReport commitReport = new CommitReport( );
        commitReport.setCommitNumber( commitNumber );
        commitReport.setNumberOfInsertedRecords( this.numberOfInsertStatementsPerCommit );
        commitReport.setCommitRuntimeInMillis( commitRuntimeInMillis );

        return commitReport;
    }

    private ThreadReport createThreadReport(
            final List< CommitReport > commitReports,
            final long threadRuntimeInMillis )
    {
        ThreadReport threadReport = new ThreadReport( );
        threadReport.setThreadNumber( this.threadNumber );
        threadReport.setThreadRuntimeInMillis( threadRuntimeInMillis );
        threadReport.setCommitReports( commitReports );

        return threadReport;
    }
}