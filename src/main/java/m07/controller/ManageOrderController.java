package m07.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import m07.entity.Customer;
import m07.entity.Order;
import m07.entity.OrderDetail;
import m07.entity.Role;
import m07.repository.CustomersRepository;
import m07.repository.OrderDetailRepository;
import m07.repository.OrderRepository;
import m07.repository.RoleRepository;

@Controller
@RequestMapping(value = "/")
public class ManageOrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;
    
    @Autowired
    CustomersRepository customerRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @ModelAttribute("orderList")
    public List<OrderDetail> showOrder(Model model) {
        List<OrderDetail> orderDetailList = (List<OrderDetail>) orderDetailRepository.findAll();
        return orderDetailList;
    }
    
// don hang chua duyet
    @RequestMapping(value = "/admin/orderNew")
    public String showproorders(Model model){
        List<Order> listOrder = (List<Order>) orderRepository.lisorderbydesc();
//        List<Employee> listEmployees = (List<Employee>) employeeRepository.listEmployee();
//        model.addAttribute("employees", listEmployees);
        model.addAttribute("orders",listOrder);
        return "/admin/order1";
    }
 // status to shipping
    @RequestMapping(value = "/admin/updateStatusToShipping", method = RequestMethod.GET)
    public String editSupper(@RequestParam("id") int id,  ModelMap model) {
    	
        model.addAttribute("order1", orderRepository.findOne(id));
        model.addAttribute("message","Updating");
        
        List<Role> listRole = roleRepository.listRole();
        List<String> listShipper = new ArrayList<>();
        
        for(Role item : listRole) {
        	String name = customerRepository.getFullName(item.getCustomer().getId());
        	listShipper.add(name);
        }
        
        model.addAttribute("listShipper", listShipper);
        return "admin/editStatusToShipping";
    }

    @RequestMapping(value = "/admin/updateStatusToShipping", method = RequestMethod.POST)
    @Transactional
    public String editorder(@ModelAttribute("order1") Order order, Model model, RedirectAttributes rs) {
    	
    	System.out.println("Order Info : ====================================");
    	System.out.println(order.toString());
//        Order cs = orderRepository.save(order);
    	/*Customer customer = order.getCustomer();*/
    	
    	Customer customer = customerRepository.getCustomer(order.getCustomer().getId());
    	System.out.println("Customer Info : ====================================");
    	System.out.println(customer.getFullname());    	
    	
    	order.setCustomer(customer);
    	
    	Order cs = orderRepository.save(order);
    	
    	System.out.println("Order Info status : ====================================");
    	System.out.println(order.getStatus());
    	
        if (null != cs) {
            model.addAttribute("message", "Update success");
            model.addAttribute("order", orderRepository.findOne(cs.getId()));
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("order", order);
        }
        
        if(order.getStatus().equals("Dang giao")) {
        	return "redirect:/admin/orderNew";
        }
        else {
        	return "redirect:/admin/orderNew";
        }
    }
    
    // don hang dang giao
    @Transactional
    @RequestMapping(value = "/admin/orderShipping")
    public String showproorders1(Model model){
        List<Order> orders = (List<Order>) orderRepository.lisorderbydesc1();
        System.out.println("========================== OUTPUT DATA ==================");
        for (Order order : orders) {
        	System.out.println(order.toString());
        }
        model.addAttribute("orders",orders);
        return "/admin/order2";
    }
    
    // status to finish    
    @RequestMapping(value = "/admin/updateStatusToFinish", method = RequestMethod.GET)
    public String editSupper1(@RequestParam("id") int id,
                             ModelMap model) {
        model.addAttribute("order1", orderRepository.findOne(id));
        model.addAttribute("message","Updating");
        return "admin/editStatusToFinish";
    }

    @RequestMapping(value = "/admin/updateStatusToFinish", method = RequestMethod.POST)
    @Transactional
    public String editorder1(@ModelAttribute("order1") Order order, Model model, RedirectAttributes rs) {
    	
    	System.out.println("Order Info : ====================================");
    	System.out.println(order.toString());
//        Order cs = orderRepository.save(order);
    	/*Customer customer = order.getCustomer();*/
    	
    	Customer customer = customerRepository.getCustomer(order.getCustomer().getId());
    	System.out.println("Customer Info : ====================================");
    	System.out.println(customer.getFullname());
    	
    	order.setCustomer(customer);
    	
    	Order cs = orderRepository.save(order);
    	
    	System.out.println("Order Info status : ====================================");
    	System.out.println(order.getStatus());
    	
        if (null != cs) {
            model.addAttribute("message", "Update success");
            model.addAttribute("order", orderRepository.findOne(cs.getId()));
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("order", order);
        }
        return "redirect:/admin/orderShipping";
    }
    
    // don hang da hoan tat
    @RequestMapping(value = "/admin/orderFinish")
    public String showproorders2(Model model){
        List<Order> order = (List<Order>) orderRepository.lisorderbydesc2();
        model.addAttribute("orders",order);
        return "/admin/order3";      
    }
    
    // don hang da huy
    @RequestMapping(value = "/admin/orderCancel")
    public String showproorders3(Model model){
        List<Order> order = (List<Order>) orderRepository.lisorderbydesc3();
        model.addAttribute("orders",order);
        return "/admin/order4";
    }     
    
    @RequestMapping(value = "/admin/detailOrder", method = RequestMethod.GET)
    public String detailOders(@RequestParam("id") Integer id,ModelMap model)
    {
        model.addAttribute("orderDetails", orderDetailRepository.listorderDetail(id));
        return "admin/orderdetail";
    }

}