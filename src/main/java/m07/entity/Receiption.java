package m07.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Receiption")
public class Receiption {
	@Id
	@GeneratedValue
	int id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	Date createDate;

	@Column(name = "total_price")
	private double totalPrice;

	@Nationalized
	String status;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employeeCreateReceip;

	@OneToMany(mappedBy = "receiption")
	private Collection<ReceipDetail> receipDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_for_supplier_id", unique = true)
	private OrderForSupplier orderForSupplier;

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "id")
	 * 
	 * @MapsId OrderForSupplier orderForSupplier;
	 */

	public OrderForSupplier getOrderForSupplier() {
		return orderForSupplier;
	}

	public void setOrderForSupplier(OrderForSupplier orderForSupplier) {
		this.orderForSupplier = orderForSupplier;
	}

	public Collection<ReceipDetail> getReceipDetails() {
		return receipDetails;
	}

	public void setReceipDetails(Collection<ReceipDetail> receipDetails) {
		this.receipDetails = receipDetails;
	}

	public Employee getEmployeeCreateReceip() {
		return employeeCreateReceip;
	}

	public void setEmployeeCreateReceip(Employee employeeCreateReceip) {
		this.employeeCreateReceip = employeeCreateReceip;
	}

	@Override
	public String toString() {
		return "Receiption [id=" + id + ", createDate=" + createDate + ", totalPrice=" + totalPrice + ", status="
				+ status + ", employeeCreateReceip=" + employeeCreateReceip + ", receipDetails=" + receipDetails
				+ ", orderForSupplier=" + orderForSupplier + "]";
	}

	
}
