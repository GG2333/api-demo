package com.spring.apidemo.mzitu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModelRepository extends JpaRepository<ModelEntity, Integer>, JpaSpecificationExecutor<ModelEntity> {

    Page<ModelEntity> findAllByTagId(Pageable pageable, int tagId);

}
