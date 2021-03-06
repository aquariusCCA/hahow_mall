package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.constant.ProductCategory;
import com.example.dto.ProductQueryParams;
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


	@Override
	public void deleteProductById(Integer productId) {
		// TODO Auto-generated method stub
		productMapper.deleteProductById(productId);
	}


	@Override
	public List<Product> getProducts(ProductQueryParams productQueryParams) {
		// TODO Auto-generated method stub
		return productMapper.getProducts(productQueryParams);
	}


	@Override
	public Integer countProduct(ProductQueryParams productQueryParams) {
		// TODO Auto-generated method stub
		return productMapper.countProduct(productQueryParams);
	}

}
