package com.example.demo.reponsitory;

import com.example.demo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;


public interface IOrderProductReponsitory extends JpaRepository<OrderProduct, Long> {
    // Find orderbyid

    @Query(value = "select * from order_product where id = ?1 ",nativeQuery = true)
    OrderProduct SearchOrderByID(int id);


    // Create order
    @Modifying
    @Transactional
    @Query(value = "insert into order_product(total_price,original_total_price,customer_id) values(0,0,?1)",nativeQuery = true)
    void CreateOrder(int user_id);


    // Update
    @Modifying
    @Transactional
    @Query(value = "update order_product set orderstatus = ?2 , payment_status = ?3 ," +
            "  address = ?4 where id = ?1",nativeQuery = true)
    void UpdateOrder(int id,String orderstatus, boolean payment_status ,
                      String address );

    @Modifying
    @Transactional
    @Query(value = "update order_product set orderstatus = ?2 , delivery_date = ?3 where id = ?1",nativeQuery = true)
    void UpdateOrderStatus(int id, String orderstatus, Date delivery_date);

    @Modifying
    @Transactional
    @Query(value = "delete from order_product where id = ?1",nativeQuery = true)
    void DeleteOrder(int id);

}
