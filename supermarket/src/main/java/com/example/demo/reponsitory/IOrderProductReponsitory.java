package com.example.demo.reponsitory;

import com.example.demo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;


public interface IOrderProductReponsitory extends JpaRepository<OrderProduct, Long> {
    // Create order
    @Modifying
    @Transactional
    @Query(value = "insert into order_product(total_price,original_total_price) values(0,0)",nativeQuery = true)
    void CreateOrder();

    // Update
    @Modifying
    @Transactional
    @Query(value = "update order_product set   payment_status = ?2 ," +
            "  address = ?3 where id = ?1",nativeQuery = true)
    void UpdateOrder(int id, boolean payment_status ,
                      String address );
}
