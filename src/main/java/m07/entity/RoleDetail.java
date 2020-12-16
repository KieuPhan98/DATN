package m07.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name="RoleDetail")
public class RoleDetail {
	
	@Id
	@GeneratedValue
	Integer id;
	
	@Nationalized
	String description;
	
	@ManyToOne
	@JoinColumn(name="functionID")
	Functions function;
	
	@ManyToOne
	@JoinColumn(name="roleID")
	Roles role;
}
