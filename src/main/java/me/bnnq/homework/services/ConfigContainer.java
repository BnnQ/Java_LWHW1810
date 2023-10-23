package me.bnnq.homework.services;

import com.fasterxml.jackson.jr.ob.JSON;
import me.bnnq.homework.configs.DatabaseConfiguration;

import java.io.IOException;
import java.nio.file.Files;

public class ConfigContainer
{
    private static DatabaseConfiguration databaseConfiguration;

    public static DatabaseConfiguration getDatabaseConfiguration() throws IOException
    {
        if (databaseConfiguration == null)
        {
            String content = new String(Files.readAllBytes(ContextPathHolder.getProjectPath().resolve("database.json")));
            databaseConfiguration = JSON.std.with().beanFrom(DatabaseConfiguration.class, content);
        }

        return databaseConfiguration;
    }
}
