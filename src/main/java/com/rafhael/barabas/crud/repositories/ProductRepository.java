package com.rafhael.barabas.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafhael.barabas.crud.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
