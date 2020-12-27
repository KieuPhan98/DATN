package m07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import m07.entity.Employee;
import m07.entity.Order;
import m07.entity.OrderForSupplier;

//public interface OrderRepository extends CrudRepository<Order, Integer> {
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //List order by custommer ID
    @Query(value = "select *from orders where customerId = ? and status = \"Cho duyet\" ORDER BY id desc", nativeQuery = true)
    public List<Order> listoderbycus (String customerId);
    
    @Query(value = "select *from orders where customerId = ? and status = \"Dang giao\" ORDER BY id desc", nativeQuery = true)
    public List<Order> listoderbycus1 (String customerId);
    
    @Query(value = "select *from orders where customerId = ? and status = \"Hoan tat\" ORDER BY id desc", nativeQuery = true)
    public List<Order> listoderbycus2 (String customerId);
    
    @Query(value = "select *from orders where customerId = ? and status = \"Da huy\" ORDER BY id desc", nativeQuery = true)
    public List<Order> listoderbycus3 (String customerId);

    // loc don hang co trang thai cho duyet
    @Query(value = "select *from orders where status = \"Cho duyet\" ORDER BY id desc", nativeQuery = true)
    public List<Order> lisorderbydesc ();
    
 // loc don hang co trang thai dag giao
    @Query(value = "select *from orders where status = \"Dang giao\" ORDER BY id desc", nativeQuery = true)
    public List<Order> lisorderbydesc1 ();
    
 // loc don hang co trang thai hoan tat
    @Query(value = "select *from orders where status = \"Hoan tat\" ORDER BY id desc", nativeQuery = true)
    public List<Order> lisorderbydesc2 ();

 // loc don hang co trang thai da huy
    @Query(value = "select *from orders where status = \"Da huy\" ORDER BY id desc", nativeQuery = true)
    public List<Order> lisorderbydesc3 ();
    
    @Query(value = "SELECT shipperId FROM orders where id = ?", nativeQuery = true)
    public String idShipper(int id);
    
    @Query(value = "SELECT employeeId FROM orders where id = ?", nativeQuery = true)
    public String idEmployee(int id);
}
