package com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.repository;

import com.resourceserver.emazonshoppingcartservice.ports.driven.mysql.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {
    Optional<ShoppingCartEntity> findByUserId(Long userId);

}
