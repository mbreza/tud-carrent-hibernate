package service;

import java.util.List;

import domain.Car;
import domain.Emp;
import domain.Rent;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import carrent.RentDAO;


@Component
@Transactional
public class RentManager implements RentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRent(Long carID, Long empID, int payment, String dor)  {
        Car car = (Car) sessionFactory.getCurrentSession().get(
                Car.class, carID);

        Emp emp = (Emp) sessionFactory.getCurrentSession().get(
                Emp.class, empID);

        Rent rent = new Rent();
        rent.setPayment(payment);
        rent.setCar(car);
        rent.setEmp(emp);
        rent.setDor(dor);

        rent.setId_rent(null);
        sessionFactory.getCurrentSession().persist(rent);

        car.getRents().add(rent);
        emp.getRents().add(rent);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Rent> getAllRents() {
        return sessionFactory.getCurrentSession().getNamedQuery("rent.getAll").list();
    }

    @Override
    public Rent getRentByID(Long id_rent) {
        return (Rent) sessionFactory.getCurrentSession().getNamedQuery("rent.getByID").setLong("id_rent", id_rent).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Rent> getRentsByCarID(Car car)
    {
        return sessionFactory.getCurrentSession().getNamedQuery("rent.getByIdCar").setLong("id_car", car.getId_car()).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Rent> getRentsByEmpID(Emp emp)
    {
        return sessionFactory.getCurrentSession().getNamedQuery("rent.getByIdEmp").setLong("id_emp", emp.getId_emp()).list();
    }

    @Override
    public void deleteRent(Rent rent) {
        rent = (Rent)sessionFactory.getCurrentSession().get(Rent.class, rent.getId_rent());

        sessionFactory.getCurrentSession().delete(rent);
    }

    @Override
    public void updateRent(Rent rent) {
        sessionFactory.getCurrentSession().update(rent);
    }
}
