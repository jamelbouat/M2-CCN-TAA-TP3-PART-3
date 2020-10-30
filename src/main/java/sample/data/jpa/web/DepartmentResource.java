package sample.data.jpa.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.data.jpa.dao.DepartmentDao;
import sample.data.jpa.domain.Department;

@RestController
@RequestMapping(value = "/department", produces = {"application/json", "application/xml"})
public class DepartmentResource {

	@Autowired
	private DepartmentDao departmentDao;
	
	@GetMapping("/{departmentId}")
	public Department getDepartmentById(@PathVariable("departmentId") Long departmentId) {
		Optional<Department> department = departmentDao.findById(departmentId);

		return department.isPresent() ? department.get() : null;
	}
	
	@PostMapping(consumes = "application/json")
	public Department addDepartment(@RequestBody Department department) {
		
		return departmentDao.save(department);
	}
	
	@PutMapping(consumes = "application/json")
	public void updateDepartment(@RequestBody Department department) {
		Optional<Department> oldDepartment = departmentDao.findById(department.getId());
		oldDepartment.get().setName(department.getName());
		
		departmentDao.save(oldDepartment.get());
	}
	
	@DeleteMapping(value = "/{departmentId}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("departmentId") Long departmentId) {
		departmentDao.deleteById(departmentId);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
 
}
