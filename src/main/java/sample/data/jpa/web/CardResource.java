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

import sample.data.jpa.dao.CardDao;
import sample.data.jpa.domain.Card;

@RestController
@RequestMapping(value = "/card", produces = {"application/json", "application/xml"})
public class CardResource {

	@Autowired
	private CardDao cardDao;
	
	@GetMapping("/{cardId}")
	public Card getCardById(@PathVariable("cardId") Long cardId) {
		Optional<Card> card = cardDao.findById(cardId);

		return card.isPresent() ? card.get() : null;
	}
	
	@PostMapping(consumes = "application/json")
	public Card addCard(@RequestBody Card card) {
		
		return cardDao.save(card);
	}
	
	@PutMapping(consumes = "application/json")
	public void updateCard(@RequestBody Card card) {
		Optional<Card> oldCard = cardDao.findById(card.getId());
		
		oldCard.get().setDeadline(card.getDeadline());
		oldCard.get().setDuration(card.getDuration());
		oldCard.get().setSection(card.getSection());
		
		cardDao.save(oldCard.get());
	}
	
	@DeleteMapping("/{cardId}")
	public ResponseEntity<String> deleteCard(@PathVariable("cardId") Long cardId) {
		cardDao.deleteById(cardId);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
 
}
