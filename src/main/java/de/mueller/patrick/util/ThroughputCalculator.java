package de.mueller.patrick.util;

public class ThroughputCalculator
{
    private final int numberOfRecords;
    private final double runtimeInMillis;

    public ThroughputCalculator( final int numberOfRecords, final double runtimeInMillis )
    {
        this.numberOfRecords = numberOfRecords;
        this.runtimeInMillis = runtimeInMillis;
    }

    public double calculate( )
    {
        return ( ( double ) this.numberOfRecords ) / getRuntimeInSeconds( );
    }

    private double getRuntimeInSeconds( )
    {
        return ( this.runtimeInMillis / 1000.0 ) % 60;
    }
}