package com.brisbiere.Brisbiere.persistence.crud;


import com.brisbiere.Brisbiere.persistence.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ProductoPageSortRepository extends ListPagingAndSortingRepository<Producto, Integer> {
    Page<Producto> findBy(Pageable pageable);
}

