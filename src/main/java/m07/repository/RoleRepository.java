package m07.repository;

import m07.entity.Role;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	@Query(value = "SELECT * FROM yumishop2.role where role = \"ROLE_EMPLOYEE\"", nativeQuery = true)
    public List<Role> listRole();
	
}
