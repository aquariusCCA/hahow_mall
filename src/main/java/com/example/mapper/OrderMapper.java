package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.dto.OrderMapperParams;
import com.example.dto.OrderQueryParams;
import com.example.model.Order;
import com.example.model.OrderItem;

@Mapper
public interface OrderMapper {

	Integer createOrder(OrderMapperParams orderMapperParams);

	void createOrderItems(@Param("orderId") Integer orderId, @Param("orderItemList") List<OrderItem> orderItemList);

	Order getOrderById(Integer orderId);

	List<OrderItem> getOrderItemsByOrderId(Integer orderId);

	List<Order> getOrders(OrderQueryParams orderQueryParams);

	Integer countOrder(OrderQueryParams orderQueryParams);
	
}
