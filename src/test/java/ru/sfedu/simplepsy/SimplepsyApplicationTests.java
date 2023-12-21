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
	void getListOfCustomers() {
		String name = "getListOfCustomers";
		String status = "клиент";
		String phone = "test number";
		String mail = "test@gmail.com";
		String tg = "@tg";

		Customer saved1 = customerService.saveCustomer(new Customer(name, status, new Contact(phone, mail, tg)));
		Customer saved2 = customerService.saveCustomer(new Customer(name, status, new Contact(phone, mail, tg)));
		Customer saved3 = customerService.saveCustomer(new Customer(name, status, new Contact(phone, mail, tg)));

        Assertions.assertEquals(3, customerService.getCustomers(name, null).size());
		Assertions.assertEquals(3, customerService.getCustomers(null, phone).size());
		Assertions.assertEquals(3, customerService.getCustomers(null, mail).size());
		Assertions.assertEquals(3, customerService.getCustomers(null, tg).size());
		Assertions.assertEquals(3, customerService.getCustomers(name, phone).size());
		Assertions.assertEquals(3, customerService.getCustomers(name, mail).size());
		Assertions.assertEquals(3, customerService.getCustomers(name, tg).size());

		customerService.deleteCustomer(saved1.getId());
		customerService.deleteCustomer(saved2.getId());
		customerService.deleteCustomer(saved3.getId());

	}

}
