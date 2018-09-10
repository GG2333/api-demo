package com.spring.apidemo.mzitu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelEntity, Integer> {

    Page<ModelEntity> findAllByTagId(Pageable pageable, int tagId);

}
