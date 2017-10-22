package com.stc07.gubarkovag.db.pooling;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerPostgreSQL implements IConnectionManager {
    private static final Logger logger = Logger.getLogger(ConnectionManagerPostgreSQL.class);

    private static final ConnectionManagerPostgreSQL INSTANCE = new ConnectionManagerPostgreSQL();

    public static ConnectionManagerPostgreSQL getInstance() {
        return INSTANCE;
    }

    private Connection connection;

    @Override
    public Connection getConnection() {
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/bookmanager"
                    , "postgres", "postgres");
        } catch (SQLException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
        }
        return connection;
    }

    private ConnectionManagerPostgreSQL() {
        try {
            Class.forName("org.postgresql.Driver");
            /*connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/bookmanager"
                    , "postgres", "postgres");*/
        } catch (ClassNotFoundException/* | SQLException*/ e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
        }
    }
}
