package m07.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ReturnDetail")
public class ReturnDetail {

	@Id
	@GeneratedValue
	int id;
	
	int quantity;
	
	double unitPrice;
	
	int orderDetail_id;
	
	@ManyToOne
	@JoinColumn(name="returnID")
	Returns returns;
}
