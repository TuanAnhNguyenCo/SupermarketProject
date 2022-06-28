package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.reponsitory.ICategoryReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryReponsitory categoryReponsitory;

    @Override
    public List<Category> GetAllCategory()
    {
        return categoryReponsitory.GetAllCategory();
    }
}
