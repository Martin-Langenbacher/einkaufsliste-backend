package de.martin.learning.einkaufsliste.backend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.martin.learning.einkaufsliste.backend.entities.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	
	// now with Tymeleaf
	public Optional<Item> findByName(String name);
	public List<Item> findByRemark(String remark);

}


/*

Anmerkung:
================================================	
1) Repository vergessen...
2) FindBy... funktioniert nicht...!
3) bookRepository.save(book); --> bookRepository unterstrichen: Funktioniert nicht...!

4) bookfound (?) in Eclipse...





*/