package carrent;

import domain.Emp;

import java.util.List;

public interface EmpDAO {

    void addEmp(Emp emp);
    void deleteEmp(Emp emp);
    void updateEmp(Emp emp);
    List<Emp> getAllEmp();
    Emp getEmpByID(Long id_emp);

}
