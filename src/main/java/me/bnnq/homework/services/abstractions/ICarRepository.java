package me.bnnq.homework.services.abstractions;

import me.bnnq.homework.models.Car;
import me.bnnq.homework.models.enums.CarType;

import java.util.Dictionary;

public interface ICarRepository extends IRepository<Car>
{
    String[] getManufacturers();
    Dictionary<String, Integer> getManufacturersWithCarCount();
    String getManufacturerWithMaximumCarCount();
    String getManufacturerWithMinimumCarCount();
    Car[] getCarsByYear(int year);
    Car[] getCarsInYearRange(int yearLeftBound, int yearRightBound);
    Car[] getCarsByManufacturer(String manufacturer);
    Car[] getCarsByColor(String color);
    Car[] getCarsByEngineVolume(double engineVolume);
    Car[] getCarsByType(CarType type);
}
