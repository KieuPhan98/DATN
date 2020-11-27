package m07.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import m07.entity.OrderForSupplier;
import m07.entity.Supplier;
import m07.repository.CustomersRepository;
import m07.repository.ProductRepository;
import m07.repository.SuppliersRepository;

@Controller
@RequestMapping(value = "/")
public class ReportController {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@Autowired
	SuppliersRepository suppliersRepository;
	
	@RequestMapping(value = "/admin/reportTonKho")
    public String tonKho(Model model) {
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		
		System.out.println(currentDateTime);
		
		List<Object[]> listTonKho = productRepository.tonkho(currentDateTime, currentDateTime);
		
		System.out.println("listTonKho[1]: " + listTonKho.get(0)[2]);
		model.addAttribute("listTonKho", listTonKho);
		
        return "/admin/reportTonKho";
    }
	
	@RequestMapping(value = "/admin/reportLoiNhuan")
	public String loinhuan(ModelMap model) {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());
		
		List<Object[]> listLoiNhuan = productRepository.loinhuan(currentDateTime, currentDateTime);
		model.addAttribute("listLoiNhuan", listLoiNhuan);
		
		return "/admin/reportLoiNhuan";
	}
	
	//======================test=====================
	
	  @RequestMapping(value = "/admin/reportDoanhThu")
	  public String doanhthu(ModelMap model){
	  
	  DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	  String currentDateTime = dateFormatter.format(new Date());
	  
	  List<Object[]> listDoanhThu = productRepository.doanhthu(currentDateTime);
	  model.addAttribute("listDoanhThu", listDoanhThu);
	  
	  return "/admin/reportDoanhThu";
	  
	  }
	
	//======================== XUAT FILE EXCEL =============================
	  
	@RequestMapping(value = "/admin/exportExcelLoiNhuan")
	public void exportLoiNhuan(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ThongKeLoiNhuan.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Object[]> listLN = productRepository.loinhuan(currentDateTime, currentDateTime);

		ExportExcelLoiNhuan excelExport = new ExportExcelLoiNhuan(listLN);
		
		System.out.println(listLN.get(0)[4]);
		
		HttpSession httpSession = request.getSession();
		Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl context = (SecurityContextImpl) s;
		String loggedInUser = context.getAuthentication().getName();

		String name = customersRepository.getFullName(loggedInUser);
		
		excelExport.export(response, name);
	}
	
	@RequestMapping(value = "/admin/exportExcelTonKho")
	public void exportExcelTonKho(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ThongKeTonKho.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Object[]> listTK = productRepository.tonkho(currentDateTime, currentDateTime);

		ExportExcelTonKho excelExport = new ExportExcelTonKho(listTK);
		
		System.out.println(listTK.get(0)[4]);
		
		HttpSession httpSession = request.getSession();
		Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl context = (SecurityContextImpl) s;
		String loggedInUser = context.getAuthentication().getName();

		String name = customersRepository.getFullName(loggedInUser);
		
		excelExport.export(response, name);
	}
	
	@RequestMapping(value = "/admin/exportExcelDoanhThu")
	public void exportExcelDoanhThu(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ThongKeDoanhThu.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Object[]> listDT = productRepository.doanhthu(currentDateTime);

		ExportExcelDoanhThu excelExport = new ExportExcelDoanhThu(listDT);
		
		System.out.println(listDT.get(0)[4]);
		
		HttpSession httpSession = request.getSession();
		Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl context = (SecurityContextImpl) s;
		String loggedInUser = context.getAuthentication().getName();

		String name = customersRepository.getFullName(loggedInUser);
		
		excelExport.export(response, name);
	}
	
}
