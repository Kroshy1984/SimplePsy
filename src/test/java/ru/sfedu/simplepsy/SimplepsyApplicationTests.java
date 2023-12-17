package ru.sfedu.simplepsy;

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
//		System.out.println(customerService.getCustomer("Николай", "71234567890"));
//		System.out.println(customerService.saveCustomer(new Customer("Абоба", "клиент", new Contact("79996669966", null, null))));
	}

}
