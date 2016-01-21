package service;

import domain.Emp;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import carrent.EmpDAO;

import java.util.List;

@Component
@Transactional
public class EmpManager implements EmpDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addEmp(Emp emp) {
		emp.setId_emp(null);
		sessionFactory.getCurrentSession().persist(emp);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Emp> getAllEmp() {
		return sessionFactory.getCurrentSession().getNamedQuery("emp.getAll")
				.list();
	}

	@Override
	public Emp getEmpByID(Long id) {
		return (Emp) sessionFactory.getCurrentSession()
				.getNamedQuery("emp.getByID").setLong("id_emp", id)
				.uniqueResult();
	}

	@Override
	public void deleteEmp(Emp emp) {
		emp = (Emp) sessionFactory.getCurrentSession().get(Emp.class,
				emp.getId_emp());

		sessionFactory.getCurrentSession().delete(emp);
	}

	@Override
	public void updateEmp(Emp emp) {
		sessionFactory.getCurrentSession().update(emp);
	}

}
