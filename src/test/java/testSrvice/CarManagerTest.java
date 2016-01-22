package testSrvice;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import carrent.CarDAO;
import domain.Car;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CarManagerTest {

	@Autowired
    CarDAO carManager;

    private static final String brand = "Fiat";
    private static final String model = "126p";
    private static final String dop = "1990";
    private static final String description = "nice";
    

    private static final String brand1 = "Ford";
    private static final String model1 = "mustang";
    private static final String dop1 = "1999";
    private static final String description1 = "very nice";

    @Before
    @Test
    public void addCarTest() {

        Car  newCar = new Car();
        newCar.setBrand(brand);
        newCar.setModel(model);
        newCar.setDop(dop);
        newCar.setDescription(description);
        
        carManager.addCar(newCar);

        List<Car> allCar = carManager.getAllCars();

        Car retrievedCar = allCar.get(allCar.size() - 1);

        assertEquals(brand, retrievedCar.getBrand());
        assertEquals(model, retrievedCar.getModel());
        assertEquals(dop, retrievedCar.getDop());
        assertEquals(description, retrievedCar.getDescription());
    }

    @Test
    public void getCarByIDTest() {

    	Car firstCar = carManager.getAllCars().get(0);

    	Car receivedCar = carManager.getCarByID(firstCar.getId_car());


        assertEquals(firstCar.getId_car(), receivedCar.getId_car());
    }

    @Test
    public void updateCarTest() {

    	Car updateCar = carManager.getAllCars().get(0);

        updateCar.setBrand(brand1);
        updateCar.setModel(model1);
        updateCar.setDop(dop1);
        updateCar.setDescription(description1);
        
        carManager.updateCar(updateCar);


        assertEquals(carManager.getAllCars().get(0).getModel(), model1);
    }

    @Test
    public void deleteCarTest() {

        List<Car> listOfCar = carManager.getAllCars();

        int numberOfCar = listOfCar.size();

        Car retrievedCar = carManager.getAllCars()
                .get(carManager.getAllCars().size() - 1);

        carManager.deleteCar(retrievedCar);

        assertEquals(numberOfCar - 1, carManager.getAllCars().size());

        List<Car> listOfCarAfterDelete = carManager.getAllCars();

        assertTrue(!listOfCarAfterDelete.contains(retrievedCar));


        for (Car c : listOfCarAfterDelete) {
            assertTrue(listOfCar.contains(c));
        }
    }
	
    @Test
    public void getAllCarsAvailableTest() {

        List<Car> carsAvailable = carManager.getAllCarsAvailable();

        for (Car c : carsAvailable) {
            assertTrue(c.getAvailable());
        }
    }
}
