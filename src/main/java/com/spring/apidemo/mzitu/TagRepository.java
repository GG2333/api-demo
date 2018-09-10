package com.spring.apidemo.mzitu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository  extends JpaRepository<TagEntity, Integer>  {

}
