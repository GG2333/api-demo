package com.spring.apidemo.mzitu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelEntity, Integer> {
}
