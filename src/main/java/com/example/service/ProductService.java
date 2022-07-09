package com.example.service;

import java.util.List;

import javax.validation.Valid;

import com.example.dto.ProductRequest;
import com.example.model.Product;

public interface ProductService {

	Product getProductById(Integer id);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(Integer productId, ProductRequest productRequest);

	void deleteProductById(Integer productId);

	List<Product> getProducts();
}
