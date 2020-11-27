package m07.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="OrderDetails")
public class OrderDetail implements Serializable {

	@Id
	@GeneratedValue
	Integer id;
	Integer quantity;
	/*
	 * @Column(name = "status", nullable = false)
	 * 
	 * @Nationalized private String status;
	 */
	Integer re_quantity;
	Double unitPrice;
	
	int returns_id;
	public Integer getRe_quantity() {
		return re_quantity;
	}

	public void setRe_quantity(Integer re_quantity) {
		this.re_quantity = re_quantity;
	}

	
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.DETACH)
//	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="orderId")
	Order order;
	
	@ManyToOne
	@JoinColumn(name="productId")
	Product product;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getReturns_id() {
		return returns_id;
	}

	public void setReturns_id(int returns_id) {
		this.returns_id = returns_id;
	}
	
	
}
