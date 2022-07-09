package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.dto.ProductRequest;
import com.example.model.Product;

@Mapper
public interface ProductMapper {

	Product getProductById(Integer id);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(@Param("productId") Integer productId, ProductRequest productRequest);
}
