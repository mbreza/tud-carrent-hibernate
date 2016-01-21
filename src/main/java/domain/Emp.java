package domain;

import javax.persistence.*;
import domain.Rent;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
		@NamedQuery(name = "emp.getAll", query = "Select e from Emp e"),
		@NamedQuery(name = "emp.getByID", query = "Select e from Emp e where e.id_emp = :id_emp") })
public class Emp {

	private Long id_emp;
	private String firstname;
	private String lastname;
	private int salary;

	private List<Rent> rents = new ArrayList<Rent>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId_emp() {
		return id_emp;
	}

	public void setId_emp(Long id_emp) {
		this.id_emp = id_emp;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "emp")
	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}
	
}
