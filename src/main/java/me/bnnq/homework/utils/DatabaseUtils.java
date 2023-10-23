package me.bnnq.homework.utils;

import me.bnnq.homework.configs.DatabaseConfiguration;
import me.bnnq.homework.services.PostgresConnectionFactory;
import me.bnnq.homework.services.ServiceContainer;

import java.sql.Statement;

public class DatabaseUtils
{
    public static void ensureDatabase(DatabaseConfiguration databaseConfiguration) throws Exception
    {
        var factory = ServiceContainer.getDatabaseConnectionFactory(PostgresConnectionFactory.class, databaseConfiguration);
        try (var connection = factory.createConnection())
        {
            try (Statement statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT to_regclass('public.Cars');");
                if (resultSet.next())
                {
                    if (resultSet.getString(1) != null)
                        return;
                }

                statement.execute("CREATE TABLE Cars (id SERIAL PRIMARY KEY, name VARCHAR(255), manufacturer VARCHAR(255), engineVolume DOUBLE PRECISION, year INTEGER, color VARCHAR(255), type VARCHAR(255));");
                seedCars(databaseConfiguration);
            }
        }
    }

    private static void seedCars(DatabaseConfiguration databaseConfiguration) throws Exception
    {
        var factory = ServiceContainer.getDatabaseConnectionFactory(PostgresConnectionFactory.class, databaseConfiguration);
        try (var connection = factory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var firstCar = "INSERT INTO Cars (name, manufacturer, engineVolume, year, color, type) VALUES ('A4', 'Audi', 2.0, 2019, 'Black', 'SEDAN');";
                var secondCar = "INSERT INTO Cars (name, manufacturer, engineVolume, year, color, type) VALUES ('Model S', 'Tesla', 0.0, 2022, 'Red', 'SEDAN');";
                var thirdCar = "INSERT INTO Cars (name, manufacturer, engineVolume, year, color, type) VALUES ('Civic', 'Honda', 1.8, 2021, 'White', 'HATCHBACK');";
                var fourthCar = "INSERT INTO Cars (name, manufacturer, engineVolume, year, color, type) VALUES ('Corolla', 'Toyota', 1.8, 2020, 'Blue', 'SEDAN');";
                var fifthCar = "INSERT INTO Cars (name, manufacturer, engineVolume, year, color, type) VALUES ('3 Series', 'BMW', 2.0, 2023, 'Black', 'SEDAN');";
                var sixthCar = "INSERT INTO Cars (name, manufacturer, engineVolume, year, color, type) VALUES ('Camry', 'Toyota', 2.5, 2023, 'Silver', 'SEDAN');";

                statement.execute(firstCar);
                statement.execute(secondCar);
                statement.execute(thirdCar);
                statement.execute(fourthCar);
                statement.execute(fifthCar);
                statement.execute(sixthCar);
            }
        }
    }
}