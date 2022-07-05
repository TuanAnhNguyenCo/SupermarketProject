package com.example.demo.reponsitory;

import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRoleReponsitory extends JpaRepository<Role,Integer> {
    @Query(value = "select * from Role where name = ?1",nativeQuery = true)
    Role FindRoleByName(String name);
}
