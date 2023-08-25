package com.example.demo.repository;

import com.example.demo.entity.TextEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<TextEntity, Integer> {
 }
//extends JpaRepository<TextEntity, Integer> 상속 받았기 때문에
