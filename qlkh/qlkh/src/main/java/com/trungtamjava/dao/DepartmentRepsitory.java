package com.trungtamjava.dao;


import com.trungtamjava.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DepartmentRepsitory extends JpaRepository<Department,Long> {
}
