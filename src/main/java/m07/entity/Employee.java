package m07.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Employee")
public class Employee implements Serializable {

	@Id
	String id;
	
	@Nationalized
	String LastName;
	
	@Nationalized
	String FirstName;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date Birthday;
	
	@Nationalized
	String Sex;
	
	@Nationalized
	String Adress;
	
	@Nationalized
	String District;
	
	String Phone;
	String Email;
	String Password;
	
	String roleID;
	/*
	 * @OneToMany(mappedBy = "employeeId") Collection<Returns> returns;
	 */

	/*
	 * @OneToMany(mappedBy = "employeeCreateReceip") Collection<Receiption>
	 * receiptions;
	 */

	/*
	 * @OneToMany(mappedBy = "employeeCreateOrder", fetch = FetchType.EAGER)
	 * Collection<OrderForSupplier> orderForSupplier;
	 */

	/*
	 * @OneToMany(mappedBy = "employee") Collection<Order> order;
	 */

	
	@OneToMany(mappedBy = "employee")
	Collection<Cmt_Emp> cmt_emp;

	@OneToMany(mappedBy = "employee")
	Collection<DivisionDetail> divisionDetails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Collection<Cmt_Emp> getCmt_emp() {
		return cmt_emp;
	}

	public void setCmt_emp(Collection<Cmt_Emp> cmt_emp) {
		this.cmt_emp = cmt_emp;
	}

	public Collection<DivisionDetail> getDivisionDetails() {
		return divisionDetails;
	}

	public void setDivisionDetails(Collection<DivisionDetail> divisionDetails) {
		this.divisionDetails = divisionDetails;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

}
