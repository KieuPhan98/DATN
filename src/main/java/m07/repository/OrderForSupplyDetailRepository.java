package m07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import m07.entity.OrderDetail;
import m07.entity.OrderForSuplierDetail;

public interface OrderForSupplyDetailRepository extends JpaRepository<OrderForSuplierDetail, Integer> {

	@Query(value = "select * from orderforsuplierdetail where orderId = ? ", nativeQuery = true)
    public List<OrderForSuplierDetail> listOrderForSupplyDetail(int id);
	
	@Query(value = "select id from orderforsuplierdetail where orderId = ? and productId = ?", nativeQuery = true)
	public String idOrderDetail(int orderId, int productID);
	
	/*
	 * @Query(value =
	 * "select id, sum(quantity) as quantity,unitPrice, orderId, productId from orderforsuplierdetail where orderId = ? group by productId, orderId"
	 * , nativeQuery = true) public List<OrderForSuplierDetail>
	 * listOrderForSupplyDetails(int id);
	 */
	
}
