package testSrvice;

import domain.Emp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import carrent.EmpDAO;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class EmpManagerTest {

    @Autowired
    EmpDAO empManager;

    private static final String firstName = "Jan";
    private static final String lastName = "zDrzewa";
    private static final int salary = 13000;

    private static final String firstName1 = "Andrzej";
    private static final String lastName1 = "Spychalski";
    private static final int salary1 = 1200;

    @Before
    @Test
    public void addEmpTest() {

        Emp newEmp = new Emp();
        newEmp.setFirstname(firstName);
        newEmp.setLastname(lastName);
        newEmp.setSalary(salary);

        empManager.addEmp(newEmp);

        List<Emp> allEmp = empManager.getAllEmp();

        Emp retrievedEmp = allEmp.get(allEmp.size() - 1);

        assertEquals(firstName, retrievedEmp.getFirstname());
        assertEquals(lastName, retrievedEmp.getLastname());
    }

    @Test
    public void getEmpByIDTest() {

        Emp firstEmp = empManager.getAllEmp().get(0);

        Emp receivedEmp = empManager.getEmpByID(firstEmp.getId_emp());


        assertEquals(firstEmp.getId_emp(), receivedEmp.getId_emp());
    }

    @Test
    public void updateEmpTest() {

        Emp updateEmp = empManager.getAllEmp().get(0);

        updateEmp.setFirstname(firstName1);
        updateEmp.setLastname(lastName1);
        updateEmp.setSalary(salary1);

        empManager.updateEmp(updateEmp);


        assertEquals(empManager.getAllEmp().get(0).getSalary(), salary1);
    }

    @Test
    public void deleteEmpTest() {

        List<Emp> listOfEmp = empManager.getAllEmp();

        int numberOfEmp = listOfEmp.size();

        Emp retrievedEmp = empManager.getAllEmp()
                .get(empManager.getAllEmp().size() - 1);

        empManager.deleteEmp(retrievedEmp);

        assertEquals(numberOfEmp - 1, empManager.getAllEmp().size());

        List<Emp> listOfEmpAfterDelete = empManager.getAllEmp();

        assertTrue(!listOfEmpAfterDelete.contains(retrievedEmp));


        for (Emp e : listOfEmpAfterDelete) {
            assertTrue(listOfEmp.contains(e));
        }
    }


}
