package de.mueller.patrick.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory
{
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER = "postgres";
    private final static String PASSWORD = "mypwd";

    public static Connection createConnection( )
    {
        try
        {
            return DriverManager.getConnection( URL, USER, PASSWORD );
        }
        catch ( SQLException exception )
        {
            logExceptionMessage( exception );

            throw new RuntimeException( "Error connecting to database" );
        }
    }

    private static void logExceptionMessage( final SQLException exception )
    {
        Logger logger = Logger.getLogger( ConnectionFactory.class.getName( ) );

        logger.log( Level.SEVERE, exception.getMessage( ), exception );
    }
}