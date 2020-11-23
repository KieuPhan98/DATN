package m07.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import m07.entity.Customer;
import m07.entity.Role;
import m07.repository.CustomersRepository;
import m07.repository.OrderDetailRepository;
import m07.repository.RoleRepository;

@Controller
@RequestMapping(value = "/")
public class CustomerController {

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @RequestMapping(value = "/registered")
    public String showRegistered(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "/registered";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model){
        return "/login";
    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("customer") Customer customer, ModelMap model) {

       customer.setEnabled(true);

        Customer c = customersRepository.save(customer);
        Role role = new Role();
        role.setRole("ROLE_USER");
        role.setCustomer(customer);
        roleRepository.save(role);
        if (null != c) {
            model.addAttribute("message", "Update success");
            model.addAttribute("customer",customer);
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("customer", customer);
        }
        return "redirect:/login";
    }
}
