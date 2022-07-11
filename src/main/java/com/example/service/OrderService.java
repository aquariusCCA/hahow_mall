package com.example.service;

import java.util.List;

import javax.validation.Valid;

import com.example.dto.CreateOrderRequest;
import com.example.dto.OrderQueryParams;
import com.example.model.Order;

public interface OrderService {

	Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

	Order getOrderById(Integer orderId);

	List<Order> getOrders(OrderQueryParams orderQueryParams);

	Integer countOrder(OrderQueryParams orderQueryParams);
}
