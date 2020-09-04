package m07.shop.controller;

import m07.entity.*;
import m07.repository.CustomersRepository;
import m07.repository.OrderDetailRepository;
import m07.repository.OrderRepository;
import m07.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController extends BaseController   {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomersRepository customerRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @RequestMapping(value = "addToCart",
            method = RequestMethod.GET)
    public String addToCart(@RequestParam(value = "id") String id,
                            HttpServletRequest request){
        int productId = Integer.valueOf(id);
        Product product =
                productRepository.findOne(productId);
        HttpSession session = request.getSession();
        List<CartItem> carts =
                (List<CartItem>) session.getAttribute("carts");
        if (carts != null) {
            boolean isExistingId = false;
            for(CartItem item: carts) {
                // da co 1 product ID trong cart -> tang quantity len 1
                if (item.getProduct().getId() == productId) {
                    item.setQuantity(item.getQuantity() + 1);
                    isExistingId = true;
                    break;
                }
            }
            if (!isExistingId) {
                CartItem cartItem = new CartItem();
                cartItem.setQuantity(1);
                cartItem.setProduct(product);
                carts.add(cartItem);
            }
        } else {
            carts = new ArrayList<>();
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            carts.add(cartItem);
        }
        session.setAttribute("carts", carts);

        return "redirect:/";
    }

    @RequestMapping("/deleteCart")
	public String deleteGh(@RequestParam (value = "id") String id, HttpServletRequest request) {
		int index=0;
		int productId = Integer.valueOf(id);
        Product product = 
                productRepository.findOne(productId);
        HttpSession session = request.getSession();
        List<CartItem> carts =
                (List<CartItem>) session.getAttribute("carts");
		
		carts.remove(index);
		
		return "cart";
	}
    
    @RequestMapping(value = "viewCart", method = RequestMethod.GET)
    public String viewCart(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (s != null) {
            SecurityContextImpl context = (SecurityContextImpl) s;
            String loggedInUser = context.getAuthentication().getName();
            model.addAttribute("id", loggedInUser);
            
        }
        return "cart";
    }

    
    @RequestMapping(value = "/checkout")
    public String checkOut(Model model , HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Object s = httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        Customer customer = new Customer();
        if (s != null) {
            SecurityContextImpl context = (SecurityContextImpl) s;
            String loggedInUser = context.getAuthentication().getName();
            model.addAttribute("id", loggedInUser);
            Customer customers= customerRepository.getCustomer(loggedInUser);
            model.addAttribute("fullName",customers.getFullname());
            model.addAttribute("phone",customers.getPhone());
            model.addAttribute("address", customers.getAddress());
            
        }
        Order order = new Order();
        model.addAttribute("orderModel", order);

        return "checkout";
    }

    // handle form submit 
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String doCheckOut(Model model,
                             Order order,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object s = session.getAttribute("SPRING_SECURITY_CONTEXT");
        Customer customer = new Customer();
        if (s != null) {
            SecurityContextImpl context = (SecurityContextImpl) s;
            String loggedInUser = context.getAuthentication().getName();
            customer = customerRepository.getCustomer(loggedInUser);
            //customer.setId(loggedInUser);//customer null nè, chắc v
            order.setCustomer(customer);
        }
        
        orderRepository.save(order);
        //Customer id = (Customer) request.getAttribute("id"); 

        List<CartItem> cartItems
                = (List<CartItem>) session.getAttribute("carts");
        double totalPrice = 0;

        for(CartItem cartItem: cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            System.out.println("id product"+cartItem.getQuantity());
            Product product =
                    productRepository.findOne(cartItem.getProduct().getId());
            System.out.println("quantity "+product.getQuantity() );
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setOrder(order);
            double price = cartItem.getQuantity() * cartItem.getProduct().getUnitPrice();
            totalPrice += price;
           /* orderDetail.setTotalPrice(price);*/
            orderDetail.setStatus("Chờ xác nhận");
            
            product.setQuantity(product.getQuantity()-cartItem.getQuantity());
            
            productRepository.save(product);
            orderDetailRepository.save(orderDetail);
        }
        order.setTotalPrice(totalPrice);
        order.setStatus("Cho duyet");
        Date date = new Date();
        order.setOrderDate(date);
        orderRepository.save(order);
        session.removeAttribute("carts");
//        session.setAttribute("carts", null);
//        session.invalidate();
        model.addAttribute("orderId",order.getId()); 
        return "checkout_success";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(sdf, true));
    }
}
