package m07.shop.controller;

import m07.entity.Order;
import m07.entity.OrderDetail;
import m07.entity.Product;
import m07.repository.OrderDetailRepository;
import m07.repository.OrderRepository;
import m07.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class OrderController extends  BaseController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;
    
    @Autowired
    ProductRepository productRepository;

    String idCustomer;
    
    //show tat ca cac don hang cua customer
    @RequestMapping(value = "/listordercus")
    public String showorderbycus(Model model, @RequestParam("customerId") String customerId , HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (s != null) {
            SecurityContextImpl context = (SecurityContextImpl) s;
            String loggedInUser = context.getAuthentication().getName();
            model.addAttribute("id", loggedInUser);
        }

        idCustomer = customerId;
        List<Order> orderList = (List<Order>) orderRepository.listoderbycus(customerId);
        model.addAttribute("listordercus", orderList);
        
        List<Order> orderList1 = (List<Order>) orderRepository.listoderbycus1(customerId);
        model.addAttribute("listordercus1", orderList1);
        
        List<Order> orderList2 = (List<Order>) orderRepository.listoderbycus2(customerId);
        model.addAttribute("listordercus2", orderList2);
        
        List<Order> orderList3 = (List<Order>) orderRepository.listoderbycus3(customerId);
        model.addAttribute("listordercus3", orderList3);
        
        return "listordercus";
    }

    @RequestMapping(value = "/detailOrder", method = RequestMethod.GET)
    public String detailOders(@RequestParam("id") Integer id,ModelMap model)
    {
        model.addAttribute("orderDetails", orderDetailRepository.listorderDetail(id));
        
        Order order = orderRepository.findOne(id);
        System.out.println("ten ng nhan" + order.getReceiver());
        System.out.println("sdt ng nhan: " + order.getPhone() );
        System.out.println("dia chi ng nhan: " + order.getAddress() + " - " + order.getDistrict());
        
        model.addAttribute("nameReceiver", order.getReceiver());
        model.addAttribute("phoneReceiver", order.getPhone());
        model.addAttribute("addressReceiver", order.getAddress() + " - " + order.getDistrict());
        
        return "listOrderDetail";
    }
    
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
    public String cancelOrders(@RequestParam("id") Integer id,ModelMap model)
    {
    	// chuyen trang thai don hang thanh Da huy
    	Order order = orderRepository.findOne(id);
    	order.setStatus("Da huy");
    	
    	orderRepository.save(order);
    	
    	// cap nhat lai so luong ton cho san pham
    	List<OrderDetail> listProduct = orderDetailRepository.searchOrder(id);
		
		for(OrderDetail detail : listProduct) {
			System.out.println("id product: " + detail.getProduct().getId());
			System.out.println("quantity: " + detail.getQuantity());
			
			Product product = productRepository.findOne(detail.getProduct().getId());
			product.setQuantity(product.getQuantity() + detail.getQuantity());
			productRepository.save(product);
		}
    	
        return "redirect:/listordercus?customerId=" + idCustomer;
    }

}
