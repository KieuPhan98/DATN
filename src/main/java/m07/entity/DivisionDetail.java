package m07.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DivisionDetail")
public class DivisionDetail {
	@Id
	@GeneratedValue
	Integer id;
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "districtId")
	District district;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
		
}
