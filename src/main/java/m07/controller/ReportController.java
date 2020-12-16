package m07.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
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
	
	//======================= REPORT DOANH THU ============================
	
	@RequestMapping(value = "/admin/reportDoanhThu")
	public String doanhthu(ModelMap model) {

		return "/admin/reportDoanhThu";
	}
	
	@RequestMapping(value = "/admin/reportDoanhThuTest", method = RequestMethod.POST)
	public String doanhthu(Model model, HttpServletRequest req) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("fromDate: " + req.getParameter("fromdate"));

		/* Date fromdate = format.parse(req.getParameter("fromdate")); */
		Date todate = format.parse(req.getParameter("todate"));

		List<Object[]> listDoanhThu = productRepository.doanhthu(todate);
		model.addAttribute("listDoanhThu", listDoanhThu);
		
		/* model.addAttribute("datefrom", req.getParameter("fromdate")); */
		model.addAttribute("dateto", req.getParameter("todate"));
		
		return "/admin/reportDoanhThu";
	}

	//======================= REPORT LOI NHUAN ============================
	
	@RequestMapping(value = "/admin/reportLoiNhuan")
	public String loinhuan(ModelMap model) {
		
		return "/admin/reportLoiNhuan";
	}
	 
	@RequestMapping(value = "/admin/reportLoiNhuanTest", method = RequestMethod.POST)
	public String loinhuan(Model model, HttpServletRequest req) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("fromDate: " + req.getParameter("fromdate"));

		/* Date fromdate = format.parse(req.getParameter("fromdate")); */
		Date todate = format.parse(req.getParameter("todate"));

		List<Object[]> listLoiNhuan = productRepository.loinhuan(todate, todate);
		model.addAttribute("listLoiNhuan", listLoiNhuan);
		
		/* model.addAttribute("datefrom", req.getParameter("fromdate")); */
		model.addAttribute("dateto", req.getParameter("todate"));
		
		return "/admin/reportLoiNhuan";
	}
	
	//======================= REPORT LOI NHUAN ============================
	
	@RequestMapping(value = "/admin/reportTonKho")
    public String tonKho(Model model) {
		
        return "/admin/reportTonKho";
    }
	
	@RequestMapping(value = "/admin/reportTonKhoTest", method = RequestMethod.POST)
	public String tonkho(Model model, HttpServletRequest req) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("fromDate: " + req.getParameter("fromdate"));  

		/* Date fromdate = format.parse(req.getParameter("fromdate")); */
		Date todate = format.parse(req.getParameter("todate"));

		List<Object[]> listTonKho = productRepository.tonkho(todate, todate);
		model.addAttribute("listTonKho", listTonKho);
		
		/* model.addAttribute("datefrom", req.getParameter("fromdate")); */
		model.addAttribute("dateto", req.getParameter("todate"));
		
		return "/admin/reportTonKho";
	}
	
	//======================== XUAT FILE EXCEL =============================
	  
	@RequestMapping(value = "admin/admin/exportExcelLoiNhuan")
	public void exportLoiNhuan(HttpServletResponse response, HttpServletRequest req) throws IOException, ParseException {
		
		response.setContentType("application/octet-stream");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("fromDate: " + req.getParameter("todate"));

		/* Date fromdate = format.parse(req.getParameter("fromdate")); */
		Date todate = format.parse(req.getParameter("todate"));

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ThongKeLoiNhuan.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Object[]> listLN = productRepository.loinhuan(todate, todate);

		ExportExcelLoiNhuan excelExport = new ExportExcelLoiNhuan(listLN);
		
		System.out.println(listLN.get(0)[4]);
		
		HttpSession httpSession = req.getSession();
		Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl context = (SecurityContextImpl) s;
		String loggedInUser = context.getAuthentication().getName();

		String name = customersRepository.getFullName(loggedInUser);
		
		excelExport.export(response, name, todate);
	}
	
	@RequestMapping(value = "admin/admin/exportExcelDoanhThu")
	public void exportExcelDoanhThu(HttpServletResponse response, HttpServletRequest req) throws IOException, ParseException {
		
		response.setContentType("application/octet-stream");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("fromDate: " + req.getParameter("todate"));

		/* Date fromdate = format.parse(req.getParameter("fromdate")); */
		Date todate = format.parse(req.getParameter("todate"));

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ThongKeDoanhThu.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Object[]> listDT = productRepository.doanhthu(todate);

		ExportExcelDoanhThu excelExport = new ExportExcelDoanhThu(listDT);
		
		System.out.println(listDT.get(0)[4]);
		
		HttpSession httpSession = req.getSession();
		Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl context = (SecurityContextImpl) s;
		String loggedInUser = context.getAuthentication().getName();

		String name = customersRepository.getFullName(loggedInUser);
		
		excelExport.export(response, name, todate);
	}
	
	@RequestMapping(value = "admin/admin/exportExcelTonKho")
	public void exportExcelTonKho(HttpServletResponse response, HttpServletRequest req) throws IOException, ParseException {
		
		response.setContentType("application/octet-stream");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("fromDate: " + req.getParameter("todate"));

		/* Date fromdate = format.parse(req.getParameter("fromdate")); */
		Date todate = format.parse(req.getParameter("todate"));

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ThongKeTonKho.xlsx";
		response.setHeader(headerKey, headerValue);

		List<Object[]> listTK = productRepository.tonkho(todate, todate);

		ExportExcelTonKho excelExport = new ExportExcelTonKho(listTK);
		
		System.out.println(listTK.get(0)[4]);
		
		HttpSession httpSession = req.getSession();
		Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl context = (SecurityContextImpl) s;
		String loggedInUser = context.getAuthentication().getName();

		String name = customersRepository.getFullName(loggedInUser);
		
		excelExport.export(response, name, todate);
	}
	
}
