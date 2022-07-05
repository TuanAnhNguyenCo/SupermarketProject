package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.OrderProduct;
import com.example.demo.reponsitory.IOrderProductReponsitory;
import com.example.demo.reponsitory.IUserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

@Service
public class OrderProductService implements IOrderProductService{
    @Autowired
    IOrderProductReponsitory productReponsitory;
    @Autowired
    IUserReponsitory userReponsitory;

    @Autowired
    IOrderProductReponsitory orderProductReponsitory;
    // Create order
    @Override
    public boolean CreateOrder()
    {
        try {
            // Thêm người dùng tạo order
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = userReponsitory.FindUserByName(auth.getName());
            productReponsitory.CreateOrder(userReponsitory.GetCustomerIdThroughAccount((account.getId())));
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
    // Khi người dùng xác nhận thanh toán
    public boolean UpdateOrder(int id,boolean payment_status ,
                 String address )
    {
        try {
            productReponsitory.UpdateOrder(id,"Chờ xác nhận", payment_status , address );
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }
    // Lấy về tên người dùng đang đăng nhập
    public String GetNameUserLogin()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    // Kiểm tra xem có đúng là của người đó không hoặc nếu là adm thì không quan tâm
    public String CheckUserOrAdmin(int id)
    {
        Account account = userReponsitory.FindUserByName(GetNameUserLogin());
        if (account.getRoleOfUser().getName().toLowerCase(Locale.ROOT).equals("employee"))
            return "employee";
        else {
            if (userReponsitory.GetCustomerIdThroughAccount(account.getId()) != orderProductReponsitory.SearchOrderByID(id).getCustomer().getId())
                return "Fail";
        }
        return "user";
    }

    // Update trạng thái sản phẩm dành cho employee
    public boolean UpdateOrderStatus(int id, String Orderstatus)
    {
        try {
            productReponsitory.UpdateOrderStatus(id, Orderstatus , new Date() );
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    // Delete Order
    public boolean DeleteOrder(int id)
    {
        // Delete khi mà orderstatus đang là "Chờ xác nhận" đối với user
        // Employee delete bình thường
        String orderstatus = orderProductReponsitory.SearchOrderByID(id).getOrderStatus().toLowerCase();
        String check = CheckUserOrAdmin(id).toLowerCase();
        if (check.equals("fail"))
            return false;
        if (!orderstatus.equals("Chờ xác nhận".toLowerCase()))
        {
            if(check.equals("user"))
                return false;
        }

        try {
            productReponsitory.DeleteOrder(id);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public OrderProduct GetOrder(int id)
    {
        return orderProductReponsitory.SearchOrderByID(id);
    }
}
