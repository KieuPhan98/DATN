package m07.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Cmt_Cus")
public class Cmt_Cus {
	@Id
	@GeneratedValue
	Integer id;

	@Nationalized
	String content;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	Date time;
	
	@ManyToOne(optional = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="customerId")
	Customer customer;
	
	@ManyToOne
	@JoinColumn(name="cmtId")
	Comment comment;
}
