package com.cart.products.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cart.products.customeExceptions.DuplicateProductException;
import com.cart.products.customeExceptions.NoChangesProductException;
import com.cart.products.models.Product;
import com.cart.products.repos.ProductInterface;
@Service
public class ProductService {
	@Autowired
	public ProductInterface repo;
	
	public List<Product> getAllProducts(){
		return repo.findAll();
//		return products;
	}
	
	public Product getById(int id) {
		return repo.findById(id).orElse(new Product());
	}
	public String addProduct(ProdDto prod) {
		System.out.println("fbnd"+repo.duplicate(prod.getName()).isEmpty());
		if (!repo.duplicate(prod.getName()).isEmpty()) {
			throw new DuplicateProductException("Product with name " + prod.getName() + " already exists!");
		}
	       try {
			   Product product = new Product();
	    	   product.setName(prod.getName());
		        product.setPrice(prod.getPrice());
		        product.setUpdatedDate(prod.getUpdatedDate());
		        repo.save(product);
		        return "Added";
	       }
	       catch (Exception e) {
	    	   return "Data is Missing";
	       }
		
	}
	
	public String updateProduct(Product prod) {
		System.out.println("ghki"+repo.isChanges(prod.getId(), prod.getName(), prod.getPrice(), prod.getUpdatedDate()));
		if(!repo.isChanges(prod.getId(), prod.getName(), prod.getPrice(), prod.getUpdatedDate()).isEmpty()) {
			throw new NoChangesProductException( prod.getName() + " No changes detected to update");		
		}
		repo.save(prod);
        return "Updated";
	} 
	
	public String deleteProduct(int id) {
		try {
			repo.deleteById(id);
			return "Deleted Successfully";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Delete Failed";
		}
	}

	public Page<Product> searchProd(String productnameId, Double filter, Sort sortBy, int pageNu, int pageLimit) {
		Pageable pageable = PageRequest.of(pageNu, pageLimit, sortBy);

		return repo.searchProducts(productnameId, filter, pageable);
	}

//	public List<Product> filterProducts(String keyword) {
//		
//		return null;
//	}
}
