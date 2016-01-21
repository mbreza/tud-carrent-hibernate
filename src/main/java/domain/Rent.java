package domain;

import javax.persistence.*;


@Entity
@NamedQueries({
		@NamedQuery(name = "rent.getAll", query = "Select r from Rent r"),
		@NamedQuery(name = "rent.getByID", query = "Select r from Rent r where r.id_rent = :id_rent"),
		@NamedQuery(name = "rent.getByIdCar", query = "Select r from Rent r where r.car = :id_car"),
		@NamedQuery(name = "rent.getByIdEmp", query = "Select r from Rent r where r.emp = :id_emp"), })
public class Rent {
	private Long id_rent;
	private Car car;
	private Emp emp;
	private String dor;
	private int payment;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId_rent() {
		return id_rent;
	}

	public void setId_rent(Long id_rent) {
		this.id_rent = id_rent;
	}

	public String getDor() {
		return dor;
	}

	public void setDor(String dor) {
		this.dor = dor;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	@ManyToOne
	@JoinColumn(name = "id_car")
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@ManyToOne
	@JoinColumn(name = "id_emp")
	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

}
