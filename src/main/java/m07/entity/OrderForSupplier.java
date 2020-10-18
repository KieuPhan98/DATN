package m07.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
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
	
	@ManyToOne
	@JoinColumn(name = "employeeId")
	Employee employee;
	
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
		
}
