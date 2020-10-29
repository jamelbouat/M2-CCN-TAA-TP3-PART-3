package sample.data.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Department;

@Repository
@Transactional
public interface DepartmentDao extends JpaRepository<Department, Long>{

}
