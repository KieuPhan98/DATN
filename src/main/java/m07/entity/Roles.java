package m07.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {
	@Id
    @GeneratedValue
    Integer id;
	
    String name;

	@OneToMany(mappedBy="role")
	Collection<RoleDetail> roleDetails;
}
