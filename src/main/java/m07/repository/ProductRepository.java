package m07.repository;

import m07.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface ProductRepository  extends CrudRepository<Product, Integer>{

    //Hiá»ƒn thá»‹ danh sÃ¡ch product má»›i nháº¥t  á»Ÿ trang chá»§ LIMIT = 6
    @Query(value = " SELECT * FROM products ORDER BY productDate DESC limit 6" , nativeQuery = true)
    public List<Product> listproduct6 ();

    //List Sáº£n pháº©m by danh má»¥c
    @Query(value = "select  *from products where categoryId = ? ", nativeQuery = true)
    public List<Product> listproductBycategory (int categoryId);

    //List Sáº£n pháº©m by nhÃ  cung cáº¥p
    @Query(value = "select  *from products where supplierId = ? ", nativeQuery = true)
    public List<Product> listproductBysupper (int supplierId);

    @Query(value = "select *from products where enable = 1 ORDER BY id desc", nativeQuery = true)
    public List<Product> listproductdesc ();

    @Query(value = "select *from products where name = ?", nativeQuery = true)
    public List<Product> searchProduct(String name);
 
    List<Product> findByNameContainingOrCategoryNameContaining(String productName, String categoryName);

    @Query(value = "SELECT * FROM products WHERE unitPrice >= 1 AND unitPrice <= 100 " , nativeQuery = true)
    public List<Product> filterprice();

    @Query(value = "SELECT * FROM products WHERE unitPrice >= 100 AND unitPrice <= 300 " , nativeQuery = true)
    public List<Product> filterprice010();

    @Query(value = "SELECT * FROM products WHERE unitPrice >= 300 AND unitPrice <= 450 " , nativeQuery = true)
    public List<Product> filterprice1015();

    @Query(value = "SELECT * FROM products WHERE unitPrice >= 450 AND unitPrice <= 600 " , nativeQuery = true)
    public List<Product> filterprice1520();

    @Query(value = "select  *from  products where unitPrice >= 600 " , nativeQuery = true)
    public List<Product> filterprice20();

    @Query(value = "SELECT id FROM products where categoryId = ? limit 1;", nativeQuery = true)
    public String checkExistCategory(int categoryId);
    
    @Query(value = "SELECT id FROM products where supplierId = ? limit 1;", nativeQuery = true)
    public String checkExistSupplier(int supplierId);
    
    /*@Query(value = "SELECT * FROM  products where discount = ?" , nativeQuery = true)
    public  List<Product> sale(Double discount);*/

    @Query(value = "SELECT P.image, P.id, P.name, ifnull(Nhap.QI,0) AS tongnhap, ifnull(Xuat.QO,0) AS tongxuat, (ifnull(Nhap.QI,0) - ifnull(Xuat.QO,0)) AS tonkho\r\n" + 
    		"FROM products P \r\n" + 
    		"LEFT JOIN (select RD.productId PI, SUM(quantity) QI FROM receipdetail RD WHERE receiptionId in \r\n" + 
    		"           (select R.id from receiption AS R where R.createDate <= ?)\r\n" + 
    		"           GROUP BY RD.productId) Nhap on P.id = PI\r\n" + 
    		"LEFT JOIN (select OD.productId PC, SUM(quantity) QO FROM orderdetails OD WHERE orderId in \r\n" + 
    		"           (select O.id from orders AS O where (O.orderDate <= ? and (O.status = \"Hoan tat\" or O.status = \"Dang giao\")))\r\n" + 
    		"           GROUP BY OD.productId) Xuat on  P.id = PC \r\n" + 
    		"ORDER BY P.id;\r\n" + 
    		"", nativeQuery = true)
    public List<Object[]> tonkho(java.util.Date date, java.util.Date date2);
    
    @Query(value = "SELECT P.id, P.name, ifnull(SL,0) TongSL_Xuat, ifnull(XTB,0) DGXTB, ifnull(NTB,0) DGNTB, ifnull(Xuat.SL*(Xuat.XTB - Nhap.NTB),0) LN\r\n" + 
    		"FROM products P\r\n" + 
    		"LEFT JOIN (select RD.productId PI, (SUM(ifnull(RD.unitPrice,0)*ifnull(RD.quantity,0))/ SUM(ifnull(RD.quantity,1))) NTB \r\n" + 
    		"           FROM receipdetail RD WHERE receiptionId in \r\n" + 
    		"           (select id from receiption AS R where R.createDate <= ? )\r\n" + 
    		"           GROUP BY RD.productId) Nhap on P.id = PI\r\n" + 
    		"           \r\n" + 
    		"LEFT JOIN (select OD.productId PC, (sUM(ifnull(OD.unitPrice,0)*ifnull(OD.quantity,0))/ SUM(ifnull(quantity,1))) XTB , SUM(ifnull(OD.quantity,0)) SL \r\n" + 
    		"           FROM orderdetails OD WHERE orderId in \r\n" + 
    		"           (select id from orders AS O where O.orderDate <= ? )\r\n" + 
    		"           GROUP BY OD.productId) Xuat on  P.id = PC\r\n" + 
    		"ORDER BY P.id", nativeQuery = true)
    public List<Object[]> loinhuan(java.util.Date date, java.util.Date date2);
    
    @Query(value = "SELECT P.image, P.id, P.name, ifnull(Xuat.QO,0) AS tongxuat, ifnull(Xuat.DT,0) AS doanhthu\r\n" + 
    		"FROM products P \r\n" + 
    		"LEFT JOIN (select OD.productId PC, SUM(quantity) QO, SUM(quantity * unitPrice) DT FROM orderdetails OD WHERE orderId in \r\n" + 
    		"           (select O.id from orders AS O where (O.orderDate <= ? and O.status = \"Hoan tat\"))\r\n" + 
    		"           GROUP BY OD.productId) Xuat on  P.id = PC \r\n" + 
    		"ORDER BY P.id;\r\n" + 
    		"", nativeQuery = true)
    public List<Object[]> doanhthu(java.util.Date date);
}
