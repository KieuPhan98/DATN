package m07.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name="Customers")
public class Customer implements Serializable {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	String id;
	String password;
	
	@Nationalized
	String fullname;
	String email;
	String phone;
	
	@Nationalized 
	private String address;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Nationalized 
	private String district;
	
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	Boolean enabled;
	
	@OneToMany(mappedBy="customer")
	Collection<Order> orders;

	@OneToMany(mappedBy="customer")
	Collection<Role> roles;
	
	@OneToMany(mappedBy = "customer")
	Collection<Cmt_Cus> cmt_cus;

	@OneToMany(mappedBy = "customer")
	Collection<OrderForSupplier> orderForSupplier;
	
	@OneToMany(mappedBy = "customer")
	Collection<Receiption> receiption;
	
	public Collection<OrderForSupplier> getOrderForSupplier() {
		return orderForSupplier;
	}

	public void setOrderForSupplier(Collection<OrderForSupplier> orderForSupplier) {
		this.orderForSupplier = orderForSupplier;
	}
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	
}
