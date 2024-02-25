package com.springboot.customerRManger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.customerRManger.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{

	@Query("SELECT c FROM CustomerEntity c WHERE c.firstname = ?1")
    List<CustomerEntity> findByFirstname(String firstname);
}
