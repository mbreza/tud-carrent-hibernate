package testSrvice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import domain.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import carrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class RentManagerTest {
	
    private static final String firstName = "Jan";
    private static final String lastName = "zDrzewa";
    private static final int salary = 13000;    
    private static final String firstName1 = "Andrzej";
    private static final String lastName1 = "Spychalski";
    private static final int salary1 = 1200;

    private static final String brand = "Fiat";
    private static final String model = "126p";
    private static final String dop = "1990";
    private static final String description = "nice";
    
    private static final String brand1 = "Fiatt";
    private static final String model1 = "125p";
    private static final String dop1 = "1999";
    private static final String description1 = "nicee";
    
    private static final String dor = "12.03.2016";
    private static final int payment = 20;

    @Autowired
    RentDAO rentManager;

    @Autowired
    CarDAO carManager;

    @Autowired
    EmpDAO empManager;

    
    @Before
    @Test
    public void addRentTest() {


        Car  newCar = new Car();
        newCar.setBrand(brand);
        newCar.setModel(model);
        newCar.setDop(dop);
        newCar.setDescription(description);
        
        carManager.addCar(newCar);


        Emp newEmp = new Emp();
        newEmp.setFirstname(firstName);
        newEmp.setLastname(lastName);
        newEmp.setSalary(salary);

        empManager.addEmp(newEmp);



        Car firstCar = carManager.getAllCars().get(0);
        Emp firstEmp = empManager.getAllEmp().get(0);

        rentManager.addRent(firstCar.getId_car(), firstEmp.getId_emp(), payment, dor);


        assertEquals(firstCar.getRents().get(firstCar.getRents().size() - 1).getPayment(), payment);
        assertEquals(firstEmp.getRents().get(firstEmp.getRents().size() - 1).getPayment(), payment);
    }

    @Test
    public void getRentByIDTest() {

    	
        Rent rent = rentManager.getAllRents().get(0);

       Rent receivedRent = rentManager.getRentByID(rent.getId_rent());

        assertEquals(rent.getId_rent(), receivedRent.getId_rent());
    }

    @Test
    public void updateRentTest() {

        Rent updateRent = rentManager.getAllRents().get(0);


        Car newCar = new Car();
        newCar.setBrand(brand1);
        newCar.setModel(model1);
        newCar.setDop(dop1);
        newCar.setDescription(description1);
        newCar.setAvailable(Boolean.TRUE);

        carManager.addCar(newCar);


        updateRent.setCar(newCar);

        rentManager.updateRent(updateRent);

        
        assertEquals(rentManager.getAllRents().get(0).getCar().getId_car(), newCar.getId_car());
    }

    @Test
    public void deleteRentTest() {
        List<Rent> listOfRents = rentManager.getAllRents();

        int numberOfRents = listOfRents.size();

        Rent retrievedRent = listOfRents.get(0);


        List<Car> listOfCars = carManager.getAllCars();
        List<Emp> listOfEmps = empManager.getAllEmp();


        for (Car c : listOfCars) {

            int index = -1;

            for (Rent r : c.getRents()) {
                if (r == retrievedRent) {
                    index = c.getRents().indexOf(r);
                    break;
                }
            }

            if (index != -1)
            c.getRents().remove(index);
        }

        for (Emp e : listOfEmps) {

            int index = -1;

            for (Rent r : e.getRents()) {
                if (r == retrievedRent) {
                    index = e.getRents().indexOf(r);
                    break;
                }
            }
            if (index != -1)
            e.getRents().remove(index);
        }


        rentManager.deleteRent(retrievedRent);


        assertEquals(numberOfRents - 1, rentManager.getAllRents().size());


        List<Rent> listOfRentsAfterDelete = rentManager.getAllRents();


        assertTrue(!listOfRentsAfterDelete.contains(retrievedRent));


        for (Rent r : listOfRentsAfterDelete) {
            assertTrue(listOfRents.contains(r));
        }


    }

    @Test
    public void getRentByCarIDTest() {
    	

    	 Car getIDCar = carManager.getAllCars().get(0);


    	 Rent getIDRent = rentManager.getAllRents().get(0);
    	 
    	 
    	 
        assertEquals(getIDCar.getId_car(), getIDRent.getCar().getId_car());


        List<Rent> carRents = rentManager.getRentsByCarID(getIDCar);

  
        assertTrue(carRents.contains(getIDRent));

        for (Rent r : carRents) {

                assertEquals(r.getCar().getId_car(), getIDCar.getId_car());

        }

    }

    @Test
    public void getRentByEmpIDTest() {

    	
   	 Emp getIDEmp = empManager.getAllEmp().get(0);


   	 Rent getIDRent = rentManager.getAllRents().get(0);
   	 
   	 
   	 
       assertEquals(getIDEmp.getId_emp(), getIDRent.getEmp().getId_emp());

       
        List<Rent> empRents = rentManager.getRentsByEmpID(getIDEmp);

        
        assertTrue(empRents.contains(getIDRent));

        for (Rent r : empRents) {

                assertEquals(r.getEmp().getId_emp(), getIDEmp.getId_emp());
            
        }        
       
    }

}
