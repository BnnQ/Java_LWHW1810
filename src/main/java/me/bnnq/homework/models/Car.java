package me.bnnq.homework.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.bnnq.homework.models.enums.CarType;

import java.sql.ResultSet;
import java.util.Dictionary;

@AllArgsConstructor
@Getter
@Setter
public class Car
{
    private int id;
    private String name;
    private String manufacturer;
    private double engineVolume;
    private int year;
    private String color;
    private CarType type;

    public static Car parse(ResultSet resultSet)
    {
        try
        {
            return new Car(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("manufacturer"),
                    resultSet.getDouble("engineVolume"),
                    resultSet.getInt("year"),
                    resultSet.getString("color"),
                    CarType.valueOf(resultSet.getString("type"))
            );
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString()
    {
        return "{Id: " + id + ", Name: " + name + ", Manufacturer: " + manufacturer + ", Engine volume: " + engineVolume + ", Year: " + year + ", Color: " + color + ", Type: " + type + "}";
    }
}
