package com.springboot.mvc.controllers;

import java.util.List;

import com.springboot.mvc.models.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.mvc.DAOs.CustomerDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerController {

  @Autowired
  private CustomerDAO customerDAO;

  @GetMapping("/list")
  public String listCustomers(Model model) {

    try {
      List<Customer> customers = customerDAO.getAll();

      model.addAttribute("customers", customers);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return "list-customers";
  }

  @RequestMapping("/add-form")
  public String showAddForm(Model model) {
    Customer customer = new Customer();
    model.addAttribute("customer", customer);

    return "add-form";
  }

  @PostMapping("/")
  public String addCustomer(@RequestBody Customer customer) {
    try {
      customerDAO.add(customer);
    } catch (Exception err) {
      err.printStackTrace();
    }
    return "redirect:/customer/list";
  }

  @RequestMapping("/seeding")
  public String seedCustomers(Model model) {
    customerDAO.seeding();
    return "list-customers";
  }

}