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

import sample.data.jpa.dao.EmployeeDao;
import sample.data.jpa.domain.Employee;

@RestController
@RequestMapping(value = "/employee", produces = {"application/json", "application/xml"})
public class EmployeeResource {

	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/{employeeId}")
	public Employee getEmployeeById(@PathVariable("employeeId") Long employeeId) {
		Optional<Employee> employee = employeeDao.findById(employeeId);

		return employee.isPresent() ? employee.get() : null;
	}
	
	@PostMapping(consumes = "application/json")
	public Employee addEmployee(@RequestBody Employee employee) {
		
		return employeeDao.save(employee);
	}
	
	@PutMapping(consumes = "application/json")
	public void updateEmployee(@RequestBody Employee employee) {
		Optional<Employee> oldemployee = employeeDao.findById(employee.getId());
		
		oldemployee.get().setName(employee.getName());
		oldemployee.get().setDepartment(employee.getDepartment());
		oldemployee.get().setCard(employee.getCard());
		
		employeeDao.save(oldemployee.get());
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		employeeDao.deleteById(employeeId);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
 
}
