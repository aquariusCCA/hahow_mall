package com.example.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.constant.ProductCategory;
import com.example.dto.ProductQueryParams;
import com.example.dto.ProductRequest;
import com.example.model.Product;
import com.example.service.ProductService;

@Validated // 必須加上這個註解下面的 @Max 和 @Min 才會生效
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	// 查詢商品列表
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(
			// 查詢條件
			@RequestParam(required = false) ProductCategory category,
			@RequestParam(required = false) String search,
			
			// 排序條件
			@RequestParam(defaultValue = "created_date") String orderBy,
			@RequestParam(defaultValue = "DESC") String sort,
			
			//分頁
			@RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit, // 數據筆數最大不可以超過 1000，最小不可以小於 0
			@RequestParam(defaultValue = "0") @Min(0) Integer offset
	){
		ProductQueryParams productQueryParams = new ProductQueryParams();
		productQueryParams.setCategory(category);
		productQueryParams.setSearch(search);
		productQueryParams.setOrderBy(orderBy);
		productQueryParams.setSort(sort);
		productQueryParams.setLimit(limit);
		productQueryParams.setOffset(offset);
		System.out.println(productQueryParams);
		
		List<Product> productList = productService.getProducts(productQueryParams);

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
