package m07.repository;

import m07.entity.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customer, Integer> {
   
    @Query(value = "select * from customers where id = ?", nativeQuery = true)
    public Customer getCustomer(String id);
    
    @Query(value = "select fullname from customers where id = ?", nativeQuery = true)
    public String getFullName(String id);
    
    @Query(value = "SELECT * FROM customers where password = '123'", nativeQuery = true)
    public List<Customer> listEmployee();
}
