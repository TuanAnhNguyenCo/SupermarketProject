package com.example.demo.service;

import com.example.demo.entity.OrderProduct;

import java.util.Date;

public interface IOrderProductService {

    boolean CreateOrder();
    boolean UpdateOrder(int id, boolean payment_status ,
                        String address );

    boolean UpdateOrderStatus(int id, String Orderstatus);

    boolean DeleteOrder(int id);

    OrderProduct GetOrder(int id);

}
