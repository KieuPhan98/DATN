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
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Proxy(lazy = true)
@Table(name = "OrderForSupplier")
public class OrderForSupplier {
	@Id
	@GeneratedValue
	int id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date createDate;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@Nationalized 
	String status;		
	
	
	@Column(unique=true) 
	String receiptionId;
	 

	/*
	 * @OneToOne(mappedBy = "orderForSupplier", fetch = FetchType.EAGER)
	 * //@JoinColumn(name = "receiptionId") Receiption receiption;
	 */
	
	@ManyToOne
	@JoinColumn(name ="employeeId")
	Customer customer;

	@ManyToOne
	@JoinColumn(name = "supplyId")
	Supplier supplier;

	@OneToMany(mappedBy = "orderForSupplier")
	Collection<OrderForSuplierDetail> orderForSuplierDetail; 
	
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Collection<OrderForSuplierDetail> getOrderForSuplierDetail() {
		return orderForSuplierDetail;
	}

	public void setOrderForSuplierDetail(Collection<OrderForSuplierDetail> orderForSuplierDetail) {
		this.orderForSuplierDetail = orderForSuplierDetail;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getReceiptionId() {
		return receiptionId;
	}

	public void setReceiptionId(String receiptionId) {
		this.receiptionId = receiptionId;
	}

	
	@Override
	public String toString() {
		return "OrderForSupplier [id=" + id + ", createDate=" + createDate + ", totalPrice=" + totalPrice + ", status="
				+ status + ", supplier=" + supplier + ", orderForSuplierDetail=" + orderForSuplierDetail
				+ "]";
	}
	
}
