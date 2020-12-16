package m07.controller;

import m07.entity.Supplier;
import m07.repository.OrderForSupplierRepository;
import m07.repository.ProductRepository;
import m07.repository.SuppliersRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class SuppliersController {

    @Autowired
    SuppliersRepository suppliersRepository;
    
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    OrderForSupplierRepository orderForSupplierRepository;

    @RequestMapping(value = "/admin/addsuppliers")
    public String addproduct(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "admin/addsuppliers";
    }

    @RequestMapping(value = "/admin/addsuppliers", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("category") Supplier supplier, ModelMap model,
                            @RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) {
    	
        String path = httpServletRequest.getSession().getServletContext().getRealPath("/") + "resources/uploads/";
        
        try {
            FileUtils.forceMkdir(new File(path));
            File upload = new File(path + file.getOriginalFilename());
            file.transferTo(upload);
        } catch (IOException e) {
            e.printStackTrace();
        }

        supplier.setLogo(file.getOriginalFilename());
        Supplier s = suppliersRepository.save(supplier);

        if (null != s) {
            model.addAttribute("message", "Update success");
            model.addAttribute("category", supplier);
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("supplier", supplier);
        }
        return "redirect:/admin/listSupplier";
    }

    @RequestMapping(value = "/admin/editsuppliers", method = RequestMethod.GET)
    public String editSupper(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("supplier1", suppliersRepository.findOne(id));
        return "admin/editsuppliers";
    }

    @RequestMapping(value = "/admin/editsuppliers", method = RequestMethod.POST)
    public String editSupp(@ModelAttribute("supplier") Supplier supplier, Model model,
                           @RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) {

        String path = httpServletRequest.getSession().getServletContext().getRealPath("/") + "resources/uploads/";

        try {
            FileUtils.forceMkdir(new File(path));
            File upload = new File(path + file.getOriginalFilename());
            file.transferTo(upload);
        } catch (IOException e) {
            e.printStackTrace();
        }

        supplier.setLogo(file.getOriginalFilename());

        Supplier cs = suppliersRepository.save(supplier);
        if (null != cs) {
            model.addAttribute("message", "Update success");
            model.addAttribute("category", suppliersRepository.findOne(cs.getId()));
        } else {
            model.addAttribute("message", "Update failure");
            model.addAttribute("supplier", supplier);
        }
        return "redirect:/admin/listSupplier";
    }

    @RequestMapping(value = "deletesup/{id}", method = RequestMethod.GET)
    public String deletesup(@PathVariable("id") int id, ModelMap model) {
    	
    	String idProduct = productRepository.checkExistSupplier(id);
    	String order = orderForSupplierRepository.checkExistSupplier(id);
    	
    	if (idProduct == null && order == null) {
    		suppliersRepository.delete(id);
			model.addAttribute("message", "xoa thanh cong");
		} else {
			model.addAttribute("message", "khong duoc xoa");
		}
        
        return "redirect:/admin/listSupplier";
    }

}
