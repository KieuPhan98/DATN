package m07.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import m07.entity.Supplier;
import m07.repository.CustomersRepository;
import m07.repository.OrderForSupplierRepository;
import m07.repository.OrderRepository;
import m07.repository.ProductRepository;
import m07.repository.SuppliersRepository;
import m07.service.OrderService;
import m07.repository.OrderForSupplyDetailRepository;

@Controller
@RequestMapping(value = "/")
public class OrderForSupplierController {

	private int item;
	private String loginID;

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

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	OrderService service;

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

		List<OrderForSupplier> listOrderForSupplier = (List<OrderForSupplier>) orderForSupplierRepository
				.listOrderForSupplier();

		model.addAttribute("listOrder", listOrderForSupplier);

		System.out.println("=====");

		List<OrderForSupplier> listOrderForSupplier1 = (List<OrderForSupplier>) orderForSupplierRepository
				.listOrderForSupplier1();

		model.addAttribute("listOrder1", listOrderForSupplier1);

		List<OrderForSupplier> listOrderForSupplier2 = (List<OrderForSupplier>) orderForSupplierRepository
				.listOrderForSupplier2();

		model.addAttribute("listOrder2", listOrderForSupplier2);
		
		return "/admin/createOrderForSupplier";
	}

	@RequestMapping(value = "/admin/addOrderForSupplier")
	public String addOrder(Model model, HttpServletRequest request) {

		OrderForSupplier orderForSupplier = new OrderForSupplier();
		model.addAttribute("order", orderForSupplier);

		HttpSession httpSession = request.getSession();
		Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl context = (SecurityContextImpl) s;
		String loggedInUser = context.getAuthentication().getName();

		System.out.println(loggedInUser);
		// model.addAttribute("id", loggedInUser);

		String name = customersRepository.getFullName(loggedInUser);

		loginID = loggedInUser;
		System.out.println(name);
		model.addAttribute("FullName", name);

		DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String currentDateTime = dateFormatter.format(new Date());

		model.addAttribute("dateNow", currentDateTime);

		return "/admin/addOrderForSupplier";
	}

	@RequestMapping(value = "/admin/addOrderForSupplier", method = RequestMethod.POST)
	public String addOrder(Model model, @ModelAttribute("order") OrderForSupplier order, BindingResult bindingResult) {

		System.out.println("===============");
		Date date = new Date();
		order.setCreateDate(date);

		System.out.println(order.getCreateDate());
		
		order.getCustomer().setId(loginID);
		// System.out.println(loginID);

		order.setStatus("chua nhap hang");

		OrderForSupplier c = orderForSupplierRepository.save(order);

		return "redirect:/admin/createOrderForSupplier";
	}
	
	// huy don hang 
	@RequestMapping(value = "editStatusToCancel/{id}", method = RequestMethod.GET)
	public String editStatusToCancel(@PathVariable("id") int id, ModelMap model) {

		OrderForSupplier order = orderForSupplierRepository.findOne(id);
		order.setStatus("da huy");
		OrderForSupplier order1 = orderForSupplierRepository.save(order);
		
		String url = "redirect:/admin/createOrderForSupplier";

		return url;
	}
	
	@RequestMapping(value = "/admin/editOrderForSupply", method = RequestMethod.GET)
	public String editOrderForSupply(ModelMap model, @RequestParam("id") int id) {
		model.addAttribute("order", orderForSupplierRepository.findOne(id));

		return "admin/editOrderForSupply";
	}

	@RequestMapping(value = "/admin/editOrderForSupply", method = RequestMethod.POST)
	public String editOrderForSupply(ModelMap model, @ModelAttribute("orderForSupply") OrderForSupplier order,
			RedirectAttributes rs) {

		OrderForSupplier ord = orderForSupplierRepository.save(order);

		/*
		 * if (null != ord) { model.addAttribute("message", "Update success");
		 * model.addAttribute("orderForSupply",
		 * orderForSupplyDetailRepository.findOne(ord.getId())); } else {
		 * model.addAttribute("message", "Update failure");
		 * model.addAttribute("orderForSupply", order); }
		 */

		return "redirect:/admin/createOrderForSupplier";
	}

	@RequestMapping(value = "deleteOrderForSupply/{id}", method = RequestMethod.GET)
	public String deleteOrderForSupply(@PathVariable("id") int id, ModelMap model) {

		orderForSupplierRepository.delete(id);

		// return "admin/orderForSupplyDetail";
		// String url = "redirect:/admin/detailOrderForSupplier?id=" + item;

		return "redirect:/admin/createOrderForSupplier";

	}

	// =================== ADD DETAIL ORDER FOR SUPPLIER ======================

	@RequestMapping(value = "/admin/detailOrderForSupplier", method = RequestMethod.GET)
	public String detailOders(@RequestParam("id") Integer id, ModelMap model) {
		// lay id de truyen cho url tra ve khi update thanh cong
		item = id;
		model.addAttribute("orderDetails", orderForSupplyDetailRepository.listOrderForSupplyDetail(id));

		return "/admin/orderForSupplyDetail";
	}

	@RequestMapping(value = "admin/admin/addOrderForSupplierDetail")
	public String addProduct(Model model) {
		model.addAttribute("product1", item);

		OrderForSuplierDetail orderDetail = new OrderForSuplierDetail();
		// orderDetail.setId(item);
		// orderDetail.setId(id);
		model.addAttribute("product", orderDetail);

		System.out.println(item);
		// truyen list product thuoc 1 nha cung cap
		OrderForSupplier order = orderForSupplierRepository.findOne(item);
		List<Product> productList = (List<Product>) productRepository.listproductBysupper(order.getSupplier().getId());
		model.addAttribute("productList1", productList);

		return "admin/addOrderForSupplierDetail";
	}

	@RequestMapping(value = "admin/addOrderForSupplierDetail", method = RequestMethod.POST)
	public String addProduct(@Validated @ModelAttribute("product") OrderForSuplierDetail product, ModelMap model,
			BindingResult bindingResult) {

		// truy cap database de check ton tai orderId vs ProductId, neu ko ton tai thi
		// save product nhu binh thuong
		// neu ton tai thi lay quatity vs id cua cai dong co orderiD VS productId gan id
		// vao product
		// con quatyty se cong vs quatity cua product ma da nhap vao tu file jsp ,xong
		// cong lai gan vào product
		// xong save nhu bt

		int orderID = product.getOrderForSupplier().getId();
		int productID = product.getProducts().getId();
		int quantity = product.getQuantity();
		
		System.out.println(orderID + " ID ORDER");
		System.out.println(productID + " ID PRODUCT");
		System.out.println(quantity + " QUANTITY");

		String idOrderDetail = orderForSupplyDetailRepository.idOrderDetail(orderID, productID);
		System.out.println(idOrderDetail + " ID ORDER DETAIL");

		if (idOrderDetail != null) {
			int id = Integer.valueOf(idOrderDetail);

			OrderForSuplierDetail orderDetail = orderForSupplyDetailRepository.findOne(id);

			int sl = quantity + orderDetail.getQuantity();
			product.setQuantity(sl);
			product.setId(id);
			
		}
		
		OrderForSuplierDetail c = orderForSupplyDetailRepository.save(product);
		
		//tinh sum don dat hang
		List<OrderForSuplierDetail> lisOrder = orderForSupplyDetailRepository.listOrderForSupplyDetail(orderID);
		double sum = 0;
		for(OrderForSuplierDetail detail : lisOrder) {
			sum += detail.getQuantity() * detail.getUnitPrice();
		}
		System.out.println("sum: " + sum);
		OrderForSupplier order = orderForSupplierRepository.findOne(orderID);
		order.setTotalPrice(sum);
		orderForSupplierRepository.save(order);
		//===============
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", "that bai");
			return "/admin/addOrderForSupplierDetail";

		} else {
			model.addAttribute("message", "thanh cong");
		}
		// System.out.println(item);
		String url = "redirect:/admin/detailOrderForSupplier?id=" + item;

		return url;
	}

	// ======================== EDIT AND DELETE ORDER DETAIL ============================
	 
	@RequestMapping(value = "/admin/editProductOrderForSupply", method = RequestMethod.GET)
	public String editSupper(@RequestParam("id") int id, ModelMap model) {
		model.addAttribute("product", orderForSupplyDetailRepository.findOne(id));
		System.out.println(orderForSupplyDetailRepository.findOne(id).toString());

		// truyen list product thuoc 1 nha cung cap 
		OrderForSupplier order = orderForSupplierRepository.findOne(item);
		List<Product> productList = (List<Product>) productRepository.listproductBysupper(order.getSupplier().getId());
		model.addAttribute("productList1", productList);

		return "admin/editProductOrderForSupply";
	}

	@RequestMapping(value = "/admin/editProductOrderForSupply", method = RequestMethod.POST)
	public String editProduct(@ModelAttribute("orderForSupply") OrderForSuplierDetail orderForSupply, Model model, RedirectAttributes rs) {
			
		OrderForSuplierDetail cs = orderForSupplyDetailRepository.save(orderForSupply);

		// tinh sum don dat hang
		List<OrderForSuplierDetail> lisOrder = orderForSupplyDetailRepository.listOrderForSupplyDetail(item);
		double sum = 0;
		for (OrderForSuplierDetail detail : lisOrder) {
			sum += detail.getQuantity() * detail.getUnitPrice();
		}
		System.out.println("sum: " + sum);
		OrderForSupplier order = orderForSupplierRepository.findOne(item);
		order.setTotalPrice(sum);
		orderForSupplierRepository.save(order);
		// ===============

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
		
		// tinh sum don dat hang
				List<OrderForSuplierDetail> lisOrder = orderForSupplyDetailRepository.listOrderForSupplyDetail(item);
				double sum = 0;
				for (OrderForSuplierDetail detail : lisOrder) {
					sum += detail.getQuantity() * detail.getUnitPrice();
				}
				System.out.println("sum: " + sum);
				OrderForSupplier order = orderForSupplierRepository.findOne(item);
				order.setTotalPrice(sum);
				orderForSupplierRepository.save(order);
				// ===============
		
		String url = "redirect:/admin/detailOrderForSupplier?id=" + item;

		return url;
	}

	// ======================= EXPORT FILE EXCEL ===================
		@RequestMapping(value = "admin/exportExcel")
		public void exportToExcel(HttpServletResponse response) throws IOException {
			
			response.setContentType("application/octet-stream");

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=PhieuDatHang_" + item + ".xlsx";
			response.setHeader(headerKey, headerValue);

			List<OrderForSuplierDetail> listOrders = orderForSupplyDetailRepository.listOrderForSupplyDetail(item);
			
			ExportExcelOrder excelExport = new ExportExcelOrder(listOrders);

			excelExport.export(response);
			
			/*
			OrderForSupplier order = orderForSupplierRepository.findOne(item);
			String name = order.get
			*/
		}
}
