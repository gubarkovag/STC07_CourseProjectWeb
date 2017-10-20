package com.stc07.gubarkovag.db.pooling;

import java.sql.Connection;

public interface IConnectionManager {
    Connection getConnection();

}
