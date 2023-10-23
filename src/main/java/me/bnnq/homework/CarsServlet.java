package me.bnnq.homework;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.bnnq.homework.models.enums.CarType;
import me.bnnq.homework.services.ServiceContainer;
import me.bnnq.homework.utils.Strings;
import me.bnnq.homework.utils.Views;

@WebServlet(name = "carsServlet", value = "/cars")
public class CarsServlet extends HttpServlet
{

    public void init()
    {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            var carRepository = ServiceContainer.getCarRepository();
            var allCars = carRepository.getAll();
            request.setAttribute("allCars", allCars);

            var allManufacturers = carRepository.getManufacturers();
            request.setAttribute("allManufacturers", allManufacturers);

            var manufacturerCarCount = carRepository.getManufacturersWithCarCount();
            request.setAttribute("manufacturerCarCount", manufacturerCarCount);

            var maxManufacturer = carRepository.getManufacturerWithMaximumCarCount();
            request.setAttribute("maxManufacturer", maxManufacturer);

            var minManufacturer = carRepository.getManufacturerWithMinimumCarCount();
            request.setAttribute("minManufacturer", minManufacturer);

            String yearString = request.getParameter("year");
            if (!Strings.isNullOrEmpty(yearString))
            {
                int year = Integer.parseInt(yearString);
                var carsByYear = carRepository.getCarsByYear(year);
                request.setAttribute("carsByYear", carsByYear);
            }

            String yearLeftBoundString = request.getParameter("yearLeftBound");
            String yearRightBoundString = request.getParameter("yearRightBound");
            if (!Strings.isNullOrEmpty(yearLeftBoundString) && !Strings.isNullOrEmpty(yearRightBoundString))
            {
                int yearLeftBound = Integer.parseInt(yearLeftBoundString);
                int yearRightBound = Integer.parseInt(yearRightBoundString);
                var carsInYearRange = carRepository.getCarsInYearRange(yearLeftBound, yearRightBound);
                request.setAttribute("carsInYearRange", carsInYearRange);
            }

            String manufacturer = request.getParameter("manufacturer");
            if (!Strings.isNullOrEmpty(manufacturer))
            {
                var carsOfManufacturer = carRepository.getCarsByManufacturer(manufacturer);
                request.setAttribute("carsOfManufacturer", carsOfManufacturer);
            }

            String color = request.getParameter("color");
            if (!Strings.isNullOrEmpty(color))
            {
                var carsOfColor = carRepository.getCarsByColor(color);
                request.setAttribute("carsOfColor", carsOfColor);
            }

            String engineVolumeString = request.getParameter("engineVolume");
            if (!Strings.isNullOrEmpty(engineVolumeString))
            {
                double engineVolume = Double.parseDouble(engineVolumeString);
                var carsWithEngineVolume = carRepository.getCarsByEngineVolume(engineVolume);
                request.setAttribute("carsOfEngineVolume", carsWithEngineVolume);
            }

            String type = request.getParameter("type");
            if (!Strings.isNullOrEmpty(type))
            {
                var carsOfType = carRepository.getCarsByType(CarType.valueOf(type));
                request.setAttribute("carsOfType", carsOfType);
            }

            Views.get(getServletContext(), "cars", request, response);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public void destroy()
    {
    }
}