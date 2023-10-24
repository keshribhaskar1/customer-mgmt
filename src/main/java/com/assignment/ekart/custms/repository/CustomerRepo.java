package com.assignment.ekart.custms.repository;

import com.assignment.ekart.custms.entity.CustomerDetailsEntity;
import com.assignment.ekart.custms.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerDetailsEntity,Long> {
    List<CustomerDetailsEntity> findByPhoneNumber(String phoneNumber);
    Optional<CustomerDetailsEntity>  findByEmailId(String email);
    void deleteByPhoneNumber(String phoneNumber);

}
