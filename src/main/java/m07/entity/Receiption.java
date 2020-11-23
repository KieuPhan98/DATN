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
	
	@Column(unique=true) 
	String orderForSupplierId;

	/*
	 * @Nationalized String status;
	 */

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "employeeId") private Employee employeeCreateReceip;
	 */
	
	@ManyToOne
	@JoinColumn(name ="employeeId")
	Customer customer;
	
	@OneToMany(mappedBy = "receiption")
	private Collection<ReceipDetail> receipDetails;

	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "orderForSupplier_id", unique = true) private
	 * OrderForSupplier orderForSupplier;
	 */

	public String getOrderForSupplierId() {
		return orderForSupplierId;
	}

	public void setOrderForSupplierId(String orderForSupplierId) {
		this.orderForSupplierId = orderForSupplierId;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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

	public Collection<ReceipDetail> getReceipDetails() {
		return receipDetails;
	}

	public void setReceipDetails(Collection<ReceipDetail> receipDetails) {
		this.receipDetails = receipDetails;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Receiption [id=" + id + ", createDate=" + createDate + ", totalPrice=" + totalPrice + ", receipDetails=" + receipDetails
				+ "]";
	}

}
