/*
 * package m07.controller;
 * 
 * import java.io.IOException; import java.nio.file.Files; import
 * java.nio.file.Path; import java.nio.file.Paths; import java.text.DateFormat;
 * import java.text.SimpleDateFormat; import java.util.ArrayList; import
 * java.util.Date; import java.util.List;
 * 
 * import javax.servlet.ServletContext; import
 * javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpSession;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.context.SecurityContextImpl; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import org.springframework.ui.ModelMap; import
 * org.springframework.validation.BindingResult; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.context.ServletContextAware; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import m07.entity.ReceipDetail; import m07.entity.Receiption; import
 * m07.entity.ViewReceiptionDetail; import m07.repository.CustomersRepository;
 * import m07.repository.OrderForSupplierRepository; import
 * m07.repository.ReceiptionDetailRepository;
 * 
 * @Controller
 * 
 * @RequestMapping(value = "/") public class ImportReceiptionController
 * implements ServletContextAware{
 * 
 * @Autowired CustomersRepository customersRepository;
 * 
 * @Autowired OrderForSupplierRepository orderForSupplierRepository;
 * 
 * @Autowired ReceiptionDetailRepository receiptionDetailRepository;
 * 
 * private ServletContext servletContext;
 * 
 * @RequestMapping(value = "/admin/importR", method = RequestMethod.GET) public
 * String index(Model model, HttpServletRequest request) {
 * 
 * return "admin/testImport"; }
 * 
 * @RequestMapping(value = "/admin/import", method = RequestMethod.POST) public
 * String process(@RequestParam("file") MultipartFile file, HttpServletRequest
 * request) throws IOException{
 * 
 * String fileName = uploadExcelFile(file); System.out.println("fileName: " +
 * fileName);
 * 
 * String excelPath = servletContext.getRealPath("/resources/fileEcxels/" +
 * fileName); System.out.println("excelPath: " + excelPath);
 * 
 * // doc file excel ArrayList<ViewReceiptionDetail> listview = new
 * ArrayList<ViewReceiptionDetail>();
 * 
 * ReceiptionExcelImport receiptionExcelImport = new ReceiptionExcelImport();
 * ReceiptionExcelImport.ReadFile(excelPath, listview);
 * 
 * System.out.println("danh sach receipdetail"); for(ViewReceiptionDetail item :
 * listview) {
 * 
 * ReceipDetail rd = new ReceipDetail();
 * 
 * System.out.println("id product: " + item.getIdSp());
 * //rd.getProducts().setId(item.getIdSp());
 * 
 * System.out.println("don gia: " + item.getDongia());
 * rd.setUnitPrice(item.getDongia());
 * 
 * System.out.println("so luong: " + item.getSoluong());
 * rd.setQuantity(item.getSoluong());
 * 
 * System.out.println("==================="); rd =
 * receiptionDetailRepository.save(rd); }
 * 
 * return "redirect:/admin/importOrderFromSupplier"; }
 * 
 * private String uploadExcelFile(MultipartFile multipartFile) { try { byte
 * []bytes = multipartFile.getBytes(); Path path =
 * Paths.get(servletContext.getRealPath("/resources/fileEcxels/" +
 * multipartFile.getOriginalFilename())); Files.write(path, bytes); return
 * multipartFile.getOriginalFilename(); } catch (Exception e) { return null; } }
 * 
 * @Override public void setServletContext(ServletContext servletContext) {
 * this.servletContext = servletContext; } }
 */