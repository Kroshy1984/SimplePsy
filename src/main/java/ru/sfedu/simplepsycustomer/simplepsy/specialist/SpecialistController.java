package ru.sfedu.simplepsycustomer.simplepsy.specialist;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsycustomer.simplepsy.customer.Customer;
import ru.sfedu.simplepsycustomer.simplepsy.customer.CustomerService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/SimplePsy/V1/specialist")
public class SpecialistController {


    SpecialistService specialistService;

    CustomerService customerService;

    public SpecialistController(SpecialistService specialistService, CustomerService customerService) {
        this.specialistService = specialistService;
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String loginPage()
    {
        return "login.html";
    }

    @PostMapping("/new")
    @ResponseBody
    public void createNewSpecialist(@RequestBody Specialist specialist)
    {
        specialistService.save(specialist);
    }

    @GetMapping("/customers")
    public String getCustomersList(Model model)
    {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }
    @GetMapping("/customer-avatar/{customerId}")
    public ResponseEntity<byte[]> getCustomerAvatar(@PathVariable String customerId) {
        Customer customer = customerService.findById(customerId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(customer.getAvatar(), headers, HttpStatus.OK);
    }

    @Cacheable
    @GetMapping("/customer-card/{customerId}")
    public String getCustomerCard(@PathVariable String customerId, Model model)
    {
        Customer customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "customer-card";
    }
    @PostMapping("/customer-card/save")
    public String saveNewCustomerCard(@ModelAttribute("customer") Customer customer,
                                      @RequestParam String customerId,
                                      Model model)
    {
        customer.setId(customerId);
        // это строка затычка. Нужно продумать как передавать
        // фото для аватара из шаблона
        customer.setByteAvatar(customerService.findById(customerId).getAvatar());
        Customer cust = customerService.updateCustomer(customer);
        return "redirect:/SimplePsy/V1/specialist/customer-card/" + customerId;
    }
    @GetMapping("/questionnaire")
    public String questionnaire()
    {
        return "questionnaire";
    }
    @PostMapping("/saveAnswers")
    public String saveAnswers(@RequestBody String[] answers) {
        System.out.println("Received answers: " + Arrays.toString(answers));
        return "redirect:/SimplePsy/V1/specialist/questionnaire";
    }
    @DeleteMapping("/{id}")
    public String deleteResource(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return "redirect:/SimplePsy/V1/specialist/customers";
    }
}
