package com.yusai.spring.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    @Modifying
    @Query(value = "DELETE FROM products pro WHERE pro.id in ?1",nativeQuery = true)
    @Transactional
    void delete(Set<Integer> id);
}
