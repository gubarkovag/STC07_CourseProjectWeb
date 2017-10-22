package com.stc07.gubarkovag.db.pooling;

import java.sql.Connection;

public class JdbcConnectionsPool extends ObjectPool<Connection> {
    private static final JdbcConnectionsPool INSTANCE = new JdbcConnectionsPool(20, 30, 10);

    public static JdbcConnectionsPool getInstance() {
        return INSTANCE;
    }

    /*public JdbcConnectionsPool(int minIdle) {
        super(minIdle);
    }*/

    public JdbcConnectionsPool(int minIdle, int maxIdle, long validationInterval) {
        super(minIdle, maxIdle, validationInterval);
    }

    @Override
    protected Connection createObject() {
        IConnectionManager manager = ConnectionManagerPostgreSQL.getInstance();
        return manager.getConnection();
    }
}
