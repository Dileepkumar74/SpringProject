package com.cart.products.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cart.products.models.Product;
import com.cart.products.services.ProdDto;
import com.cart.products.services.ProductService;


@RestController
public class ProductController {
	@Autowired
	public ProductService prodService;
	@Autowired
	public ProdDto os;
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		os.os();
		List<Product> products = prodService.getAllProducts();
		if(products.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/products/{pId}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer pId) {

	    Product product = prodService.getById(pId);

	    if (product.getName() == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(product);
	}

	@PostMapping("products")
	public String addProduct(@RequestBody ProdDto prod) {
		return prodService.addProduct(prod);
	}
	
	@PutMapping("products")
	public String updateProduct(@RequestBody  Product prod) {
		System.out.println("Entered");
		return prodService.updateProduct(prod);
	}
	
	@DeleteMapping("products/{pId}")
	public String deleteProduct(@PathVariable Integer pId) {
		return prodService.deleteProduct(pId);
	}
	
	@GetMapping("product")
	public List<Product> searchProduct(@RequestParam(required = true) String search, @RequestParam(required = true) String filterStr, @RequestParam(required = true) String sortByStr){
		Double filter = (filterStr == null || "null".equals(filterStr)) ? 0.0 : Double.parseDouble(filterStr);
		Sort sortBy;
		if(sortByStr.equalsIgnoreCase("des")) {
			sortBy = Sort.by("price").descending();
		}
		else sortBy = Sort.by("price").ascending();
		search = (search.equalsIgnoreCase("null")) ? "" : search;
		System.out.println(search + filter + sortBy);
		return prodService.searchProd(search, filter, sortBy);
	}
}
