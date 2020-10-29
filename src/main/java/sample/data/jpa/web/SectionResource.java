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

import sample.data.jpa.dao.SectionDao;
import sample.data.jpa.domain.Section;

@RestController
@RequestMapping(value = "/section",produces = {"application/json", "application/xml"})
public class SectionResource {

	@Autowired
	private SectionDao sectionDao;
	
	@GetMapping("/{sectionId}")
	public Section getSectionById(@PathVariable("sectionId") Long sectionId) {
		Optional<Section> section = sectionDao.findById(sectionId);

		return section.isPresent() ? section.get() : null;	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> addSection(@RequestBody Section section) {
		sectionDao.save(section);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping(consumes = "application/json")
	public void updateSection(@RequestBody Section section) {
		Optional<Section> oldSection = sectionDao.findById(section.getId());
		oldSection.get().setName(section.getName());
		sectionDao.save(oldSection.get());
	}
	
	@DeleteMapping("/{sectionId}")
	public ResponseEntity<String> deleteSection(@PathVariable("sectionId") Long sectionId) {
		sectionDao.deleteById(sectionId);
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
 
}
