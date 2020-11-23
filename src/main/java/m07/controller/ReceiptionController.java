package m07.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
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

import m07.entity.OrderForSuplierDetail;
import m07.entity.OrderForSupplier;
import m07.entity.Product;
import m07.entity.ReceipDetail;
import m07.entity.Receiption;
import m07.repository.CustomersRepository;
import m07.repository.OrderForSupplierRepository;
import m07.repository.ProductRepository;
import m07.repository.ReceiptionDetailRepository;
import m07.repository.ReceiptionRepository;

@Controller
@RequestMapping(value = "/")
public class ReceiptionController {
	
	private int item;
	private String loginID;
	
	@Autowired
	ReceiptionRepository receiptionRepository;
	
	@Autowired
	ReceiptionDetailRepository receiptionDetailRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderForSupplierRepository orderForSupplierRepository;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@ModelAttribute("productList")
    public List<Product> productList(Model model) {
        List<Product> productList = (List<Product>) productRepository.findAll();
        return productList;
    }
	
	@ModelAttribute("idOrderList")
	public List<Integer> idOrderList(Model model){
		List<Integer> idOrderList = (List<Integer>) orderForSupplierRepository.idOrderList();
		
		return idOrderList;
	}
	
	@RequestMapping(value = "/admin/importOrderFromSupplier", method = RequestMethod.GET)
    public String importOrder(Model model) {
		
		List<Receiption> listReceiption = (List<Receiption>) receiptionRepository.listReceiption();
		model.addAttribute("listReceiption", listReceiption);
		
    	return "/admin/importReceiption";
    }
	
	@RequestMapping(value = "/admin/addReceiption")
	public String addReceiption(Model model, HttpServletRequest request) {
		
		Receiption receip = new Receiption();
		model.addAttribute("importReceiption", receip);
		
		HttpSession httpSession = request.getSession();
		Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl context = (SecurityContextImpl) s;
		String loggedInUser = context.getAuthentication().getName();
		
		String name = customersRepository.getFullName(loggedInUser);

		loginID = loggedInUser;
		System.out.println(name);
		model.addAttribute("FullName", name);

		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String currentDateTime = dateFormatter.format(new Date());

		model.addAttribute("dateNow", currentDateTime);
		
		//=============
		/*
		 * List<Integer> idOrderList1 = (List<Integer>)
		 * orderForSupplierRepository.idOrderList(); List<String> intList = new
		 * ArrayList<String>(); for(int item : idOrderList1) {
		 * 
		 * intList.add(String.valueOf(item)); //
		 * System.out.println(Integer.valueOf(item)); }
		 * 
		 * for(String item : intList) {
		 * 
		 * System.out.println(item); } model.addAttribute("intList", intList);
		 */
		//===========

		return "admin/addReceiption";
	}
	
	@RequestMapping(value = "/admin/addReceiption", method = RequestMethod.POST)
	public String addReceiption(Model model, @ModelAttribute("receip") Receiption receip, BindingResult bindingResult) {
		Date date = new Date();
		receip.setCreateDate(date);
		
		receip.getCustomer().setId(loginID);
		
		System.out.println(receip.getOrderForSupplierId());
		
		int id = Integer.valueOf(receip.getOrderForSupplierId());
		OrderForSupplier order = orderForSupplierRepository.findOne(id);
		order.setStatus("da nhap hang");
		
		OrderForSupplier order1 = orderForSupplierRepository.save(order);
		Receiption receiption = receiptionRepository.save(receip);
		
		return "redirect:/admin/importOrderFromSupplier";
	}
	
	
	//============================ RECEIPTION DETAIL ===================================
	
	@RequestMapping(value = "/admin/receiptionDetail", method = RequestMethod.GET)
    public String detailReceiption(@RequestParam("id") Integer id,ModelMap model)
    {
		item = id;
		List<ReceipDetail> listDetail = (List<ReceipDetail>) receiptionDetailRepository.listReceipDetail(id);
        model.addAttribute("detail", listDetail);
        
        //System.out.println("chi tiet phieu nhap: " + listDetail.toString());
        return "/admin/receiptionDetail";
    }
	
	@RequestMapping(value = "admin/admin/addReceiptionDetail")
	public String addProduct(Model model) {
		
		model.addAttribute("product1", item);
		
		ReceipDetail orderDetail = new ReceipDetail();
		model.addAttribute("product", orderDetail);

		System.out.println(item);
		// truyen list product thuoc 1 nha cung cap
		Receiption receip = receiptionRepository.findOne(item);
		
		int id = Integer.valueOf(receip.getOrderForSupplierId());
		OrderForSupplier order = orderForSupplierRepository.findOne(id);
		List<Product> productList = (List<Product>) productRepository.listproductBysupper(order.getSupplier().getId());
		model.addAttribute("productList1", productList);

		return "admin/addReceiptionDetail";
	}
	
	  @RequestMapping(value = "admin/addReceiptionDetail", method = RequestMethod.POST)
		public String addProduct(@Validated @ModelAttribute("product") ReceipDetail product, ModelMap model,
				BindingResult bindingResult) {

			int receipID = product.getReceiption().getId();
			int productID = product.getProducts().getId();
			int quantity = product.getQuantity();

			System.out.println(receipID + " ID RECEIP");
			System.out.println(productID + " ID PRODUCT");
			System.out.println(quantity + " QUANTITY");

			String idOrderDetail = receiptionDetailRepository.idReceipDetail(receipID, productID);
			System.out.println(idOrderDetail + " ID ORDER DETAIL");

			if (idOrderDetail != null) {
				int id = Integer.valueOf(idOrderDetail);

				ReceipDetail receipDetail = receiptionDetailRepository.findOne(id);

				product.setQuantity(quantity + receipDetail.getQuantity());
				product.setId(id);
			}

			
			ReceipDetail c = receiptionDetailRepository.save(product);

			if (bindingResult.hasErrors()) {
				model.addAttribute("message", "that bai");
				return "/admin/addReceiptionDetail";
			} else {
				model.addAttribute("message", "thanh cong");
			}

			String url = "redirect:/admin/receiptionDetail?id=" + item;

			return url;
	}
	
	@RequestMapping(value = "/admin/editProductReceiption", method = RequestMethod.GET)
	public String editProduct(@RequestParam("id") int id, ModelMap model) {
		model.addAttribute("product", receiptionDetailRepository.findOne(id));
		return "admin/editProductReceiption";
	}
	
	@RequestMapping(value = "/admin/editProductReceiption", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("editDetail") ReceipDetail detail, Model model, RedirectAttributes rs) {
		ReceipDetail cs = receiptionDetailRepository.save(detail);
        if (null != cs) {
            model.addAttribute("message", "Update success");
            model.addAttribute("editDetail", receiptionDetailRepository.findOne(cs.getId()));
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("editDetail", detail);
        }
        String url = "redirect:/admin/receiptionDetail?id=" + item;
        
		return url; 
    }
	
	@RequestMapping(value = "deleteProductReceiption/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") int id, ModelMap model) {
		receiptionDetailRepository.delete(id);
		String url = "redirect:/admin/receiptionDetail?id=" + item;
        
		return url; 
	}
}
