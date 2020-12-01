package m07.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import m07.entity.OrderForSuplierDetail;
import m07.entity.OrderForSupplier;
import m07.entity.Product;
import m07.entity.ReceipDetail;
import m07.entity.Receiption;
import m07.entity.ViewReceiptionDetail;
import m07.repository.CustomersRepository;
import m07.repository.OrderForSupplierRepository;
import m07.repository.OrderForSupplyDetailRepository;
import m07.repository.ProductRepository;
import m07.repository.ReceiptionDetailRepository;
import m07.repository.ReceiptionRepository;

@Controller
@RequestMapping(value = "/")
public class ReceiptionController implements ServletContextAware{

	private int item;
	private String loginID;

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

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

	@Autowired
	OrderForSupplyDetailRepository orderForSupplyDetailRepository;

	@ModelAttribute("productList")
	public List<Product> productList(Model model) {
		List<Product> productList = (List<Product>) productRepository.findAll();
		return productList;
	}

	@ModelAttribute("idOrderList")
	public List<Integer> idOrderList(Model model) {
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

		return "admin/addReceiption";
	}

	@RequestMapping(value = "/admin/addReceiption", method = RequestMethod.POST)
	public String addReceiption(Model model, @ModelAttribute("receip") Receiption receip, BindingResult bindingResult,
			HttpServletRequest request) {
		Date date = new Date();
		receip.setCreateDate(date);

		receip.getCustomer().setId(loginID);

		System.out.println(receip.getOrderForSupplierId());
		
		Receiption receiption = receiptionRepository.save(receip);
		
		int id = Integer.valueOf(receip.getOrderForSupplierId());
		OrderForSupplier order = orderForSupplierRepository.findOne(id);
		order.setStatus("da nhap hang");

		OrderForSupplier order1 = orderForSupplierRepository.save(order);
		
		return "redirect:/admin/importOrderFromSupplier";
	}

	
	//================================ IMPORT RECEIPTION =================================
	
private ServletContext servletContext;
	private int idR;
	@RequestMapping(value = "/admin/importR", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, @RequestParam("id") Integer id) {
		idR = id;
		return "admin/testImport";
	}
	
	@RequestMapping(value = "/admin/import", method = RequestMethod.POST)
	public String process(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException{
		
		String fileName = uploadExcelFile(file);
		System.out.println("fileName: " + fileName);
		
		String excelPath = servletContext.getRealPath("/resources/fileEcxels/" + fileName);
		System.out.println("excelPath: " + excelPath);
		
		// doc file excel
		ArrayList<ViewReceiptionDetail> listview = new ArrayList<ViewReceiptionDetail>();
		
		ImportExcelReceiption receiptionExcelImport = new ImportExcelReceiption();
        ImportExcelReceiption.ReadFile(excelPath, listview);
        
        System.out.println("danh sach receipdetail");
        double sum = 0;
        for(ViewReceiptionDetail i : listview) {
        	
			ReceipDetail rd = new ReceipDetail();

			System.out.println("id product: " + i.getIdSp());
			// tang so luong sp
			Product prod = productRepository.findOne(i.getIdSp());
			prod.setQuantity(prod.getQuantity() + i.getSoluong());
			prod.setUnitPrice(i.getDongia() + 50);
			prod = productRepository.save(prod);
			
			rd.setProducts(prod);

			System.out.println("don gia: " + i.getDongia());
			rd.setUnitPrice(i.getDongia());

			System.out.println("so luong: " + i.getSoluong());
			rd.setQuantity(i.getSoluong());
			
			sum += i.getDongia() * i.getSoluong();
			System.out.println("tong: " + sum);
			
			System.out.println("id receiption: " + idR);
			Receiption r = receiptionRepository.findOne(idR);
			r.setTotalPrice(sum);
			r = receiptionRepository.save(r);
			rd.setReceiption(r);
			
			System.out.println("===================");
			rd = receiptionDetailRepository.save(rd);
        }
        
		return "redirect:/admin/importOrderFromSupplier";
	}

	private String uploadExcelFile(MultipartFile multipartFile) {
		try {
			byte []bytes = multipartFile.getBytes();
			Path path = Paths.get(servletContext.getRealPath("/resources/fileEcxels/" + multipartFile.getOriginalFilename()));
			Files.write(path, bytes);
			return multipartFile.getOriginalFilename();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	// ============================ RECEIPTION DETAIL // ===================================
	
	@RequestMapping(value = "/admin/receiptionDetail", method = RequestMethod.GET)
	public String detailReceiption(@RequestParam("id") Integer id, ModelMap model) {
		item = id;
		List<ReceipDetail> listDetail = (List<ReceipDetail>) receiptionDetailRepository.listReceipDetail(id);
		model.addAttribute("detail", listDetail);

		// System.out.println("chi tiet phieu nhap: " + listDetail.toString());
		return "/admin/receiptionDetail";
	}

	@RequestMapping(value = "admin/admin/addReceiptionDetail")
	public String addProduct(Model model) {
		
		model.addAttribute("product1", item);
		
		ReceipDetail orderDetail = new ReceipDetail();
		model.addAttribute("product", orderDetail);

		System.out.println(item);
		// truyen list product thuoc 1 phieu dat
		Receiption receip = receiptionRepository.findOne(item);
		
		int id = Integer.valueOf(receip.getOrderForSupplierId());
		OrderForSupplier order = orderForSupplierRepository.findOne(id);

		List<OrderForSuplierDetail> listOrderDetail = orderForSupplyDetailRepository
				.listOrderForSupplyDetail(order.getId());
		List<Product> list = new ArrayList<>();

		for (OrderForSuplierDetail i : listOrderDetail) {
			System.out.println("them sp vao list");
			Product prod = productRepository.findOne(i.getProducts().getId());
			list.add(prod);
		}

		for (Product p : list) {
			System.out.println("ma sp trong list");
			System.out.println(p.getName());
		}
		model.addAttribute("list", list);

		return "admin/addReceiptionDetail";
	}
	
	  @RequestMapping(value = "admin/addReceiptionDetail", method = RequestMethod.POST)
		public String addProduct(@Validated @ModelAttribute("product") ReceipDetail detial, ModelMap model,
				BindingResult bindingResult) {

			int receipID = detial.getReceiption().getId();
			int productID = detial.getProducts().getId();
			int quantity = detial.getQuantity();
			double price = detial.getUnitPrice();
			double newPrice = price + 50;

			System.out.println(receipID + " ID RECEIP");
			System.out.println(productID + " ID PRODUCT");
			System.out.println(quantity + " QUANTITY");

			String idOrderDetail = receiptionDetailRepository.idReceipDetail(receipID, productID);
			System.out.println(idOrderDetail + " ID ORDER DETAIL");

			if (idOrderDetail != null) {
				int id = Integer.valueOf(idOrderDetail);

				ReceipDetail receipDetail = receiptionDetailRepository.findOne(id);

				detial.setQuantity(quantity + receipDetail.getQuantity());
				detial.setId(id);
			}
			
			ReceipDetail c = receiptionDetailRepository.save(detial);
			
			Product product = productRepository.findOne(productID);
			
			int old_quantity = product.getQuantity();
			product.setQuantity(quantity + old_quantity);
			product.setUnitPrice(newPrice);
			
			System.out.println("old quantity: " + old_quantity);
			
			productRepository.save(product);
			System.out.println("sum quantity: " + product.getQuantity());

			//tinh sum don nhap hang
			List<ReceipDetail> lisReceip = receiptionDetailRepository.listReceipDetail(receipID);
			double sum = 0;
			for(ReceipDetail detail : lisReceip) {
				sum += detail.getQuantity() * detail.getUnitPrice();
			}
			System.out.println("sum: " + sum);
			Receiption receip = receiptionRepository.findOne(receipID);
			receip.setTotalPrice(sum);
			receiptionRepository.save(receip);
			//===============
			
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
		
		ReceipDetail receipDetail = receiptionDetailRepository.findOne(id);
		int max = receipDetail.getQuantity();
		model.addAttribute("product", receipDetail);
		model.addAttribute("max", max);
		
		Product prod = productRepository.findOne(receipDetail.getProducts().getId());
		prod.setQuantity(prod.getQuantity() - receipDetail.getQuantity());
		
		productRepository.save(prod);
		return "admin/editProductReceiption";
	}

	@RequestMapping(value = "/admin/editProductReceiption", method = RequestMethod.POST)
	public String editProduct(@ModelAttribute("editDetail") ReceipDetail detail, Model model, RedirectAttributes rs) {
		
		ReceipDetail cs = receiptionDetailRepository.save(detail);
		
		System.out.println(detail.getId());
		System.out.println(detail.getProducts().getId());
		System.out.println(detail.getQuantity());
		System.out.println(detail.getUnitPrice());
		
		Product prod = productRepository.findOne(detail.getProducts().getId());
		prod.setQuantity(prod.getQuantity() + detail.getQuantity());
		prod.setUnitPrice(detail.getUnitPrice() + 50);
		
		productRepository.save(prod);
		
		//tinh sum don nhap hang
		List<ReceipDetail> lisReceip = receiptionDetailRepository.listReceipDetail(detail.getReceiption().getId());
		double sum = 0;
		for(ReceipDetail details : lisReceip) {
			sum += details.getQuantity() * details.getUnitPrice();
		}
		System.out.println("sum: " + sum);
		Receiption receip = receiptionRepository.findOne(detail.getReceiption().getId());
		receip.setTotalPrice(sum);
		receiptionRepository.save(receip);
		//===============
		
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
		
		ReceipDetail detail = receiptionDetailRepository.findOne(id);
		
		Product prod = productRepository.findOne(detail.getProducts().getId());
		prod.setQuantity(prod.getQuantity() - detail.getQuantity());
		
		productRepository.save(prod);
		
		receiptionDetailRepository.delete(id);
		
		// tinh sum don nhap hang
		List<ReceipDetail> lisReceip = receiptionDetailRepository.listReceipDetail(detail.getReceiption().getId());
		double sum = 0;
		for (ReceipDetail details : lisReceip) {
			sum += details.getQuantity() * details.getUnitPrice();
		}
		System.out.println("sum: " + sum);
		Receiption receip = receiptionRepository.findOne(detail.getReceiption().getId());
		receip.setTotalPrice(sum);
		receiptionRepository.save(receip);
		// ===============
  
		String url = "redirect:/admin/receiptionDetail?id=" + item;

		return url;
	}
}
