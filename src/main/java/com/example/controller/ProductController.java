package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductRequest;
import com.example.model.Product;
import com.example.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	// 查詢商品列表
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> productList = productService.getProducts();

		return ResponseEntity.status(HttpStatus.OK).body(productList);
	}

	// 根據編號查詢商品
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
		Product product = productService.getProductById(productId);

		if (product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(product);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// 新增商品
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
		// 執行新增商品
		productService.createProduct(productRequest);

		// 獲取剛剛新增的商品的編號
		Integer productId = productRequest.getProductId();

		// 獲取剛剛新增的商品
		Product product = productService.getProductById(productId);

		// 回傳
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(
			@PathVariable Integer productId,
			@RequestBody @Valid ProductRequest productRequest
	){
		System.out.println(productRequest);
		System.out.println(productId);
		
		// 檢查 product 是否存在
		Product product = productService.getProductById(productId);

		if(product == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// 修改商品的數據
		productService.updateProduct(productId, productRequest);

		Product updateProduct = productService.getProductById(productId);

		return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
	}
	
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
		// 1. 商品存在，成功的刪除掉
		// 2. 商品本來就不存在
		productService.deleteProductById(productId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
