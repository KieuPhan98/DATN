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

import m07.entity.OrderForSuplierDetail;
import m07.entity.Product;
import m07.entity.ReceipDetail;
import m07.entity.Receiption;
import m07.repository.ProductRepository;
import m07.repository.ReceiptionDetailRepository;
import m07.repository.ReceiptionRepository;

@Controller
@RequestMapping(value = "/")
public class ReceiptionController {
	
	private int item;
	
	@Autowired
	ReceiptionRepository receiptionRepository;
	
	@Autowired
	ReceiptionDetailRepository receiptionDetailRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@ModelAttribute("productList")
    public List<Product> productList(Model model) {
        List<Product> productList = (List<Product>) productRepository.findAll();
        return productList;
    }
	
	@RequestMapping(value = "/admin/importOrderFromSupplier", method = RequestMethod.GET)
    public String importOrder(Model model) {
		
		List<Receiption> listReceiption = (List<Receiption>) receiptionRepository.listReceiption();
		/*
		 * System.out.println("Info : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: listReceiption"
		 * ); for (Receiption e: listReceiption) { e.toString();
		 * 
		 * }
		 */
		model.addAttribute("listReceiption", listReceiption);
    	return "/admin/importReceiption";
    }
	
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
		
		return "admin/addReceiptionDetail";
	}
	
	
	  @RequestMapping(value = "admin/addReceiptionDetail", method = RequestMethod.POST)
	  public String addProduct(@Validated @ModelAttribute("product") ReceipDetail product, ModelMap model, BindingResult bindingResult) {
			   
		  ReceipDetail c = receiptionDetailRepository.save(product);
	  
		  if (bindingResult.hasErrors()) { 
			  model.addAttribute("message", "that bai");
			  return "/admin/addReceiptionDetail";
		  } 
		  else { 
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
