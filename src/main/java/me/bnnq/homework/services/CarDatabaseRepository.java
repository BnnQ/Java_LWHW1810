package me.bnnq.homework.services;

import me.bnnq.homework.configs.DatabaseConfiguration;
import me.bnnq.homework.models.Car;
import me.bnnq.homework.models.enums.CarType;
import me.bnnq.homework.services.abstractions.ICarRepository;
import me.bnnq.homework.services.abstractions.IDatabaseConnectionFactory;
import me.bnnq.homework.utils.DatabaseUtils;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;

public class CarDatabaseRepository implements ICarRepository
{
    private final IDatabaseConnectionFactory databaseConnectionFactory;

    public CarDatabaseRepository(IDatabaseConnectionFactory databaseConnectionFactory, DatabaseConfiguration databaseConfiguration) throws Exception
    {
        DatabaseUtils.ensureDatabase(databaseConfiguration);
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    @Override
    public Car[] getAll()
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT * FROM Cars");
                var cars = new LinkedList<Car>();

                while (resultSet.next())
                {
                    var car = Car.parse(resultSet);
                    cars.add(car);
                }

                return cars.toArray(Car[]::new);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car getById(int id)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT * FROM Cars WHERE id = " + id);

                if (resultSet.next())
                {
                    return Car.parse(resultSet);
                }

                return null;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Car item)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                statement.executeUpdate("INSERT INTO Cars (id, name, manufacturer, engineVolume, year, color, type) VALUES (" + item.getId() + ", '" + item.getName() + "', '" + item.getManufacturer() + "', " + item.getEngineVolume() + ", " + item.getYear() + ", '" + item.getColor() + "', '" + item.getType() + "')");
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car item)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                statement.executeUpdate("UPDATE Cars SET name = '" + item.getName() + "', manufacturer = '" + item.getManufacturer() + "', engineVolume = " + item.getEngineVolume() + ", year = " + item.getYear() + ", color = '" + item.getColor() + "', type = '" + item.getType() + "' WHERE id = " + item.getId());
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                statement.executeUpdate("DELETE FROM Cars WHERE id = " + id);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public String[] getManufacturers()
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT DISTINCT manufacturer FROM Cars");
                var manufacturers = new LinkedList<String>();

                while (resultSet.next())
                {
                    manufacturers.add(resultSet.getString("manufacturer"));
                }

                return manufacturers.toArray(String[]::new);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Dictionary<String, Integer> getManufacturersWithCarCount()
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT manufacturer, COUNT(*) AS count FROM Cars GROUP BY manufacturer");
                var manufacturers = new Hashtable<String, Integer>();

                while (resultSet.next())
                {
                    manufacturers.put(resultSet.getString("manufacturer"), resultSet.getInt("count"));
                }

                return manufacturers;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public String getManufacturerWithMaximumCarCount()
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT manufacturer, COUNT(*) AS count FROM Cars GROUP BY manufacturer ORDER BY count DESC LIMIT 1");

                if (resultSet.next())
                {
                    return resultSet.getString("manufacturer");
                }

                return null;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public String getManufacturerWithMinimumCarCount()
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT manufacturer, COUNT(*) as count FROM Cars GROUP BY manufacturer ORDER BY count LIMIT 1");

                if (resultSet.next())
                {
                    return resultSet.getString("manufacturer");
                }

                return null;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Car[] getCarsByYear(int year)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT * FROM Cars WHERE year = " + year);
                var cars = new LinkedList<Car>();

                while (resultSet.next())
                {
                    var car = Car.parse(resultSet);
                    cars.add(car);
                }

                return cars.toArray(Car[]::new);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Car[] getCarsInYearRange(int yearLeftBound, int yearRightBound)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT * FROM Cars WHERE year >= " + yearLeftBound + " AND year <= " + yearRightBound);
                var cars = new LinkedList<Car>();

                while (resultSet.next())
                {
                    var car = Car.parse(resultSet);
                    cars.add(car);
                }

                return cars.toArray(Car[]::new);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Car[] getCarsByManufacturer(String manufacturer)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT * FROM Cars WHERE manufacturer = '" + manufacturer + "'");
                var cars = new LinkedList<Car>();

                while (resultSet.next())
                {
                    var car = Car.parse(resultSet);
                    cars.add(car);
                }

                return cars.toArray(Car[]::new);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Car[] getCarsByColor(String color)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT * FROM Cars WHERE color = '" + color + "'");
                var cars = new LinkedList<Car>();

                while (resultSet.next())
                {
                    var car = Car.parse(resultSet);
                    cars.add(car);
                }

                return cars.toArray(Car[]::new);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Car[] getCarsByEngineVolume(double engineVolume)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT * FROM Cars WHERE enginevolume = " + engineVolume);
                var cars = new LinkedList<Car>();

                while (resultSet.next())
                {
                    var car = Car.parse(resultSet);
                    cars.add(car);
                }

                return cars.toArray(Car[]::new);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Car[] getCarsByType(CarType type)
    {
        try (var connection = databaseConnectionFactory.createConnection())
        {
            try (var statement = connection.createStatement())
            {
                var resultSet = statement.executeQuery("SELECT * FROM Cars WHERE type = '" + type + "'");
                var cars = new LinkedList<Car>();

                while (resultSet.next())
                {
                    var car = Car.parse(resultSet);
                    cars.add(car);
                }

                return cars.toArray(Car[]::new);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}