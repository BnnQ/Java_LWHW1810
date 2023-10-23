package me.bnnq.homework.services.abstractions;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseConnectionFactory
{
    public Connection createConnection() throws SQLException, ClassNotFoundException;
}
