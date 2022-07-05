package com.example.demo.reponsitory;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IUserReponsitory extends JpaRepository<Account,Integer> {
    @Query(value = "select * from Account where username = ?1",nativeQuery = true)
    Account FindUserByName(String username);

    @Modifying
    @Transactional
    @Query(value = "update account set role = ?2 , role_id = ?3 where id = ?1",nativeQuery = true)
    void UpdateUserRole(int id,boolean role,int roleOfUser);

    @Query(value = "select customer_id from account_customer where account_id = ?1",nativeQuery = true)
    int GetCustomerIdThroughAccount(int id);
}
