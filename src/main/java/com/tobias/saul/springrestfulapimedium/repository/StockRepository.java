package com.tobias.saul.springrestfulapimedium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobias.saul.springrestfulapimedium.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

}
