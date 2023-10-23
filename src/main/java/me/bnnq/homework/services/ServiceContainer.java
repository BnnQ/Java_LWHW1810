package me.bnnq.homework.services;

import lombok.Getter;
import me.bnnq.homework.configs.DatabaseConfiguration;
import me.bnnq.homework.models.Car;
import me.bnnq.homework.services.abstractions.ICarRepository;
import me.bnnq.homework.services.abstractions.IDatabaseConnectionFactory;
import me.bnnq.homework.services.abstractions.IRepository;

import java.lang.reflect.Constructor;

public class ServiceContainer
{
    @Getter
    private static IDatabaseConnectionFactory databaseConnectionFactory;

    public static <T extends IDatabaseConnectionFactory> IDatabaseConnectionFactory getDatabaseConnectionFactory(Class<T> classDefinition, DatabaseConfiguration databaseConfiguration) throws Exception
    {
        Constructor<T> ctor = classDefinition.getConstructor(String.class, String.class, String.class);
        databaseConnectionFactory = ctor.newInstance(databaseConfiguration.getUrl() + databaseConfiguration.getDatabaseName(), databaseConfiguration.getUsername(), databaseConfiguration.getPassword());

        return databaseConnectionFactory;
    }

    public static ICarRepository getCarRepository() throws Exception
    {
        var databaseConfiguration = ConfigContainer.getDatabaseConfiguration();
        return new CarDatabaseRepository(getDatabaseConnectionFactory(PostgresConnectionFactory.class, databaseConfiguration), databaseConfiguration);
    }


}
