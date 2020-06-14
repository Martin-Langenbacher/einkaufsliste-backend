package de.martin.learning.einkaufsliste.backend.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.martin.learning.einkaufsliste.backend.entities.Item;
import de.martin.learning.einkaufsliste.backend.repository.ItemRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	
	// ApplicationListener: --> anderer Name: Observer --> man hängt sich in die Events rein - 
	
		private ItemRepository itemRepository;
		
		
		public DevBootstrap(ItemRepository itemRepository) {
	        this.itemRepository = itemRepository;
	    }
		
		

		@Override
		public void onApplicationEvent(ContextRefreshedEvent arg0) {
			this.initData();
		}



		private void initData() {
			// Äpfel, Birnen, Salat und Käse, ...
			Item item1 = new Item("Äpfel", 2, "2020-05-22", "Remark - if needed", true);
			Item item2 = new Item("Birnen", 2, "2020-05-22", "Remark - if needed", true);
			Item item3 = new Item("Salat", 2, "2020-05-22", "Remark - if needed", true);
			Item item4 = new Item("Käse", 2, "2020-05-22", "Remark - if needed", true);
			Item item5 = new Item("Kartoffeln", 5, "2019-06-04", "Remark - if needed: Dies ist nur ein Test...", true);
			
			this.itemRepository.save(item1);
			this.itemRepository.save(item2);
			this.itemRepository.save(item3);
			this.itemRepository.save(item4);
			this.itemRepository.save(item5);
			
			
			/* ==> 40 items, but with the same name !!!!!!!!!!!!!!!!!!!!!!!
			// does not work with Thymeleaf-part !!!!!!!!!!!!!!!!!!!!!!!!!!
			  
			   
			for (int i = 0; i < 10; i++) {
				Item item1 = new Item("Äpfel", 2, "2020-05-22", "Remark - if needed", true);
				Item item2 = new Item("Birnen", 2, "2020-05-22", "Remark - if needed", true);
				Item item3 = new Item("Salat", 2, "2020-05-22", "Remark - if needed", true);
				Item item4 = new Item("Käse", 2, "2020-05-22", "Remark - if needed", true);
				
				
				this.itemRepository.save(item1);
				this.itemRepository.save(item2);
				this.itemRepository.save(item3);
				this.itemRepository.save(item4);
			} */
			
		}
	
}


