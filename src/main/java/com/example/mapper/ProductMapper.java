package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.constant.ProductCategory;
import com.example.dto.ProductQueryParams;
import com.example.dto.ProductRequest;
import com.example.model.Product;

@Mapper
public interface ProductMapper {

	Product getProductById(Integer id);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(@Param("productId") Integer productId, ProductRequest productRequest);

	void deleteProductById(Integer productId);

	List<Product> getProducts(ProductQueryParams productQueryParams);
}
