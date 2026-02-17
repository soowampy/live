package com.live.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        update Product p
           set p.stock = p.stock.value - :qty
         where p.id = :productId
           and p.stock >= :qty
    """)
    int decreaseStockIfEnough(@Param("productId") Long productId, @Param("qty") int qty);
}
