package com.coober.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coober.modal.Cab;

public interface CabRepository extends JpaRepository<Cab, Integer> , PagingAndSortingRepository<Cab, Integer> {

    public List<Cab> findByCarType(String carType);

    public Integer countByCarType(String carType);

}
