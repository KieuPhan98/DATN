package m07.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name="Functions")
public class Functions {

	@Id
	@GeneratedValue
	Integer id;
	
	@Nationalized
	String name;
	
	String url;
	
	@OneToMany(mappedBy="function")
	Collection<RoleDetail> roleDetails;
}
