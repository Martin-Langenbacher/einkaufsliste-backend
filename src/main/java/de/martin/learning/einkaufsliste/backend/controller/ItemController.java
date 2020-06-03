package de.martin.learning.einkaufsliste.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.martin.learning.einkaufsliste.backend.entities.Item;
import de.martin.learning.einkaufsliste.backend.repository.ItemRepository;


@Controller
public class ItemController {
	
	private final ItemRepository itemRepository;
	
	@Autowired
	public ItemController(ItemRepository itemRepository){
		super();
		this.itemRepository = itemRepository;
	}
	
	
	
	@GetMapping({"/items", "/"})
	public String listItems (Model model) {
		model.addAttribute("items", itemRepository.findAll());
		return "items";
	}
	

	
	@GetMapping({"/item", "/item/{id}"})
	public String itemFormGet(
			Model model,
			@PathVariable (required = false) Long id) {

		Item item = new Item();
		if (id != null) {
			Optional<Item> itemOptional = itemRepository.findById(id);
			if (itemOptional.isPresent()) {
				item = itemOptional.get();
			}
		}
		model.addAttribute("item", item);
		return "item";
	}
	
	
	
	@PostMapping("/save") 
	public String saveItem (Model model, @ModelAttribute Item item) {
		
		Optional<Item> itemFromDBOptional = itemRepository.findByName(item.getName());
		// Prüft ob Buch mit gleichem Titel schon da ist!
		if (itemFromDBOptional.isPresent()) {
			model.addAttribute("message", "Please check your data. Item with the same name already existing:");
			model.addAttribute("items", itemRepository.findAll());
			return "items";
		} 
		itemRepository.save(item);
		return "redirect:/items";
	}
	

	
	@GetMapping("search")
	public String getSearchForm () {
		return "search";
	}
	
	
	
	@GetMapping("search-name")
	public String searchName (Model model, @RequestParam String name) {
		
		Optional<Item> itemOptional = itemRepository.findByName(name);
		if (itemOptional.isPresent()) {
			Item item = itemOptional.get();
			model.addAttribute("message", "Search successful!");
			model.addAttribute("item", item);
		} else {
			model.addAttribute("message", "No item found with this name!");
		}
		return "itemfound";
	}
	
	
	
	
	@GetMapping("search-remark")
	public String searchRemark(Model model, @RequestParam String remark) {
		
		List<Item> items = itemRepository.findByRemark(remark);
		if (items.isEmpty()) {
			model.addAttribute("message", "No items found with this remark!");
		} else {
			model.addAttribute("message", items.size() + " item(s) found:");
			model.addAttribute("items", items);
		}
		return "itemsfound";
	}
	
	
}
	
	


