package carrent;

import domain.Car;
import domain.Emp;
import domain.Rent;

import java.util.List;

public interface RentDAO {

    void addRent(Long carID, Long empID, int payment, String dor);
    void deleteRent(Rent rent);
    void updateRent(Rent rent);
    List<Rent> getAllRents();
    Rent getRentByID(Long id_rent);
    List<Rent> getRentsByCarID(Car car);
    List<Rent> getRentsByEmpID(Emp emp);


}