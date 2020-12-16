package m07.repository;

import m07.entity.Order;
import m07.entity.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

    @Query(value = "SELECT * FROM orderdetails limit 2" , nativeQuery = true)
    public List<OrderDetail> toporder();

    @Query(value = "Select month(od.orderDate) , \n" +
            "SUM(o.quantity) as quantity ,    \n" +
            "SUM(o.quantity * o.total_price) as sum, \n" +
            "AVG(o.total_price) as avg  , \n" +
            "Min(o.total_price) as min  , \n" +
            "max(o.total_price) as max \n" +
            "FROM orderdetails o\n" +
            "INNER JOIN orders od ON o.orderId =od.id\n" +
            "GROUP BY month(od.orderDate);", nativeQuery = true)

    public List<Object[]> repowheremonth();

   @Query(value = "select *from orderdetails where orderId = ?", nativeQuery = true)
    public List<OrderDetail> searchOrder(int id);

    @Query(value = "SELECT  productId, SUM(quantity) as quantity\n" +
            "FROM orderdetails \n" +
            "group by productId\n" +
            "order by quantity desc", nativeQuery = true)
    public List<OrderDetail> sanphamdathang();

    @Query(value = "SELECT  p.name, p.quantity as productquantity,  p.image, o.productId, SUM(o.quantity) as quantity , p.unitPrice\n" +
            "FROM orderdetails o\n" +
            "INNER JOIN products p ON o.productId = p.id\n" +
            "group by productId\n" +
            "order by quantity desc\n" +
            "limit 4", nativeQuery = true)
    public List<Object[]> topdathangnhieu();

    @Query(value = "select * from orderdetails where id  = ? ", nativeQuery = true)
    public List<OrderDetail> lisorderby (int id);

    @Query(value = "select * from orderdetails where orderId  = ? ", nativeQuery = true)
    public List<OrderDetail> listorderDetail(int id);
    
    @Query(value = "SELECT id FROM orderdetails where productId = ? limit 1", nativeQuery = true)
    public String checkExistProduct(int productId);

}
