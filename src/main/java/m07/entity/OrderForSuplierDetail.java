package m07.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderForSuplierDetail")
public class OrderForSuplierDetail {
	@Id
	@GeneratedValue
	Integer id;
	
	int quantity;
	Double unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	Product products;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId")
	OrderForSupplier orderForSupplier;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public OrderForSupplier getOrderForSupplier() {
		return orderForSupplier;
	}

	public void setOrderForSupplier(OrderForSupplier orderForSupplier) {
		this.orderForSupplier = orderForSupplier;
	}

	public OrderForSuplierDetail(Integer id, int quantity, Double unitPrice, Product products,
			OrderForSupplier orderForSupplier) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.products = products;
		this.orderForSupplier = orderForSupplier;
	}
	
	public OrderForSuplierDetail() {
		
	}
}
