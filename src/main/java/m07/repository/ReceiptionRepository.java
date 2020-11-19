package m07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import m07.entity.Receiption;

public interface ReceiptionRepository extends JpaRepository<Receiption, Integer>{

	@Query(value = "SELECT * FROM receiption",nativeQuery = true )
    public List<Receiption> listReceiption();
}
