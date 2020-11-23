package m07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import m07.entity.OrderForSupplier;

public interface OrderForSupplierRepository extends JpaRepository<OrderForSupplier, Integer>{
	
    @Query(value = "SELECT * FROM yumishop2.orderforsupplier where status = \"da nhap hang\" ORDER BY id desc",nativeQuery = true )
    public List<OrderForSupplier> listOrderForSupplier();

    @Query(value = "SELECT * FROM yumishop2.orderforsupplier where status = \"chua nhap hang\" ORDER BY id desc",nativeQuery = true )
    public List<OrderForSupplier> listOrderForSupplier1();
    
    @Query(value = "SELECT * FROM yumishop2.orderforsupplier where status = \"da huy\" ORDER BY id desc",nativeQuery = true )
    public List<OrderForSupplier> listOrderForSupplier2();
    
    @Query(value = "SELECT id FROM orderforsupplier where status = \"chua nhap hang\"", nativeQuery = true)
    public List<Integer> idOrderList();
    
}
