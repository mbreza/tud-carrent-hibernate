package domain;



import javax.persistence.*;

import domain.Rent;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "car.getAll", query = "Select c from Car c"),
    @NamedQuery(name = "car.getByID", query = "Select c from Car c where c.id_car = :id_car"),
	@NamedQuery(name = "car.isavailable", query = "Select c from Car c where c.available = true") })
public class Car {

	private List<Rent> rents = new ArrayList<Rent>();
	
	private Long id_car;
	private String brand;
	private String model;
	private String dop; //date of production
	private String description;
	private Boolean available = false;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId_car() {
		return id_car;
	}

	public void setId_car(Long id_car) {
		this.id_car = id_car;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDop() {
		return dop;
	}

	public void setDop(String dop) {
		this.dop = dop;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

}
