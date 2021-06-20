package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.ProductItem;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long>{
	

}
