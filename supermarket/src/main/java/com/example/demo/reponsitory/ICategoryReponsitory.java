package com.example.demo.reponsitory;


import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoryReponsitory extends JpaRepository<Category,Integer> {

    // Get all category
    @Query(value = "select * from category",nativeQuery = true)
    List<Category> GetAllCategory();
}
