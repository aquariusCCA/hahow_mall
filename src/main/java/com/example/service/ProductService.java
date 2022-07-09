package com.example.service;

import javax.validation.Valid;

import com.example.dto.ProductRequest;
import com.example.model.Product;

public interface ProductService {

	Product getProductById(Integer id);

	Integer createProduct(ProductRequest productRequest);
}
