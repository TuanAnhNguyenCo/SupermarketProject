package com.example.demo.service;

import java.util.Date;

public interface IOrderProductService {

    boolean CreateOrder();
    boolean UpdateOrder(int id, boolean payment_status ,
                        String address );

}
