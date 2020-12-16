package m07.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Returns")
public class Returns {
	@Id
	@GeneratedValue
	int id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date createDate;
	
	double totalPrice;
	
	int employeeId;
	
	int bill_Id;
	
	@OneToMany(mappedBy="returns")
	Collection<ReturnDetail> returnDetail;
}
