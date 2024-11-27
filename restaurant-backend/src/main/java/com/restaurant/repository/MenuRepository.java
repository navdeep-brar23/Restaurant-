package com.restaurant.repository;

import com.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    // JpaRepository provides built-in methods like findAll(), save(), and deleteById()
}
