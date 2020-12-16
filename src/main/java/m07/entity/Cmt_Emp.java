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
@Table(name = "Cmt_Emp")
public class Cmt_Emp {
	@Id
	@GeneratedValue
	Integer id;
			
	@Nationalized
	String content;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	Date time;

	@ManyToOne

	@JoinColumn(name = "employeeId")
	Employee employee;
	
	@ManyToOne
	@JoinColumn(name="cmtId")
	Comment comment;
}
