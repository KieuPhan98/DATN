package m07.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import m07.entity.Category;
import m07.entity.Customer;
import m07.entity.OrderForSuplierDetail;
import m07.entity.OrderForSupplier;
import m07.entity.Product;
import m07.entity.Supplier;
import m07.repository.OrderForSupplierRepository;
import m07.repository.OrderRepository;
import m07.repository.ProductRepository;
import m07.repository.SuppliersRepository;
import m07.repository.OrderForSupplyDetailRepository;

@Controller
@RequestMapping(value = "/")
public class OrderForSupplierController {

	private int item;
	
	@Autowired
	OrderForSupplierRepository orderForSupplierRepository;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderForSupplyDetailRepository orderForSupplyDetailRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SuppliersRepository suppliersRepository;
	
	@ModelAttribute("productList")
    public List<Product> productList(Model model) {
        List<Product> productList = (List<Product>) productRepository.findAll();
        return productList;
    }
	
	@ModelAttribute("supplierList")
    public List<Supplier> supplierList(Model model) {
        List<Supplier> supplierList = (List<Supplier>) suppliersRepository.findAll();
        return supplierList;
    }

	@RequestMapping(value = "/admin/createOrderForSupplier")
	public String exportOrder(Model model) {

		List<OrderForSupplier> listOrderForSupplier = (List<OrderForSupplier>) orderForSupplierRepository.listOrderForSupplier();
				
		model.addAttribute("listOrder", listOrderForSupplier);
		System.out.println("=====");

		List<OrderForSupplier> listOrderForSupplier1 = (List<OrderForSupplier>) orderForSupplierRepository.listOrderForSupplier1();
				
		model.addAttribute("listOrder1", listOrderForSupplier1);

		return "/admin/createOrderForSupplier";
	}

	@RequestMapping(value = "/admin/addOrderForSupplier")
	public String addOrder(Model model) {
		OrderForSupplier orderForSupplier = new OrderForSupplier();
        model.addAttribute("order", orderForSupplier);
		return "/admin/addOrderForSupplier";
	}
	
	@RequestMapping(value = "/admin/detailOrderForSupplier", method = RequestMethod.GET)
    public String detailOders(@RequestParam("id") Integer id,ModelMap model)
    {
		// lay id de truyen cho url tra ve khi update thanh cong
		item = id; 
        model.addAttribute("orderDetails", orderForSupplyDetailRepository.listOrderForSupplyDetail(id));
        return "/admin/orderForSupplyDetail";
    }
	
	@RequestMapping(value = "/admin/editProductOrderForSupply", method = RequestMethod.GET)
    public String editSupper(@RequestParam("id") int id, ModelMap model) {    
        model.addAttribute("product", orderForSupplyDetailRepository.findOne(id));
		/* System.out.println(orderForSupplyDetailRepository.findOne(id).toString()); */
        return "admin/editProductOrderForSupply";
    }
	
	@RequestMapping(value = "/admin/editProductOrderForSupply", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("orderForSupply") OrderForSuplierDetail orderForSupply, Model model, RedirectAttributes rs) {
        OrderForSuplierDetail cs = orderForSupplyDetailRepository.save(orderForSupply);
        if (null != cs) {
            model.addAttribute("message", "Update success");
            model.addAttribute("orderForSupply", orderForSupplyDetailRepository.findOne(cs.getId()));
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("orderForSupply", orderForSupply);
        }
        String url = "redirect:/admin/detailOrderForSupplier?id=" + item;
        
		return url; 
    }
	
	@RequestMapping(value = "deleteProductOrderForSupply/{id}", method = RequestMethod.GET)
	public String deleteProductOrder(@PathVariable("id") int id, ModelMap model) {
		orderForSupplyDetailRepository.delete(id);
		/* return "admin/orderForSupplyDetail"; */
		String url = "redirect:/admin/detailOrderForSupplier?id=" + item;
        
		return url; 
	}
	
	@RequestMapping(value = "admin/admin/addOrderForSupplierDetail")
	public String addProduct(Model model) {
		
		/*
		 * OrderForSuplierDetail x = orderForSupplyDetailRepository.findOne(id);
		 * 
		 * model.addAttribute("product1", x.getOrderForSupplier().getId());
		 */
		model.addAttribute("product1", item);
		
		OrderForSuplierDetail orderDetail = new OrderForSuplierDetail();
		model.addAttribute("product", orderDetail);
		
		return "admin/addOrderForSupplierDetail";
	}
	
	@RequestMapping(value = "admin/addOrderForSupplierDetail", method = RequestMethod.POST)
    public String addProduct(@Validated @ModelAttribute("product") OrderForSuplierDetail product, ModelMap model, BindingResult bindingResult) {
        OrderForSuplierDetail c = orderForSupplyDetailRepository.save(product);
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "that bai");
            return "/admin/addOrderForSupplierDetail";

        } else {
            model.addAttribute("message", "thanh cong");
        }
        //return "admin/addOrderForSupplierDetail";
        //System.out.println(item);
        String url = "redirect:/admin/detailOrderForSupplier?id=" + item;
        
		return url;
    }
}
