package ru.sfedu.simplepsy.customer.dto;

import ru.sfedu.simplepsy.customer.document.Customer;

import java.util.Set;

public class CustomersSearch {

    private String specialistId;
    private Set<Customer> customers;

    public CustomersSearch() {}

    public CustomersSearch(String specialistId, Set<Customer> customers) {
        this.specialistId = specialistId;
        this.customers = customers;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}