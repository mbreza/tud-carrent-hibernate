package carrent;

import domain.Car;

import java.util.List;

public interface CarDAO {

    void addCar(Car car);
    void deleteCar(Car car);
    void updateCar(Car car);
    List<Car> getAllCars();
    Car getCarByID(Long id_car);
    List<Car> getAllCarsAvailable();
}
