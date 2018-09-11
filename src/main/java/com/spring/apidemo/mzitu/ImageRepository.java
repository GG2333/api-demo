package com.spring.apidemo.mzitu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    Page<ImageEntity> findAllByListId(Pageable pageable, int listId);

}
