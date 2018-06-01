package com.example.food;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserModel extends PagingAndSortingRepository<Foods,Long> {
}
