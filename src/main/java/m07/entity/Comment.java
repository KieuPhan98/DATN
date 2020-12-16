package m07.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Comment")
public class Comment {
	@Id
	@GeneratedValue
	int id;
	
	@Nationalized
	String content;
	
	int productId;

	@OneToMany(mappedBy = "comment")
	Collection<Cmt_Cus> cmt_cus;
	
	@OneToMany(mappedBy = "comment")
	Collection<Cmt_Emp> cmp_emp;
}
