package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProductRequest;
import com.example.mapper.ProductMapper;
import com.example.model.Product;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductMapper productMapper;
	
	@Transactional(readOnly = true)
	@Override
	public Product getProductById(Integer id) {
		// TODO Auto-generated method stub		
		return productMapper.getProductById(id);
	}

	
	@Transactional
	@Override
	public Integer createProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		return productMapper.createProduct(productRequest);
	}


	@Transactional
	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		// TODO Auto-generated method stub
		productMapper.updateProduct(productId, productRequest);
	}

}
