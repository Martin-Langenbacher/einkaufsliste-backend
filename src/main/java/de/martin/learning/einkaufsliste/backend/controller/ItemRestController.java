package de.martin.learning.einkaufsliste.backend.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.martin.learning.einkaufsliste.backend.entities.Item;
import de.martin.learning.einkaufsliste.backend.repository.ItemRepository;


@RestController
@RequestMapping("/api/v2/items")
public class ItemRestController {
	
	private final ItemRepository repository;

	
	
	@Autowired
	public ItemRestController(ItemRepository itemRepository) {
		this.repository = itemRepository;
	}

	
	
	@GetMapping
	public List<Item> getAll() {
		return repository.findAll();
	}

	
	
	@GetMapping("/{id}")
	public Item get(@PathVariable long id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		// ALTERNATIVE: --> return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Item newItem(@RequestBody Item newItem) {
		return repository.save(newItem);
	}
	
	
	
	@PutMapping("/{id}")
	public Item replace(@RequestBody Item newItem, @PathVariable Long id) {
		return (Item) repository.findById(id).map(item -> {
			item.setName(newItem.getName());
			item.setAmount(newItem.getAmount());
			item.setRemark(newItem.getRemark());
			item.setLastBought(newItem.getLastBought());
			item.setNeeded(newItem.isNeeded());
			return repository.save(item);

		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}


	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id, HttpServletResponse deleteResponse) {
		try {
			repository.deleteById(id);
			deleteResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}


