package com.biblioadmin.application.data.service;

import com.biblioadmin.application.data.entity.SamplePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, Long>, JpaSpecificationExecutor<SamplePerson> {

}
