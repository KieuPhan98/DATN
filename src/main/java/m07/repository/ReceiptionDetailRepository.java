package m07.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import m07.entity.ReceipDetail;

public interface ReceiptionDetailRepository extends JpaRepository<ReceipDetail, Integer>{

	@Query(value = "select * from receipdetail where receiptionId = ? ", nativeQuery = true)
    public List<ReceipDetail> listReceipDetail(int id);
	
	@Query(value = "select id from receipdetail where receiptionId = ? and productId = ?", nativeQuery = true)
	public String idReceipDetail(int receiptionId, int productID);
}
