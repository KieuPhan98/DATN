package m07.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Orders")
@Proxy(lazy = false)
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer Id;
	//String customerId;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date orderDate;
/*	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date requireDate;
	*/
	@Nationalized
	String receiver;
	
	@Nationalized
	String address;
	
	@Nationalized
	String district;
	
	String phone;
	
	@Nationalized
	String description;
	/*Double amount;*/
	@Column(name = "total_price")
	private double totalPrice;

	@Column(name = "status", nullable = false)
	@Nationalized 
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getShipperId() {
		return shipperId;
	}

	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}

	public int getBill_Id() {
		return bill_Id;
	}

	public void setBill_Id(int bill_Id) {
		this.bill_Id = bill_Id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@ManyToOne//thêm vô sửa cái gì lỗi customer?
	@JoinColumn(name="customerId")
	Customer customer;

	@OneToMany(mappedBy="order")
	Collection<OrderDetail> orderDetails;
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	Employee employee;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

/*	public Date getRequireDate() {
		return requireDate;
	}

	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}*/

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

/*	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}*/

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	/*fk*/
	int shipperId;
	int bill_Id;

	@Override
	public String toString() {
		return "Order [Id=" + Id + ", orderDate=" + orderDate + ", requireDate=" + ", receiver="
				+ receiver + ", address=" + address + ", phone=" + phone + ", description=" + description
				+ ", totalPrice=" + totalPrice + ", status=" + status + ", customer=" + customer + ", orderDetails="
				+ orderDetails + ", employee=" + employee + ", shipperId=" + shipperId + ", bill_Id=" + bill_Id + "]";
	}
	
}
