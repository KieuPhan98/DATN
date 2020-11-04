package m07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import m07.entity.OrderDetail;
import m07.entity.OrderForSuplierDetail;

public interface OrderForSupplyDetailRepository extends JpaRepository<OrderForSuplierDetail, Integer> {

	@Query(value = "select * from orderforsuplierdetail where orderId = ? ", nativeQuery = true)
    public List<OrderForSuplierDetail> listOrderForSupplyDetail(int id);
}
