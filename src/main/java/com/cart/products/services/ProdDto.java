package com.cart.products.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProdDto {

    private String name;
    private Double price;
    private Date updatedDate;
	@Value("#{systemProperties['os.name']}")
	private String os;
	
	public void os() {
		System.out.println(os);
	}
}
