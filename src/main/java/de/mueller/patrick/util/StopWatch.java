package de.mueller.patrick.util;

import java.time.Duration;
import java.time.Instant;

public class StopWatch
{
    private final Instant start;
    private final Instant end;

    public StopWatch( final Instant start, final Instant end )
    {
        this.start = start;
        this.end = end;
    }

    public long computeDurationInMillis( )
    {
        return Duration.between( this.start, this.end ).toMillis( );
    }
}