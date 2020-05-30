package de.martin.learning.einkaufsliste.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.martin.learning.einkaufsliste.backend.entities.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}



