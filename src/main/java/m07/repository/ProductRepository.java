package m07.repository;

import m07.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

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


    @Query(value = "select *from products ORDER BY id desc", nativeQuery = true)
    public List<Product> listproductdesc ();

    //TÃ¬m kiá»ƒm sáº£n pháº©m
    @Query(value = "select *from products where name = ?", nativeQuery = true)
    public List<Product> searchProduct(String name);
 
    List<Product> findByNameContainingOrCategoryNameContaining(String productName, String categoryName);

    //lá»�c giÃ¡ cáº£
    @Query(value = "SELECT * FROM products WHERE unitPrice >= 1 AND unitPrice <= 100 " , nativeQuery = true)
    public List<Product> filterprice();

    //lá»�c giÃ¡ cáº£
    @Query(value = "SELECT * FROM products WHERE unitPrice >= 100 AND unitPrice <= 300 " , nativeQuery = true)
    public List<Product> filterprice010();

    //lá»�c giÃ¡ cáº£
    @Query(value = "SELECT * FROM products WHERE unitPrice >= 300 AND unitPrice <= 450 " , nativeQuery = true)
    public List<Product> filterprice1015();

    //lá»�c giÃ¡ cáº£
    @Query(value = "SELECT * FROM products WHERE unitPrice >= 450 AND unitPrice <= 600 " , nativeQuery = true)
    public List<Product> filterprice1520();

    //lá»�c giÃ¡ cáº£
    @Query(value = "select  *from  products where unitPrice >= 600 " , nativeQuery = true)
    public List<Product> filterprice20();

    // tÃ¬m kiáº¿m sáº£n pháº©m sale
    /*@Query(value = "SELECT * FROM  products where discount = ?" , nativeQuery = true)
    public  List<Product> sale(Double discount);*/

}
