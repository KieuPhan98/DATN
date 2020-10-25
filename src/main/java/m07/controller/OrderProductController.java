package m07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderProductController {

	@GetMapping(value = "orderProduct")
	public String index() {

		return null;

	}

}
