package com.redox.apptScheduler.dao;

import com.redox.apptScheduler.entity.Department;
import org.springframework.data.repository.CrudRepository;

/**
 * Department Repository
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}

