package m07.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ReceipDetail")
public class ReceipDetail {
	@Id
	@GeneratedValue
	Integer id;
	
	int quantity;
	Double unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	Product products;
	
	@ManyToOne
	@JoinColumn(name = "receiptionId")
	Receiption receiption;
	
	
	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public Receiption getReceiption() {
		return receiption;
	}

	public void setReceiption(Receiption receiption) {
		this.receiption = receiption;
	}

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
	
	@Override
	public String toString() {
		return "ReceipDetail [id=" + id + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", products="
				+ products + ", receiption=" + receiption + "]";
	}
}
