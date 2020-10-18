package m07.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
@Table(name="Products")
public class Product implements Serializable {
	@Id
	@GeneratedValue
	Integer id;
	
	@Nationalized
	String name;
	String unitBrief;
	Double unitPrice;
	String image;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dddd")
	Date productDate;
	
	@Nationalized
	String description;
	Integer quantity;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	Category category;
	
	@ManyToOne
	@JoinColumn(name="supplierId")
	Supplier supplier;
	
	@OneToMany(mappedBy="product")
	Collection<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "products")
	Collection<ReceipDetail> receipDetail;
	
	@OneToMany(mappedBy = "products")
	Collection<OrderForSuplierDetail> orderForSuplierDetail;

	public Product() {
	}

	public Product(Integer id, String name, String unitBrief, Double unitPrice, String image, Date productDate, String description, Integer quantity, Double discount, Category category, Supplier supplier, Collection<OrderDetail> orderDetails) {
		this.id = id;
		this.name = name;
		this.unitBrief = unitBrief;
		this.unitPrice = unitPrice;
		this.image = image;
		this.productDate = productDate;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
		this.supplier = supplier;
		this.orderDetails = orderDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitBrief() {
		return unitBrief;
	}

	public void setUnitBrief(String unitBrief) {
		this.unitBrief = unitBrief;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Collection<ReceipDetail> getReceipDetail() {
		return receipDetail;
	}

	public void setReceipDetail(Collection<ReceipDetail> receipDetail) {
		this.receipDetail = receipDetail;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Collection<OrderForSuplierDetail> getOrderForSuplierDetail() {
		return orderForSuplierDetail;
	}

	public void setOrderForSuplierDetail(Collection<OrderForSuplierDetail> orderForSuplierDetail) {
		this.orderForSuplierDetail = orderForSuplierDetail;
	}
	
}
