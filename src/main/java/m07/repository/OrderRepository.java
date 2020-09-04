package m07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import m07.entity.Order;

//public interface OrderRepository extends CrudRepository<Order, Integer> {
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //List sản phẩm đã order by custommer ID
    @Query(value = "select *from orders where customerId = ?", nativeQuery = true)
    public List<Order> listoderbycus (String customerId);

    // lọc đơn hàng có trạng thái chờ duyệt
    @Query(value = "select *from orders where status = \"Cho duyet\" ORDER BY id desc", nativeQuery = true)
    public List<Order> lisorderbydesc ();
    
 // lọc đơn hàng có trạng thái dag giao
    @Query(value = "select *from orders where status = \"Dang giao\" ORDER BY id desc", nativeQuery = true)
    public List<Order> lisorderbydesc1 ();
    
 // lọc đơn hàng có trạng thái hoàn tất
    @Query(value = "select *from orders where status = \"Hoan tat\" ORDER BY id desc", nativeQuery = true)
    public List<Order> lisorderbydesc2 ();

 // lọc đơn hàng có trạng thái đã hủy
    @Query(value = "select *from orders where status = \"Da huy\" ORDER BY id desc", nativeQuery = true)
    public List<Order> lisorderbydesc3 ();
}
