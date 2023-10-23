package me.bnnq.homework.services;

import lombok.AllArgsConstructor;
import me.bnnq.homework.services.abstractions.IDatabaseConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor
public class PostgresConnectionFactory implements IDatabaseConnectionFactory
{
    private final String url;
    private final String username;
    private final String password;

    @Override
    public Connection createConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, username, password);
    }

}
