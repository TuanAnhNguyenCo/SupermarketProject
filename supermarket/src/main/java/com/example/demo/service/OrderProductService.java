package com.example.demo.service;

import com.example.demo.reponsitory.IOrderProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderProductService implements IOrderProductService{
    @Autowired
    IOrderProductReponsitory productReponsitory;
    // Create order
    @Override
    public boolean CreateOrder()
    {
        try {
            productReponsitory.CreateOrder();
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
    public boolean UpdateOrder(int id, boolean payment_status ,
                 String address )
    {
        try {
            productReponsitory.UpdateOrder(id,  payment_status , address );
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
