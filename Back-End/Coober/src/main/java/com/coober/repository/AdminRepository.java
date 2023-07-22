package com.coober.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coober.modal.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer>, PagingAndSortingRepository<Admin, Integer> {

	public Optional<Admin> findByUserName(Object userName);
	
}
