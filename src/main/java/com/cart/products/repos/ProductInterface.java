package com.cart.products.repos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cart.products.models.Product;

public interface ProductInterface extends JpaRepository<Product, Integer>{
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :keyword, '%') AND (:filter = 0.0 AND p.price <= :filter) ")
	Page<Product> searchProducts1(@Param("keyword") String keyword, @Param("filter") Double filter, Pageable  pageable);
	
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "AND (:filter = 0.0 OR p.price <= :filter)")
		Page<Product> searchProducts(@Param("keyword") String keyword, @Param("filter") Double filter, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.name = :keyword")
	List<Product> duplicate(@Param("keyword") String keyword);
	
	@Query("SELECT p FROM Product p WHERE p.id=:id "+ "AND p.name = :name " + "AND p.price=:price "+  "AND p.updatedDate=:updatedDate")
	List<Product> isChanges(@Param("id") int i, @Param("name") String name, @Param("price") Double price, @Param("updatedDate") Date updatedDate);
}
