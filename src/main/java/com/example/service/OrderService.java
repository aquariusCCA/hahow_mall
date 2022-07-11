package com.example.service;

import javax.validation.Valid;

import com.example.dto.CreateOrderRequest;
import com.example.model.Order;

public interface OrderService {

	Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

	Order getOrderById(Integer orderId);
}
