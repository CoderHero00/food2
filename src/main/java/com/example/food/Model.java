package com.example.food;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Model extends PagingAndSortingRepository<Foods, Long> {
    Page<Foods> findFoodsByStatusOrStatus(int a, int b, Pageable pageable);

}
