package com.mycompany.ecommerce;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mycompany.ecommerce.domain.Address;
import com.mycompany.ecommerce.domain.Category;
import com.mycompany.ecommerce.domain.City;
import com.mycompany.ecommerce.domain.Client;
import com.mycompany.ecommerce.domain.Product;
import com.mycompany.ecommerce.domain.State;
import com.mycompany.ecommerce.domain.enums.EClientType;
import com.mycompany.ecommerce.repositories.AddressRepository;
import com.mycompany.ecommerce.repositories.CategoryRepository;
import com.mycompany.ecommerce.repositories.CityRepository;
import com.mycompany.ecommerce.repositories.ClientRepository;
import com.mycompany.ecommerce.repositories.ProductRepository;
import com.mycompany.ecommerce.repositories.StateRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	// ---------- Run when initializing application
	@Override
	public void run(String... args) throws Exception {
		
		// ---------- Category and product
		Category category1 = new Category(null, "Computing");
		Category category2 = new Category(null, "Office");
		
		Product product1 = new Product(null, "Computer", 2000.00);
		Product product2 = new Product(null, "Printer", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);
		
		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2));
		
		product1.getCategories().addAll(Arrays.asList(category1));
		product2.getCategories().addAll(Arrays.asList(category1, category2));
		product3.getCategories().addAll(Arrays.asList(category1));
		
		categoryRepository.saveAll(Arrays.asList(category1, category2));
		productRepository.saveAll(Arrays.asList(product1, product2, product3));
		
		// ---------- City and State
		State state1 = new State(null, "São Paulo");
		State state2 = new State(null, "Minas Gerais");
		
		City city1 = new City(null, "Uberlândia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Ribeirão Preto", state2);
		
		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));
		
		stateRepository.saveAll(Arrays.asList(state1, state2));
		
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		Client client1 = new Client(null, "Maria Silva", "mara@gmail.com", "611.904.270-95", EClientType.PHYSICALPERSON);
		
		client1.getPhones().addAll(Arrays.asList("999999999", "111111111"));
		
		Address address1 = new Address(null, "Avenida João Dias", "2046", "Prédio 2", "Santo Amaro", "04724-003", client1, city2);
		Address address2 = new Address(null, "Rua Othay Ribeiro de Azambuja Neto", "184", "Chácara Green Valley", "Shopping Park", "04724-003", client1, city1);
		
		client1.getAddresses().addAll(Arrays.asList(address1, address2));
		
		clientRepository.saveAll(Arrays.asList(client1));
		
		addressRepository.saveAll(Arrays.asList(address1, address2));
	}

	
	
}
