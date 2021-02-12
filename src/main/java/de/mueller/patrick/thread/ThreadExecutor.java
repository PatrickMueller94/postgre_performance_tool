package de.mueller.patrick.thread;

import de.mueller.patrick.configuration.ToolConfiguration;
import de.mueller.patrick.models.reports.ThreadReport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadExecutor
{
    public List< ThreadReport > execute( final ToolConfiguration configuration )
    {
        List< ThreadReport > threadReports = new LinkedList<>( );

        ExecutorService executor = createExecutorService( configuration );

        List< Future< ThreadReport > > futures = new ArrayList<>( );

        for ( int threadNumber = 1; threadNumber <= configuration.getNumberOfThreads( ); threadNumber++ )
        {
            RecordInserterCallable callable = createRecordInserter( configuration, threadNumber );

            Future< ThreadReport > future = executor.submit( callable );

            futures.add( future );
        }

        for ( Future< ThreadReport > future : futures )
        {
            try
            {
                threadReports.add( future.get( ) );
            }
            catch ( InterruptedException | ExecutionException exception )
            {
                exception.printStackTrace( );
            }
        }

        executor.shutdown( );

        return threadReports;
    }

    private RecordInserterCallable createRecordInserter(
            final ToolConfiguration configuration,
            final int threadNumber )
    {
        return new RecordInserterCallable(
                threadNumber,
                configuration.getNumberOfCommitsPerThread( ),
                configuration.getNumberOfInsertStatementsPerCommit( ) );
    }

    private ExecutorService createExecutorService( final ToolConfiguration configuration )
    {
        return Executors.newFixedThreadPool( configuration.getNumberOfThreads( ) );
    }
}