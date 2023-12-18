package ru.sfedu.simplepsy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sfedu.simplepsy.customer.Contact;
import ru.sfedu.simplepsy.customer.Customer;
import ru.sfedu.simplepsy.customer.CustomerService;

@SpringBootTest
class SimplepsyApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	void contextLoads() {
	}

	@Test
	void sampleCRUDTest() {
		Customer customer = new Customer("Тест", "клиент", new Contact("79001270000", null, null));

		Customer savedCustomer = customerService.saveCustomer(customer);

		Contact newContact = savedCustomer.getContact();
		newContact.setTg("@IWillBeDeleted");
		savedCustomer.setContact(newContact);
		Customer updatedCustomer = customerService.updateCustomer(savedCustomer);

		customerService.getCustomer(updatedCustomer.getName(), updatedCustomer.getContact().getPhone());
		customerService.getCustomer(updatedCustomer.getName(), updatedCustomer.getContact().getTg());

		customerService.deleteCustomer(updatedCustomer.getId());
	}

}
