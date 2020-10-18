package m07.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.DerivationSetImpl;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "District")
public class District {
	@Id
	@GeneratedValue
	Integer Id;
	
	@Nationalized
	String name;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public Collection<DivisionDetail> getDivisionDetails() {
		return divisionDetails;
	}

	public void setDivisionDetails(Collection<DivisionDetail> divisionDetails) {
		this.divisionDetails = divisionDetails;
	}

	@OneToMany(mappedBy = "district")
	Collection<DivisionDetail> divisionDetails;
		
}
