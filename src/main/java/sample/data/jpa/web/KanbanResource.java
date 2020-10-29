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

import sample.data.jpa.dao.KanbanDao;
import sample.data.jpa.domain.Kanban;

@RestController
@RequestMapping(value = "/kanban", produces = {"application/json", "application/xml"})
public class KanbanResource {

	@Autowired
	private KanbanDao kanbanDao;
	
	@GetMapping("/{kanbanId}")
	public Kanban getKanbanById(@PathVariable("kanbanId") Long kanbanId) {
		Optional<Kanban> kanban = kanbanDao.findById(kanbanId);

		return kanban.isPresent() ? kanban.get() : null;
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> addKanban(@RequestBody Kanban kanban) {
		kanbanDao.save(kanban);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(consumes = "application/json")
	public void updateKanban(@RequestBody Kanban kanban) {
		Optional<Kanban> oldDepartment = kanbanDao.findById(kanban.getId());
		oldDepartment.get().setName(kanban.getName());
		
		kanbanDao.save(oldDepartment.get());	}
	
	@DeleteMapping("/{kanbanId}")
	public ResponseEntity<String> deleteKanban(@PathVariable("kanbanId") Long kanbanId) {
		kanbanDao.deleteById(kanbanId);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
 
}
