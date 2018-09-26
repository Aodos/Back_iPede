package com.arthur.ipede.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthur.ipede.domain.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>{

}
