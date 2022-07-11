package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.dto.BuyItem;
import com.example.dto.CreateOrderRequest;
import com.example.dto.OrderMapperParams;
import com.example.dto.OrderQueryParams;
import com.example.mapper.OrderMapper;
import com.example.mapper.ProductMapper;
import com.example.mapper.UserMapper;
import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.Product;
import com.example.model.User;
import com.example.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private UserMapper userMapper;

	
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        // 檢查 user 是否存在
        User user = userMapper.getUserById(userId);

        if (user == null) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0; //訂單的總價
        List<OrderItem> orderItemList = new ArrayList<>(); // 保存訂單項

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productMapper.getProductById(buyItem.getProductId());

            // 檢查 product 是否存在、庫存是否足夠
            if (product == null) {
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {}，欲購買數量 {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // 扣除商品庫存
            productMapper.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());


            // 計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();//購買數量 * 金額
            totalAmount = totalAmount + amount; // 累加金額
            

            // 轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        // 創建訂單，因為我們需要獲取自增主鍵的值，所以我這邊自己多定義一個 dto
        OrderMapperParams orderMapperParams = new OrderMapperParams();
        orderMapperParams.setUserId(userId);
        orderMapperParams.setTotalAmount(totalAmount);
        
        orderMapper.createOrder(orderMapperParams);
        
        Integer orderId = orderMapperParams.getOrderId(); // 獲取 orderId 

        // 創建訂單項
        orderMapper.createOrderItems(orderId, orderItemList);

        return orderId;
    }


    
    @Transactional(readOnly = true)
	@Override
	public Order getOrderById(Integer orderId) {
        Order order = orderMapper.getOrderById(orderId);

        List<OrderItem> orderItemList = orderMapper.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
	}



	@Override
	public List<Order> getOrders(OrderQueryParams orderQueryParams) {
		// 查出該用戶的所有訂單
        List<Order> orderList = orderMapper.getOrders(orderQueryParams);

        // 將每筆訂單的訂單項給設置好
        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderMapper.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }

        return orderList;
	}



	@Override
	public Integer countOrder(OrderQueryParams orderQueryParams) {
		// TODO Auto-generated method stub
		return orderMapper.countOrder(orderQueryParams);
	}
}
