package de.mueller.patrick.models.reports;

public class PerformanceReport
{
    private long totalRuntimeOfFastestThread;
    private long totalRuntimeOfSlowestThread;

    private double averageThroughputOfAllThreads;
    private double throughputOfFastestThread;
    private double throughputOfSlowestThread;

    private double averageThroughputOfAllCommits;
    private double throughputOfFastestCommit;
    private double throughputOfSlowestCommit;

    public long getTotalRuntimeOfFastestThread( )
    {
        return this.totalRuntimeOfFastestThread;
    }

    public void setTotalRuntimeOfFastestThread( final long totalRuntimeOfFastestThread )
    {
        this.totalRuntimeOfFastestThread = totalRuntimeOfFastestThread;
    }

    public long getTotalRuntimeOfSlowestThread( )
    {
        return this.totalRuntimeOfSlowestThread;
    }

    public void setTotalRuntimeOfSlowestThread( final long totalRuntimeOfSlowestThread )
    {
        this.totalRuntimeOfSlowestThread = totalRuntimeOfSlowestThread;
    }

    public double getAverageThroughputOfAllThreads( )
    {
        return this.averageThroughputOfAllThreads;
    }

    public void setAverageThroughputOfAllThreads( final double averageThroughputOfAllThreads )
    {
        this.averageThroughputOfAllThreads = averageThroughputOfAllThreads;
    }

    public double getThroughputOfFastestThread( )
    {
        return this.throughputOfFastestThread;
    }

    public void setThroughputOfFastestThread( final double throughputOfFastestThread )
    {
        this.throughputOfFastestThread = throughputOfFastestThread;
    }

    public double getThroughputOfSlowestThread( )
    {
        return this.throughputOfSlowestThread;
    }

    public void setThroughputOfSlowestThread( final double throughputOfSlowestThread )
    {
        this.throughputOfSlowestThread = throughputOfSlowestThread;
    }

    public double getAverageThroughputOfAllCommits( )
    {
        return this.averageThroughputOfAllCommits;
    }

    public void setAverageThroughputOfAllCommits( final double averageThroughputOfAllCommits )
    {
        this.averageThroughputOfAllCommits = averageThroughputOfAllCommits;
    }

    public double getThroughputOfFastestCommit( )
    {
        return this.throughputOfFastestCommit;
    }

    public void setThroughputOfFastestCommit( final double throughputOfFastestCommit )
    {
        this.throughputOfFastestCommit = throughputOfFastestCommit;
    }

    public double getThroughputOfSlowestCommit( )
    {
        return this.throughputOfSlowestCommit;
    }

    public void setThroughputOfSlowestCommit( final double throughputOfSlowestCommit )
    {
        this.throughputOfSlowestCommit = throughputOfSlowestCommit;
    }
}