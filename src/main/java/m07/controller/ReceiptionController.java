package m07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class ReceiptionController {
	
	@RequestMapping(value = "/admin/importOrderFromSupplier", method = RequestMethod.GET)
    public String importOrder(Model model) {
    	return "/admin/importReceiption";
    }
    

}
