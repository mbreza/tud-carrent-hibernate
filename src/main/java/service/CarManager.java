package service;

import java.util.List;

import domain.Car;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import carrent.CarDAO;


@Component
@Transactional
public class CarManager implements CarDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCar(Car car) {
        car.setId_car(null);
        sessionFactory.getCurrentSession().persist(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getAllCars() {
        return sessionFactory.getCurrentSession().getNamedQuery("car.getAll").list();
    }

    @Override
    public Car getCarByID(Long id_car) {
        return (Car) sessionFactory.getCurrentSession().getNamedQuery("car.getByID").setLong("id_car", id_car).uniqueResult();
    }

    @Override
    public void deleteCar(Car car) {
        car = (Car)sessionFactory.getCurrentSession().get(Car.class, car.getId_car());
        sessionFactory.getCurrentSession().delete(car);
    }

    @Override
    public void updateCar(Car car) {
        sessionFactory.getCurrentSession().update(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getAllCarsAvailable() {
        return sessionFactory.getCurrentSession().getNamedQuery("car.isavailable").list();
    }
}