package com.santxa.moneyLending.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santxa.moneyLending.models.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{
	Optional<Manager> findByUsername(String username);
	Optional<Manager> findByUsernameAndPassword(String username,String password);

}
