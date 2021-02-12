package de.mueller.patrick.database.statements;

import de.mueller.patrick.database.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @param <R> represents the return type of the statement
 */
public abstract class AbstractStatement< R >
{
    public R execute( )
    {
        Connection connection = ConnectionFactory.createConnection( );

        R returnValue = null;

        try
        {
            connection.setAutoCommit( false );

            PreparedStatement preparedStatement = connection.prepareStatement( defineSqlStatement( ) );

            setParametersInPreparedStatement( preparedStatement );

            returnValue = executeQuery( preparedStatement );

            connection.commit();

            preparedStatement.close( );

            connection.close( );
        }
        catch ( SQLException exception )
        {
            handleSqlException( exception );
        }

        return returnValue;
    }

    protected abstract String defineSqlStatement( );

    protected void setParametersInPreparedStatement( final PreparedStatement preparedStatement ) throws SQLException
    {

    }

    protected abstract R executeQuery( final PreparedStatement preparedStatement ) throws SQLException;

    protected void handleSqlException( final SQLException exception )
    {
        exception.printStackTrace( );
    }
}