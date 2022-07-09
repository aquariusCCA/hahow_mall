package com.example.dto;

import javax.validation.constraints.NotNull;

import com.example.constant.ProductCategory;

// 當需要新增或修改時，用來作數據校驗的
public class ProductRequest {
	
	private Integer productId;
	
	@NotNull
	private String productName;
	
	@NotNull
	private ProductCategory category;
	
	@NotNull
	private String imageUrl;
	
	@NotNull
	private Integer price;
	
	@NotNull
	private Integer stock;
	
	private String description;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ProductRequest [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", imageUrl=" + imageUrl + ", price=" + price + ", stock=" + stock + ", description=" + description
				+ "]";
	}
}
